package com.coco.pibackend.controller;

import com.coco.pibackend.Dto.LoginDto;
import com.coco.pibackend.Dto.SignupDto;
import com.coco.pibackend.Dto.UpdateProfileDto;
import com.coco.pibackend.Entity.*;
import com.coco.pibackend.Security.jwt.JwtUtils;
import com.coco.pibackend.Security.services.EmailService;
import com.coco.pibackend.Security.services.UserDetailsImpl;
import com.coco.pibackend.repository.PasswordResetTokenRepository;
import com.coco.pibackend.repository.RoleRepository;
import com.coco.pibackend.repository.UserRepository;
import com.coco.pibackend.repository.VerificationTokenRepository;
import com.coco.pibackend.response.JwtResponse;
import com.coco.pibackend.response.MessageResponse;
import com.coco.pibackend.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private EmailService emailService;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private ProfileService profileService;


    @Operation(description = "signin")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                userDetails.getFirstname(),
                userDetails.getLastename(),
                userDetails.getCin(),
                userDetails.getPhone()));
    }

    @Operation(description = "signup")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signupDto) {
        if (userRepository.existsByUsername(signupDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signupDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Création du compte utilisateur
        User user = new User(signupDto.getUsername(),
                signupDto.getEmail(),
                encoder.encode(signupDto.getPassword()));
        user.setFirstName(signupDto.getFirstname());
        user.setLasteName(signupDto.getLastename());
        user.setCin(signupDto.getCin());
        user.setPhone(signupDto.getPhone());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_MEMBRE)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);


        // Generate the verification token
        String token = UUID.randomUUID().toString();
        createVerificationToken(user, token);

        // Send verification email
        emailService.sendVerificationEmail(user, token);

        return ResponseEntity.ok(new MessageResponse("User registered successfully. Please check your email for verification."));
    }
    private void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
    }
    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid token."));
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Account activated successfully!"));
    }
    @Operation(description = "Forgot Password")
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("User not found with this email."));
        }

        // Generate reset token
        String resetToken = UUID.randomUUID().toString();
        createPasswordResetToken(user, resetToken);

        // Send password reset email
        emailService.sendPasswordResetEmail(user, resetToken);

        return ResponseEntity.ok(new MessageResponse("Password reset instructions sent to your email."));
    }
    private void createPasswordResetToken(User user, String token) {
        PasswordResetToken resetToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(resetToken);
    }
    // Ajouter cette méthode dans votre contrôleur AuthController
    @Operation(description = "Reset Password")
    @PostMapping("/reset-password/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable("token") String token,
                                           @RequestBody String newPassword) {
        // Trouver le token de réinitialisation dans la base de données
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);

        if (resetToken == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid or expired token."));
        }

        // Vérifier si le token est toujours valide
        if (resetToken.isExpired()) {
            // Gérer le cas où le token a expiré
            return ResponseEntity.badRequest().body(new MessageResponse("Token has expired. Please request a new password reset."));
        }

        User user = resetToken.getUser();
        // Mettre à jour le mot de passe de l'utilisateur avec la nouvelle password

        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);

        // Supprimer le token de réinitialisation car il n'est plus nécessaire après avoir réinitialisé le mot de passe
        passwordResetTokenRepository.delete(resetToken);

        return ResponseEntity.ok(new MessageResponse("Password reset successfully."));
    }





    @GetMapping("/users/pdf")
    public void exportUsersPdf(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"liste_utilisateurs.pdf\"");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Définir des polices et des couleurs
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, new BaseColor(255, 0, 0));
        Font headingFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        Font textFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

        Paragraph title = new Paragraph("Liste des Utilisateurs", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Initialiser PdfPTable avec 4 colonnes
        PdfPTable table = new PdfPTable(4); // 4 colonnes pour username, email, nom, prénom
        table.setWidthPercentage(100);
        table.setWidths(new float[] {2, 3, 2, 2});

        // Ajouter l'en-tête de la table
        String[] columnTitles = {"Username", "Email", "Nom", "Prénom"};
        for (String columnTitle : columnTitles) {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle, headingFont));
            table.addCell(header);
        }

        // Récupérer la liste des utilisateurs
        List<User> users = userRepository.findAll();

        // Ajouter les données des utilisateurs au tableau
        for (User user : users) {
            table.addCell(new Phrase(user.getUsername(), textFont));
            table.addCell(new Phrase(user.getEmail(), textFont));
            table.addCell(new Phrase(user.getFirstName(), textFont));
            table.addCell(new Phrase(user.getLasteName(), textFont));
        }

        document.add(table);
        document.close();
    }


}
