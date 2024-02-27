package com.coco.pibackend.ServiceIMp;

import com.coco.pibackend.Entity.AnnonceCollocation;
import com.coco.pibackend.Entity.User;
import com.coco.pibackend.Repo.AnnonceCollocationRepository;
import com.coco.pibackend.Repo.UserRepository;
import com.coco.pibackend.Service.AnnonceCollocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnoncecollocationImpl implements AnnonceCollocationService {

    private final AnnonceCollocationRepository annonceCollocationRepository;
    private  final UserRepository userRepository;
    @Override
    public AnnonceCollocation addAnnonceCollocation(AnnonceCollocation annonceCollocation) {

        return annonceCollocationRepository.save(annonceCollocation);
    }

    @Override
    public AnnonceCollocation updateAnnonceCollocation(AnnonceCollocation annonceCollocation) {

        return annonceCollocationRepository.save(annonceCollocation);
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
    public List<AnnonceCollocation> getAllAnnonceCollocation() {
        return annonceCollocationRepository.findAll();
    }

    @Override
    public List<AnnonceCollocation> getAnnonceCollocationByUser(int id_user) {
        User user = userRepository.findById(id_user).orElse(null);
        return annonceCollocationRepository.findByUser(user);
    }
}
