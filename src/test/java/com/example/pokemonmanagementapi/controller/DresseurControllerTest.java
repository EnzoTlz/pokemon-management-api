package com.example.pokemonmanagementapi.controller;

import com.example.pokemonmanagementapi.dto.DresseurDto;
import com.example.pokemonmanagementapi.services.IDresseurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DresseurController.class)
class DresseurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDresseurService service;

    @Test
    void should_return_200_and_dto_when_dresseur_exists() throws Exception {
        // Arrange
        DresseurDto dto = new DresseurDto();
        dto.setNomDresseur("Sacha");
        dto.setNombrePokemons(3);
        dto.setNiveauDresseur(135);

        when(service.exist(1L)).thenReturn(true);
        when(service.getDresseurDto(1L)).thenReturn(dto);

        // Act & Assert
        mockMvc.perform(get("/dresseurs/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomDresseur").value("Sacha"))
                .andExpect(jsonPath("$.nombrePokemons").value(3))
                .andExpect(jsonPath("$.niveauDresseur").value(135));
    }

    @Test
    void should_return_404_when_dresseur_does_not_exist() throws Exception {
        // Arrange
        when(service.exist(99L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(get("/dresseurs/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Dresseur n'existe pas"));
    }
}