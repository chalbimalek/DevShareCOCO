package com.coco.pibackend.ServiceIMp;


import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }
}
