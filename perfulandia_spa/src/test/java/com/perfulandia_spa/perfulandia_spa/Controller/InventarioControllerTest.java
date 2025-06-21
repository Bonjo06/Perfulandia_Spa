package com.perfulandia_spa.perfulandia_spa.Controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.perfulandia_spa.perfulandia_spa.Model.Inventario;
import com.perfulandia_spa.perfulandia_spa.Service.InventarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        inventario.setNombreProducto("Producto de prueba");
        inventario.setStock(100);
    }

    @Test
    public void testGetAllInventarios() throws Exception {
        when(inventarioService.findAll()).thenReturn(List.of(inventario));

        mockMvc.perform(get("/Test/inventarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$[0].stock").value(100));
    }
    @Test
    public void testGetInventarioById() throws Exception {
        when(inventarioService.findById(1L)).thenReturn(inventario);

        mockMvc.perform(get("/Test/inventarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$.stock").value(100));
    }
    @Test
    public void testCreateInventario() throws Exception {
        when(inventarioService.save(any(Inventario.class))).thenReturn(inventario);

        mockMvc.perform(post("/Test/inventarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$.stock").value(100));
    }
    @Test
    public void testUpdateInventario() throws Exception {
        when(inventarioService.save(any(Inventario.class))).thenReturn(inventario);

        mockMvc.perform(put("/Test/inventarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$.stock").value(100));
    }  
    @Test
    public void testDeleteInventario() throws Exception {
        doNothing().when(inventarioService).delete(1L);

        mockMvc.perform(delete("/Test/inventarios/1"))
                .andExpect(status().isNoContent());

        verify(inventarioService, times(1)).delete(1L);
    }
}
