package com.coco.pibackend.ServiceIMp;
import com.twilio.rest.api.v2010.account.Message;
import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.AnnonceCollocationRepository;
import com.coco.pibackend.Repo.UserRepository;
import com.coco.pibackend.Service.AnnonceCollocationService;
import com.twilio.Twilio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.twilio.type.PhoneNumber;
@Service
@RequiredArgsConstructor
public class AnnoncecollocationImpl implements AnnonceCollocationService {
    private final String ACCOUNT_SID = "ACe3aa4b575988dc66fc0f0d64843152c9";
    private final String AUTH_TOKEN = "d9e44bb5ceffd39dedd2d8d36b00a2a8";
    private final String TWILIO_PHONE_NUMBER = "+12513134381";
    private final JavaMailSender javaMailSender;
    private final AnnonceCollocationRepository annonceCollocationRepository;
    private  final UserRepository userRepository;
    @Override
    public AnnonceCollocation addAnnonceCollocation(AnnonceCollocation annonceCollocation) {
        Optional<User>optionalUser=userRepository.findById(annonceCollocation.getUser().getId_user());
        if(optionalUser.isPresent()){
            annonceCollocation.setStatus(true);
            annonceCollocation.setDate_disponiblite(new Date());
            AnnonceCollocation newannance=annonceCollocationRepository.save(annonceCollocation);
            List<User> users=userRepository.findAll();
            String url="http://localhost:4200/DetailsAnnaonce/"+newannance.getId_anno_colo();
            for(int i=0; i<users.size();i++){
                this.sendEmailtoAllUsers(users.get(i).getEmail(),url);
            }
            return newannance;
        }
        return null;
    }

    @Override
    public AnnonceCollocation updateAnnonceCollocation(AnnonceCollocation annonceCollocation) {

        Optional<AnnonceCollocation>optionalAnnonceCollocation=annonceCollocationRepository.findById(annonceCollocation.getId_anno_colo());
        if(optionalAnnonceCollocation.isPresent()){

            optionalAnnonceCollocation.get().setDate_disponiblite(new Date());
            optionalAnnonceCollocation.get().setAddresse(annonceCollocation.getAddresse());
            optionalAnnonceCollocation.get().setMeuble(annonceCollocation.getMeuble());
            optionalAnnonceCollocation.get().setPays(annonceCollocation.getPays());
            optionalAnnonceCollocation.get().setVille(annonceCollocation.getVille());
            optionalAnnonceCollocation.get().setNbrChambre(annonceCollocation.getNbrChambre());
            optionalAnnonceCollocation.get().setNbrPersonne(annonceCollocation.getNbrPersonne());
            optionalAnnonceCollocation.get().setDescription(annonceCollocation.getDescription());
            optionalAnnonceCollocation.get().setMontantContrubition(annonceCollocation.getMontantContrubition());
            optionalAnnonceCollocation.get().setCautionnement(annonceCollocation.getCautionnement());
            optionalAnnonceCollocation.get().setTypeAnnonCollocation(annonceCollocation.getTypeAnnonCollocation());
            optionalAnnonceCollocation.get().setTypeLogement(annonceCollocation.getTypeLogement());
            optionalAnnonceCollocation.get().setSexe(annonceCollocation.getSexe());
            optionalAnnonceCollocation.get().setStatus(annonceCollocation.isStatus());
            return annonceCollocationRepository.save(optionalAnnonceCollocation.get());
        }
        return null;
    }

    @Override
    public void deleteAnnonceCollocation(int id) {
        annonceCollocationRepository.deleteById(id);
    }

    @Override
    public AnnonceCollocation getAnnonceCollocationById(int id) {
        return annonceCollocationRepository.findById(id).orElse(null);
    }
    @Override
    public Page<AnnonceCollocation> getAllAnnoncesCollocationOrderByMontantContrubitionAsc(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return annonceCollocationRepository.findAllByOrderByMontantContrubitionAsc(pageRequest);
    }
    @Override
    public List<AnnonceCollocation> getAllAnnonceCollocation() {
        return annonceCollocationRepository.findAll();
    }

    @Override
    public List<AnnonceCollocation> getAnnonceCollocationByUser(int id_user) {
        User user = userRepository.findById(id_user).orElse(null);
        return annonceCollocationRepository.findByUser(user);
    }
    @Override
    public void SendRequestMeet(int id,String message){
        Optional<User>optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            String Mmessage="User : " +optionalUser.get().getUsername()+" Have send you a new request meet   :"+message;
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message sms = Message.creator(
                            new PhoneNumber("+21651513940"),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            Mmessage)
                    .create();

        }
    }

    public void sendEmailtoAllUsers(String emailUser,String url) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ahmedm2kki67@gmail.com");
        message.setTo(emailUser);
        message.setSubject("CoCo - Check our new Announcement");
        String emailBody = """
    Hello Madam/Sir,
    We are pleased to announce that a new announcement has been published, do not miss the opportunity.\n"""+
                url+"\n"+
    "Kind regards,\n" +"CoCo Team";
        message.setText(emailBody);
        javaMailSender.send(message);
    }
}
