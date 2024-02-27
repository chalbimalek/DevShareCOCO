package com.coco.pibackend.Service;

import com.coco.pibackend.Entity.Claims;
import com.coco.pibackend.Enum.StatusClaims;
import com.coco.pibackend.Enum.TypeClaim;

import java.util.List;


public interface ClaimService  {
    public String addClaims(StatusClaims status , TypeClaim typeClaim , Claims claims) ;
    public Claims getClaimsById(Integer idClaims);
    public List<Claims> GetClaims() ;

    public Claims rejectClaims(Integer idClaims ) ;
    public String deleteClaims(Integer idClaims );
    public Claims StatusClaims(Integer idClaims , StatusClaims status);

    public List<Claims> GetClaimsWIthStatus(StatusClaims status);
    public List<Claims> GetClaimsWithTypeClaim(TypeClaim typeClaim);


}
