package com.coco.pibackend;

import com.coco.pibackend.Entity.Role;
import com.coco.pibackend.Enum.ERole;
import com.coco.pibackend.Repo.RoleRepo;
import com.coco.pibackend.ServiceIMp.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class PiBackendApplication {
    @Autowired
    RoleRepo roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(PiBackendApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    @Bean
    CommandLineRunner start() {
        return args -> {

            for (ERole r1 : ERole.values()
            ) {

                if (!roleRepository.existsByName(r1)) {
                    Role r = new Role(r1);
                    roleRepository.save(r);
                }
            }

        };
    }

}
