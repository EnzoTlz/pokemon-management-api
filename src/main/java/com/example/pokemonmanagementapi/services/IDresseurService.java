package com.example.pokemonmanagementapi.services;

import com.example.pokemonmanagementapi.dto.DresseurDto;

public interface IDresseurService {

    int calculerNiveauDresseur(Long dresseurId);

    boolean exist(Long id);

    DresseurDto getDresseurDto(Long id);
}