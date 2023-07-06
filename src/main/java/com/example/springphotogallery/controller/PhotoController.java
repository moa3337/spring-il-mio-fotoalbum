package com.example.springphotogallery.controller;

import com.example.springphotogallery.model.Photo;
import com.example.springphotogallery.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    // Questo dipende da PizzaRepository
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String index(Model model) {
        // Recupero la lista di pizze dal db
        List<Photo> photos = photoRepository.findAll();
        // Passo la lista di libri alla view
        model.addAttribute("photoList", photos);
        return "/photos/index";
    }

}
