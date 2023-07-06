package com.example.springphotogallery.repository;

import com.example.springphotogallery.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    // Metodo per cercare le pizze tramite nome
    List<Photo> findByTitle(String title);
}
