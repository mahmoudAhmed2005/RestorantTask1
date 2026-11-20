package com.restorant_springdemo.restorant.controller;

import com.restorant_springdemo.restorant.dto.ChefDto;
import com.restorant_springdemo.restorant.model.Chef;
import com.restorant_springdemo.restorant.repo.ChefRepo;
import com.restorant_springdemo.restorant.service.ChefService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/chefs")

public class ChefController {
    private ChefService chefService;

    @Autowired
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    @GetMapping
    public List<ChefDto>getAllChef(){
        return chefService.getAllChef();
    }

    @GetMapping("/{id}")

    public ChefDto getChefById(@Valid @PathVariable Long id) throws SystemException {
        return chefService.getChefById(id);
    }


    @PostMapping
    public ChefDto saveChef(@Valid @RequestBody ChefDto chefDto) throws SystemException {
        return chefService.saveChef(chefDto);
    }

    @PutMapping
    private ChefDto updateChef(@Valid @RequestBody ChefDto chefDto) throws SystemException {
        return chefService.updateChef(chefDto);
    }

    @DeleteMapping
    public void deleteChefById(@Valid @RequestParam Long id) throws SystemException {
        chefService.deleteChefById(id);
    }
}
