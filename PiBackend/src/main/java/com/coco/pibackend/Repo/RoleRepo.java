package com.coco.pibackend.Repo;


import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Enum.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,String> {
    Optional<Role> findByName (ERole name);
    boolean existsByName(ERole r1);
}
