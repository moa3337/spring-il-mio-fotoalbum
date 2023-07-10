package com.example.springphotogallery.controller;

import com.example.springphotogallery.model.Category;
import com.example.springphotogallery.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String list(Model model, @RequestParam("edit") Optional<Category> categoryId) {
        // Recupero da db tutte le categorie
        List<Category> categoryList = categoryRepository.findAll();
        // Passo al model un attributo category con tutte le categorie
        model.addAttribute("categories", categoryList);
        // Passo al model un attributo categoryObj per mappare il form sun un oggetto Category
        Category categoryObj;
        // Se ho il parametro categoryId allora cerco la categoria su db
        if (categoryId.isPresent()) {
            Optional<Category> categoryDb = categoryRepository.findById(categoryId.get().getId());
            if (categoryDb.isPresent()) {
                categoryObj = categoryDb.get();
            } else {
                categoryObj = new Category();
            }
        } else {
            categoryObj = new Category();
        }
        model.addAttribute("categoryObj", categoryObj);
        return "/categories/list";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("categoryObj") Category formCategory,
                       BindingResult bindingResult, Model model) {
        // Verifico se ci sono errori
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "/categories/list";
        }
        // Salvo la categoria
        categoryRepository.save(formCategory);
        // Redirect alla lista
        return "redirect:/categories";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:/categories";
    }
}
