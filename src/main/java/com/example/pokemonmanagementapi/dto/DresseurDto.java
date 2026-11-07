package com.example.pokemonmanagementapi.dto;

public class DresseurDto {

    private String nomDresseur;
    private int nombrePokemons;
    private int niveauDresseur;

    public String getNomDresseur() {
        return nomDresseur;
    }

    public void setNomDresseur(String nomDresseur) {
        this.nomDresseur = nomDresseur;
    }

    public int getNombrePokemons() {
        return nombrePokemons;
    }

    public void setNombrePokemons(int nombrePokemons) {
        this.nombrePokemons = nombrePokemons;
    }

    public int getNiveauDresseur() {
        return niveauDresseur;
    }

    public void setNiveauDresseur(int niveauDresseur) {
        this.niveauDresseur = niveauDresseur;
    }
}