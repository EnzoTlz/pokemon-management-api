package com.example.pokemonmanagementapi.services;

import com.example.pokemonmanagementapi.entities.DresseurEntity;
import com.example.pokemonmanagementapi.entities.PokemonEntity;
import com.example.pokemonmanagementapi.repositories.DresseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DresseurService implements IDresseurService {

    @Autowired
    DresseurRepository repository;

    @Override
    public int calculerNiveauDresseur(Long dresseurId) {
        DresseurEntity dresseur = repository.findById(dresseurId)
                .orElseThrow(() -> new IllegalArgumentException("Dresseur introuvable"));

        List<PokemonEntity> pokemons = dresseur.getPokemons();

        // Cas limite : aucun Pokémon
        if (pokemons.isEmpty()) {
            return 1;
        }

        int niveau = 0;
        Set<String> types = new HashSet<>();

        for (PokemonEntity pokemon : pokemons) {
            // Base : somme des niveaux
            niveau += pokemon.getNiveau();

            // Bonus élite : +10 par Pokémon de niveau >= 50
            if (pokemon.getNiveau() >= 50) {
                niveau += 10;
            }

            types.add(pokemon.getType());
        }

        // Bonus diversité : +20 si au moins 3 types distincts
        if (types.size() >= 3) {
            niveau += 20;
        }

        return niveau;
    }

    @Override
    public boolean exist(Long id) {
        return repository.existsById(id);
    }
}