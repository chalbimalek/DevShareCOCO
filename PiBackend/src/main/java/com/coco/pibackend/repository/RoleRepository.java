package com.coco.pibackend.repository;

import com.coco.pibackend.Entity.ERole;
import com.coco.pibackend.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Optional<Role> findByName (ERole name);
    boolean existsByName(ERole r1);
}
