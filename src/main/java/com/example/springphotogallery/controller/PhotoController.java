package com.example.springphotogallery.controller;

import com.example.springphotogallery.model.Photo;
import com.example.springphotogallery.repository.PhotoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/***
 * INDEX *
 ***/
@Controller
@RequestMapping("/photos")
public class PhotoController {
    // Questo dipende da PhotoRepository
    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String list(
            @RequestParam(name = "keyword", required = false) String searchString,
            Model model) {
        List<Photo> photos;
        if (searchString == null || searchString.isBlank()) {
            // Se non ho il parametro RequestParam faccio la query con filtro
            // Recupero la lista di photos dal db
            photos = photoRepository.findAll();
        } else {
            // Se ho il parametro RequestParam faccio la query con filtro
            photos = photoRepository.findByTitle(searchString);
        }

        // Passo la lista di photos alla view
        model.addAttribute("photoList", photos);
        return "/photos/index";
    }

    /***
     * SHOW *
     ***/
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Integer photoId, Model model) {
        Photo photo = getPhotoById(photoId);
        // Passo la photo alla view
        model.addAttribute("photo", photo);
        // Ritorno il nome del template della view
        return "/photos/detail";
    }

    // Controller che ritorna il form per creazione di photo
    @GetMapping("/create")
    public String create(Model model) {
        // Aggiungi al model un attributo photo contenente una photo vuota
        model.addAttribute("photo", new Photo());
        return "/photos/edit"; // Ritorno il form unico create/edit
    }

    /***
     * CREATE *
     ***/
    // Controller per gestire la post del form con i dati di photo
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult) {
        // Imposta il valore di visible in base alla checkbox
        formPhoto.setVisible(formPhoto.getVisible() != null && formPhoto.getVisible());
        // I dati di photo sono dentro all'oggetto formPhoto
        // Verifico in validazione se ci sono stati errori
        if (bindingResult.hasErrors()) {
            // Se ci sono stati errori
            //return "/photos/edit"; // Ritorno il form unico create/edit
            return "/photos/edit"; // Ritorno il form unico create/edit
        }
        // Setto il timestamp di creazione
        formPhoto.setCreatedAt(LocalDateTime.now());
        // Persisto formPhoto su db
        photoRepository.save(formPhoto);
        // Se tutto va bene rimando alla lista delle pizze
        return "redirect:/photos";
    }

    /***
     * UPDDATE *
     ***/
    // Metodo per l'edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // Verifico se esiste quel photo id
        Photo photo = getPhotoById(id);
        // Cerco i dati di quella photo sul db
        // Aggiungo la photo al model
        model.addAttribute("photo", photo);
        // Ritorno il template form edit
        return "/photos/edit";
    }

    @PostMapping("/edit/{id}")
    public String doEdit(
            @PathVariable Integer id,
            @Valid @ModelAttribute("photo") Photo formPhoto,
            BindingResult bindingResult
    ) {
        // Imposta il valore di visible in base alla checkbox
        formPhoto.setVisible(formPhoto.getVisible() != null && formPhoto.getVisible());
        // Cerco la photoa per id
        Photo photoToEdit = getPhotoById(id); // Vecchia versione di photo
        // La nuova verisone di photoa è formPhotoa
        // Valido il
        if (bindingResult.hasErrors()) {
            // Se ci sono errori ritorno il form
            return "/photos/edit";
        }
        // Trasferisco su formPhoto i valori dei campi non presenti nel form
        formPhoto.setId(photoToEdit.getId());
        formPhoto.setCreatedAt(photoToEdit.getCreatedAt());
        // Salvo idati
        photoRepository.save(formPhoto);
        return "redirect:/photos";
    }

    /***
     * DELETE *
     ***/
    // Metodo per la delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // Verifo che esista quel id photo
        Photo photoToDelete = getPhotoById(id);
        // Lo cancello
        photoRepository.delete(photoToDelete);
        // Aggiungo messaggio successo come flashAttribute
        redirectAttributes.addFlashAttribute(
                "message",
                "Foto" + photoToDelete.getTitle() + "eliminata con successo"
        );
        // Redirect alla lista pizze
        return "redirect:/photos";
    }

    // Metodo getPhotoById
    private Photo getPhotoById(Integer id) {
        // Verifico se esiste quel photo id
        Optional<Photo> result = photoRepository.findById(id);
        // Se no ritorno un error 404
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "foto con id " + id + "non trovata");
        }
        return result.get();
    }
}
