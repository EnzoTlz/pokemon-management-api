package com.example.pokemonmanagementapi.repositories;

import com.example.pokemonmanagementapi.entities.DresseurEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class DresseurRepositoryTest {

    @Autowired
    private DresseurRepository repository;

    @Test
    void should_return_2_dresseurs_when_find_all_with_data_sql() {
        // Arrange : jeu de données chargé par data.sql

        // Act
        List<DresseurEntity> dresseurs = repository.findAll();

        // Assert
        assertEquals(2, dresseurs.size());
    }

    @Test
    void should_return_sacha_with_3_pokemons_when_find_by_id_1() {
        // Arrange : jeu de données chargé par data.sql

        // Act
        DresseurEntity sacha = repository.findById(1L).get();

        // Assert
        assertEquals("Sacha", sacha.getNom());
        assertEquals("Kanto", sacha.getRegion());
        assertEquals(3, sacha.getPokemons().size());
        assertEquals("Pikachu", sacha.getPokemons().get(0).getNom());
    }

    @Test
    void should_return_ondine_without_pokemon_when_find_by_id_2() {
        // Arrange : jeu de données chargé par data.sql

        // Act
        DresseurEntity ondine = repository.findById(2L).get();

        // Assert
        assertEquals("Ondine", ondine.getNom());
        assertEquals(0, ondine.getPokemons().size());
    }

    @Test
    void should_return_empty_when_dresseur_does_not_exist() {
        // Arrange : aucun dresseur avec l'id 99

        // Act
        Optional<DresseurEntity> result = repository.findById(99L);

        // Assert
        assertEquals(Optional.empty(), result);
    }
}