package com.coco.pibackend.Controller;

import com.coco.pibackend.Entity.Claims;
import com.coco.pibackend.Enum.StatusClaims;
import com.coco.pibackend.Enum.TypeClaim;
import com.coco.pibackend.Service.ClaimsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    public class ClaimsController {
    @Autowired
    ClaimsServices claimService;
    @PostMapping("/addClaim/{Status}/{typeClaim}")
    public String AddClaim(@PathVariable StatusClaims Status ,@PathVariable TypeClaim typeClaim ,@RequestBody Claims claims) {

        return claimService.addClaims(Status , typeClaim ,claims);

    }
    @GetMapping("/GetALLClaims")
    public List<Claims> GetALLClaims(){
        return claimService.GetClaims();
    }


    @GetMapping("/GetClaimsById/{idClaims}")
    public  Claims GetClaimsById(@PathVariable Integer idClaims){
        return claimService.getClaimsById(idClaims);
    }
    
    @PutMapping("/rejectClaims/{idClaims}")
    public Claims RejectClaims(@PathVariable Integer idClaims) {
        return claimService.rejectClaims(idClaims);
    }
    @DeleteMapping("/DeleteClaims/{idClaims}")
    public String Delete(@PathVariable Integer idClaims){
        return claimService.deleteClaims( idClaims);
    }

    @PutMapping("/UpdateStatus/{idClaims}/{status}")
    public Claims UpdateClaims(@PathVariable Integer idClaims,@PathVariable StatusClaims status) {
      return  claimService.StatusClaims(idClaims, status);

    }

    @GetMapping("/GetClaimsByStatus/{Status}")
    public  List<Claims> GetClaimsById(@PathVariable StatusClaims Status){
        return claimService.GetClaimsWIthStatus(Status);
    }
    @GetMapping("/GetClaimsByType/{typeClaim}")
    public  List<Claims> GetClaimsWithTypeClaim(@PathVariable TypeClaim typeClaim){
        return claimService.GetClaimsWithTypeClaim(typeClaim);
    }


}

