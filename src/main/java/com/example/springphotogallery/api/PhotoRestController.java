package com.example.springphotogallery.api;

import com.example.springphotogallery.model.Photo;
import com.example.springphotogallery.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/v1/photos")
public class PhotoRestController {
    // Controller per la risorsa pizza
    @Autowired
    private PhotoRepository photoRepository;

    // Servizio per avere la lista delle pizze
    @GetMapping
    public List<Photo> index() {
        return photoRepository.findAll(Sort.by("title"));
    }

    // Servizio per il dettaglio della singola pizza
    @GetMapping("/{id}")
    public Photo get(@PathVariable Integer id) {
        // Cerco la pizza per id su db
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()) {
            return photo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // Servizio per creare una nuova photo
    @PostMapping
    public Photo create(@RequestBody Photo photo) {
        return photoRepository.save(photo);
    }

    // Servizio per cancellare una nuova photo
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        photoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Photo update(@PathVariable Integer id, @RequestBody Photo photo) {
        photo.setId(id);
        return photoRepository.save(photo);
    }

    @GetMapping("/page")
    public Page<Photo> page(
            /*@RequestParam(defaultValue = "4") Integer size,
            @RequestParam(defaultValue = "0") Integer page*/
            Pageable pageable) {
        // Creo un pageable a partire da size e page
        // Pageable pageable = PageRequest.of(page, size);
        // Restituisco una page da db dal metodo findAll
        return photoRepository.findAll(pageable);
    }
}
