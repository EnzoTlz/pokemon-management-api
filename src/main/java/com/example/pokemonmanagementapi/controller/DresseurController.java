package com.example.pokemonmanagementapi.controller;

import com.example.pokemonmanagementapi.services.IDresseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dresseurs")
public class DresseurController {

    @Autowired
    IDresseurService service;

    @GetMapping("{id}")
    public ResponseEntity<Object> getDresseur(@PathVariable Long id) {
        if (!service.exist(id)) {
            return new ResponseEntity<>("Dresseur n'existe pas", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.getDresseurDto(id), HttpStatus.OK);
    }
}