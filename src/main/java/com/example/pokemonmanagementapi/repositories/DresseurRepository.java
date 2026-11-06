package com.example.pokemonmanagementapi.repositories;

import com.example.pokemonmanagementapi.entities.DresseurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DresseurRepository extends JpaRepository<DresseurEntity, Long> {
}

