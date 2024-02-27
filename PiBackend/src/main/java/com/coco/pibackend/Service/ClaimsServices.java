package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Claims;
import com.coco.pibackend.Enum.StatusClaims;
import com.coco.pibackend.Enum.TypeClaim;
import com.coco.pibackend.Repo.ClaimsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ClaimsServices implements ClaimService {
    @Autowired
    ClaimsRepository claimsRepository;
    @Override
    public String addClaims(StatusClaims status , TypeClaim typeClaim , Claims claims) {
        if (typeClaim == TypeClaim.Other) {
            String otherDetails = claims.getOtherDetails();
            if (otherDetails == null || otherDetails.isEmpty()) {
                return "Veuillez fournir des détails supplémentaires pour le type de réclamation 'Other'.";
            }
            System.out.println("Détails supplémentaires pour le type 'Other': " + otherDetails);
        }
        claims.setStatusClaims(status);
        claims.setTypeClaim(typeClaim);
        claimsRepository.save(claims) ;
        return " Claim Ajouté ! ";
    }
    @Override
    public Claims getClaimsById(Integer id) {
            Claims getRec =claimsRepository.findById(id).orElse(null);
            return getRec;
        }


    @Override
    public List<Claims> GetClaims() {
        return claimsRepository.findAll();
    }

    // modiofier le statut de la reclamation :
    @Override
   public Claims rejectClaims(Integer idClaims) {
       Claims treatedClaim= claimsRepository.findByIdClaims(idClaims);
        treatedClaim.setStatusClaims(StatusClaims.Rejected);
        return claimsRepository.saveAndFlush(treatedClaim);
    }

     /* public Claims updateClaims(Integer idClaims, Claims claims) {
        return claimsRepository.findById(idClaims)
                .map(c->{
                    c.setStatusClaims(claims.getStatusClaims());
                    return claimsRepository.saveAndFlush(claims);
                }).orElseThrow(()-> new RuntimeException("Claim Not Found ! "));

    }/*
    */

    @Override
    public String deleteClaims(Integer idClaims) {
        claimsRepository.deleteById(idClaims) ;
        return " Claim Deleted ! ";
    }

    @Override
    public Claims StatusClaims(Integer idClaims , StatusClaims status) {
        Claims c = claimsRepository.findById(idClaims).orElse(null);
        if(c != null){
            c.setStatusClaims(status);
            return  claimsRepository.saveAndFlush(c);
        }
        return null;
    }

    @Override
    public List<Claims> GetClaimsWIthStatus(StatusClaims status) {
        List<Claims> cc = claimsRepository.findAll();
        List<Claims> ccc = new ArrayList<>();
        for (Claims c : cc
             )
        {
            if(c.getStatusClaims().equals(status))
                ccc.add(c);
        }
        return ccc;
    }

    @Override
    public List<Claims> GetClaimsWithTypeClaim(TypeClaim typeClaim) {
        List<Claims> cc = claimsRepository.findAll();
        List<Claims> ccc = new ArrayList<>();
        for ( Claims c : cc )
        {
            if ( c.getTypeClaim().equals(typeClaim))
                ccc.add(c);
        }

        return ccc;
    }


}
