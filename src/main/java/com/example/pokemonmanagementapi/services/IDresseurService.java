package com.example.pokemonmanagementapi.services;

public interface IDresseurService {

    int calculerNiveauDresseur(Long dresseurId);

    boolean exist(Long id);
}