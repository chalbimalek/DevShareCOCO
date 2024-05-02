package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Entity.RendezVous;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.AnnonceCollocationRepository;
import com.coco.pibackend.Repo.RendezVousRepo;
import com.coco.pibackend.Repo.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RendezVousService {
    private final String ACCOUNT_SID = "ACe3aa4b575988dc66fc0f0d64843152c9";
    private final String AUTH_TOKEN = "d9e44bb5ceffd39dedd2d8d36b00a2a8";
    private final String TWILIO_PHONE_NUMBER = "+12513134381";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnonceCollocationRepository annonceCollocationRepository;
    @Autowired
    private RendezVousRepo rendezVousRepo;


    public RendezVous Add(RendezVous r,Integer idu,Integer idc){
        Optional<User>optionalUser=userRepository.findById(idu);
        Optional<AnnonceCollocation>annonceCollocation=annonceCollocationRepository.findById(idc);
        if (optionalUser.isPresent() && annonceCollocation.isPresent()){
            r.setStatus(false);
            r.setAnnonceCollocation(annonceCollocation.get());
            r.setOwner(annonceCollocation.get().getUser());
            r.setClient(optionalUser.get());
            String Mmessage="User : " +r.getClient().getUsername()+" Have send your request meet";
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message sms = Message.creator(
                            new PhoneNumber("+21651513940"),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            Mmessage)
                    .create();
            return rendezVousRepo.save(r);
        }
        return null;
    }
    public RendezVous accaepte(Integer id){
        Optional<RendezVous>rendezVous=rendezVousRepo.findById(id);
        if(rendezVous.isPresent()){
            rendezVous.get().setStatus(true);
            String Mmessage="User : " +rendezVous.get().getOwner().getUsername()+" Have accapte your  request meet";
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message sms = Message.creator(
                            new PhoneNumber("+21651513940"),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            Mmessage)
                    .create();
            return rendezVousRepo.save(rendezVous.get());
        }
        return null;
    }
    public void delteRendezvous(Integer id){
        Optional<RendezVous>rendezVous=rendezVousRepo.findById(id);
        if(rendezVous.isPresent()){
            String Mmessage="User : " +rendezVous.get().getOwner().getUsername()+" Have refuse your  request meet";
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message sms = Message.creator(
                            new PhoneNumber("+21651513940"),
                            new PhoneNumber(TWILIO_PHONE_NUMBER),
                            Mmessage)
                    .create();
            rendezVousRepo.deleteById(id);
        }

    }
    public void delte(Integer id){
        Optional<RendezVous>rendezVous=rendezVousRepo.findById(id);
        if(rendezVous.isPresent()){
            rendezVousRepo.deleteById(id);
        }

    }
    public List<RendezVous>findbyannoance(Integer id){
        Optional<AnnonceCollocation>annonceCollocation=annonceCollocationRepository.findById(id);
        if(annonceCollocation.isPresent()){
            return rendezVousRepo.findByAnnonceCollocation(annonceCollocation.get());
        }
        return null;
    }
    public List<RendezVous>findbyOwnerOrclient(Integer id){
        Optional<User>User=userRepository.findById(id);
        if(User.isPresent()){
            return rendezVousRepo.findActiveRendezVousByUser(User.get());
        }
        return null;
    }

    public List<RendezVous>findbyOwner(Integer id){
        Optional<User>User=userRepository.findById(id);
        if(User.isPresent()){
            return rendezVousRepo.findByOwnerAndStatus(User.get(),false);
        }
        return null;
    }
}
