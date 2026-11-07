package com.example.pokemonmanagementapi.services;

import com.example.pokemonmanagementapi.entities.DresseurEntity;
import com.example.pokemonmanagementapi.entities.PokemonEntity;
import com.example.pokemonmanagementapi.repositories.DresseurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DresseurServiceTest {

    @Mock
    private DresseurRepository repository;

    @InjectMocks
    private DresseurService service;

    private PokemonEntity creerPokemon(String nom, String type, int niveau) {
        PokemonEntity pokemon = new PokemonEntity();
        pokemon.setNom(nom);
        pokemon.setType(type);
        pokemon.setNiveau(niveau);
        return pokemon;
    }

    private DresseurEntity creerDresseur(List<PokemonEntity> pokemons) {
        DresseurEntity dresseur = new DresseurEntity();
        dresseur.setId(1L);
        dresseur.setNom("Sacha");
        dresseur.setPokemons(pokemons);
        return dresseur;
    }

    @Test
    void should_return_sum_of_levels_when_no_bonus_applies() {
        // Arrange
        List<PokemonEntity> pokemons = new ArrayList<>();
        pokemons.add(creerPokemon("Salameche", "Feu", 20));
        pokemons.add(creerPokemon("Carapuce", "Eau", 30));
        when(repository.findById(1L)).thenReturn(Optional.of(creerDresseur(pokemons)));

        // Act
        int niveau = service.calculerNiveauDresseur(1L);

        // Assert
        assertEquals(50, niveau);
    }

    @Test
    void should_return_1_when_dresseur_has_no_pokemon() {
        // Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(creerDresseur(new ArrayList<>())));

        // Act
        int niveau = service.calculerNiveauDresseur(1L);

        // Assert
        assertEquals(1, niveau);
    }

    @Test
    void should_apply_diversity_bonus_when_dresseur_has_3_different_types() {
        // Arrange
        List<PokemonEntity> pokemons = new ArrayList<>();
        pokemons.add(creerPokemon("Salameche", "Feu", 10));
        pokemons.add(creerPokemon("Carapuce", "Eau", 20));
        pokemons.add(creerPokemon("Bulbizarre", "Plante", 30));
        when(repository.findById(1L)).thenReturn(Optional.of(creerDresseur(pokemons)));

        // Act
        int niveau = service.calculerNiveauDresseur(1L);

        // Assert
        assertEquals(80, niveau); // 60 + 20
    }

    @Test
    void should_not_apply_diversity_bonus_when_dresseur_has_only_2_types() {
        // Arrange
        List<PokemonEntity> pokemons = new ArrayList<>();
        pokemons.add(creerPokemon("Salameche", "Feu", 10));
        pokemons.add(creerPokemon("Goupix", "Feu", 20));
        pokemons.add(creerPokemon("Carapuce", "Eau", 30));
        when(repository.findById(1L)).thenReturn(Optional.of(creerDresseur(pokemons)));

        // Act
        int niveau = service.calculerNiveauDresseur(1L);

        // Assert
        assertEquals(60, niveau);
    }

    @Test
    void should_apply_elite_bonus_when_pokemon_level_is_50_or_more() {
        // Arrange
        List<PokemonEntity> pokemons = new ArrayList<>();
        pokemons.add(creerPokemon("Dracaufeu", "Feu", 50));
        pokemons.add(creerPokemon("Arcanin", "Feu", 60));
        when(repository.findById(1L)).thenReturn(Optional.of(creerDresseur(pokemons)));

        // Act
        int niveau = service.calculerNiveauDresseur(1L);

        // Assert
        assertEquals(130, niveau); // 110 + 10 + 10
    }

    @Test
    void should_cumulate_all_bonuses_when_elite_pokemon_and_3_different_types() {
        // Arrange
        List<PokemonEntity> pokemons = new ArrayList<>();
        pokemons.add(creerPokemon("Pikachu", "Electrik", 55));
        pokemons.add(creerPokemon("Salameche", "Feu", 20));
        pokemons.add(creerPokemon("Carapuce", "Eau", 30));
        when(repository.findById(1L)).thenReturn(Optional.of(creerDresseur(pokemons)));

        // Act
        int niveau = service.calculerNiveauDresseur(1L);

        // Assert
        assertEquals(135, niveau); // 105 + 10 (élite) + 20 (diversité)
    }

    @Test
    void should_throw_exception_when_dresseur_does_not_exist() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.calculerNiveauDresseur(99L));
    }
}