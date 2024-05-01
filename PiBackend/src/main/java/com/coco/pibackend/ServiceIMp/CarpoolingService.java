package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.Carpooling;
import com.coco.pibackend.Entity.Notification;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.CarpoolingRepo;
import com.coco.pibackend.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CarpoolingService {

    private final CarpoolingRepo carpoolingRepo;
    private final UserRepo userDao;
    private final NotificationServiceImpl notificationService;
    private final MessageSendingOperations<String> wsTemplate;
    @Transactional
    public Carpooling saveCarpooling(Carpooling carpooling) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDao.findByUsername(username).orElse(null);
        if (user == null) {
            System.out.println("L'utilisateur avec le nom d'utilisateur " + username + " n'a pas été trouvé.");
            return null;
        }

        Carpooling carpooling1 = carpoolingRepo.save(carpooling);
        carpooling1.setUser(user);
        carpooling1.setStatus(false);
        return carpooling1;
    }


    public Carpooling getCarpolingById(int id) {

        return carpoolingRepo.findById(id).orElse(null);
    }

    public List<Carpooling> getAllCarpooling() {
        // return carpoolingRepo.findAllExceptUserCarpoolingsHistory();
        LocalDateTime currentDate = LocalDateTime.now();


        return carpoolingRepo.getCarpooling();
    }


    public void deleteCarpooling(int id) {
        carpoolingRepo.deleteById(id);
    }

    public List<Carpooling> searchbygouvernerat(String Gouvernorat) {
        return carpoolingRepo.getCarpoolingByGouvernorat(Gouvernorat);
    }

    @Transactional
    public void reserverCovoiturage(Integer carpoolingId) {
        // Récupérer l'utilisateur actuellement authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User chercheur = userDao.findByUsername(username).orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));

        // Récupérer le covoiturage par son identifiant
        Carpooling carpooling = carpoolingRepo.findById(carpoolingId).orElseThrow(() -> new IllegalArgumentException("Covoiturage non trouvé"));

        // Vérifier si l'utilisateur ne peut pas réserver son propre covoiturage
        if (chercheur.equals(carpooling.getUser())) {
            throw new IllegalStateException("Vous ne pouvez pas réserver votre propre covoiturage");
        }

        // Vérifier si l'utilisateur a déjà réservé ce covoiturage
        List<User> chercheurs = carpooling.getChercheurs();
        if (chercheurs.stream().anyMatch(u -> u.getId().equals(chercheur.getId()))) {
            throw new IllegalStateException("Vous avez déjà réservé ce covoiturage");
        }


        // Ajouter l'utilisateur à la liste des chercheurs pour ce covoiturage
        chercheurs.add(chercheur);
        carpooling.setChercheurs(chercheurs); // Mettre à jour la liste des chercheurs
        carpoolingRepo.save(carpooling);
        System.out.println("jjjjj "
                + carpooling.getUser().getId());

        // Envoyer une notification à l'utilisateur annonçant la demande de réservation
        notificationService.envoyerNotification(carpoolingId, "Demande de réservation reçue à ", carpooling.getUser().getId());
        //wsTemplate.convertAndSend("/topic/notification/" ," Demande de réservation reçue à"+  carpooling.getUser().getId());

    }

    @Transactional
    public void accepterOuRefuserCovoiturage(Integer carpoolingId, long userId, boolean accepter) {
        Carpooling carpooling = carpoolingRepo.findById(carpoolingId)
                .orElseThrow(() -> new IllegalArgumentException("Covoiturage non trouvé"));
        User user1 = userDao.findById(userId).orElse(null);

        // Vérifier si l'utilisateur est l'un des chercheurs pour ce covoiturage
        boolean userFound = false;
        for (User chercheur : carpooling.getChercheurs()) {

            System.out.println("chercheur " + chercheur.getId());
            System.out.println("id  " + userId);
            if (chercheur.getId().equals(userId)) {
                Set<User> useracceptee = carpooling.getUtilisateursAcceptes();
                if (useracceptee.stream().anyMatch(u -> u.getId().equals(chercheur.getId()))) {

                    System.out.println("Vous avez déjà acceptee ce user ");
                    throw new IllegalStateException("Vous avez déjà acceptee ce user ");
                }


                userFound = true;
                System.out.println(userFound);

                break;
            }
        }
        if (!userFound) {
            System.out.println(userFound);
            throw new IllegalArgumentException("L'utilisateur spécifié n'est pas un chercheur pour ce covoiturage.");
        }


        if (accepter && carpooling.getNbrPlaceDisponible() > 0) {
            // Accepter la réservation pour l'utilisateur spécifié
            carpooling.setNbrPlaceDisponible(carpooling.getNbrPlaceDisponible() - 1);
            carpooling.getUtilisateursAcceptes().add(user1);
            if (carpooling.getNbrPlaceDisponible() == 0) {
                carpooling.setStatus(true);
                //carpoolingRepo.deleteById(carpoolingId);
                Notification notification1 = notificationService.envoyerNotification(carpoolingId, "Felicitation a toi, tous les places sont pleines !!! ", carpooling.getUser().getId());
                for (Notification notification : carpooling.getNotifications()) {
                    if (notification.getUserEnvoyer().getId().equals(carpooling.getUser().getId())) {
                        notification1.setAcceptee(true);
                        notification.setAcceptee(true);
                        notification.setRefusee(true);
                        notification1.setRefusee(true);

                        //  notification.setRefusee(true);
                    }
                }
            }

            // Mettre à jour les notifications pour l'utilisateur spécifié


            // Enregistrer les modifications dans la base de données
            carpoolingRepo.save(carpooling);

            // Envoyer une notification à l'utilisateur spécifié
            Notification notification1 = notificationService.envoyerNotification(carpoolingId, "Votre réservation avec " + carpooling.getUser().getUsername() + "  a été acceptée à ", userId);
            for (Notification notification : carpooling.getNotifications()) {
                if (notification.getUserEnvoyer().getId().equals(userId)) {
                    notification1.setAcceptee(true);
                    notification.setAcceptee(true);
                    notification.setRefusee(true);
                    notification1.setRefusee(false);

                    //  notification.setRefusee(true);
                }
            }
        } else {
            // Refuser la réservation pour l'utilisateur spécifié


            // Enregistrer les modifications dans la base de données
            carpoolingRepo.save(carpooling);

            // Envoyer une notification à l'utilisateur spécifié
            Notification notification1 = notificationService.envoyerNotification(carpoolingId, "Votre réservation avec " + carpooling.getUser().getUsername() + " a été refusée à ", userId);
            for (Notification notification : carpooling.getNotifications()) {
                if (notification.getUserEnvoyer().getId().equals(userId)) {
                    notification1.setAcceptee(false);
                    notification1.setRefusee(true);
                    notification.setAcceptee(false);
                    notification.setRefusee(true);
                }
            }
        }
    }

    @Transactional
    public void annulerAcceptationCovoiturage(int carpoolingId, long id) {
        Carpooling carpooling = carpoolingRepo.findById(carpoolingId)
                .orElseThrow(() -> new IllegalArgumentException("Covoiturage non trouvé"));
        User user = userDao.findById(id).orElse(null);
        if (carpooling.getUtilisateursAcceptes().contains(user)) {
            // Annuler l'acceptation
            carpooling.setNbrPlaceDisponible(carpooling.getNbrPlaceDisponible() + 1);
            carpooling.getUtilisateursAcceptes().remove(user);
            carpooling.setStatus(false);
            carpoolingRepo.save(carpooling);

            // Envoyer une notification au chercheur
            assert user != null;
            for (Notification notification1 : carpooling.getNotifications()) {
                if (notification1.getUserDestiner().getId().equals(id)) {
                    notification1.setRefusee(true);
                }
            }
            Notification notification = notificationService.envoyerNotification(carpoolingId, user.getUsername() + "  a  annulée la réservation", carpooling.getUser().getId());
            notification.setRefusee(true);
        } else {
            throw new IllegalArgumentException("Vous n'avez pas encore accepté cette réservation.");
        }
    }

    public int nbrdecovoiturage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User annoncer = userDao.findByUsername(username).orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));
        Set<Carpooling> carpoolings = annoncer.getAnnonceCarpoolingSet();
        int gain = 0;
        for (Carpooling carpooling : carpoolings) {
            if (carpooling.getStatus())
                gain++;
        }
        return gain;

    }

    public int calculatePoints() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userDao.findByUsername(username).orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));
        Set<Carpooling> carpoolings = user.getAnnonceCarpoolingSet();
        int totalPoints = 0;
        int totalCarpoolings = carpoolings.size();
        int weekdayCarpoolings = 0;

        // Compter le nombre de covoiturages effectués en semaine
        for (Carpooling carpooling : carpoolings) {
            if (carpooling.getStatus()) {
                weekdayCarpoolings++;
                System.out.println(weekdayCarpoolings);
                totalPoints += weekdayCarpoolings * POINTS_PER_CARPPOOLING;

            }
        }

        // Calculer les points en fonction du nombre de covoiturages en semaine
       /* if (weekdayCarpoolings >= 5) {
            totalPoints += 100;
        }*/
        System.out.println("1 " + totalCarpoolings);
        System.out.println("2 " + totalPoints);


        return totalPoints;
    }

    private static final int POINTS_PER_CARPPOOLING = 10;

    public boolean isWeekdayCarpooling(Carpooling carpooling) {
        LocalDateTime departureDateTime = carpooling.getDateSortie();
        DayOfWeek dayOfWeek = departureDateTime.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    public int calculatePointsPerCarpooling(int totalCarpoolings) {
        // Votre logique pour déterminer le nombre de points par covoiturage
        if (totalCarpoolings >= 5) {
            System.out.println(totalCarpoolings);
            return POINTS_PER_CARPPOOLING * 2; // Double points si le nombre de covoiturages est égal ou supérieur à 5
        } else {
            return POINTS_PER_CARPPOOLING;
        }
    }

}




