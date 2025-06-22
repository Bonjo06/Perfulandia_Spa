package com.perfulandia_spa.perfulandia_spa.Controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//import com.perfulandia_spa.perfulandia_spa.Model.Envio;
import com.perfulandia_spa.perfulandia_spa.Model.Inventario;
import com.perfulandia_spa.perfulandia_spa.Service.InventarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
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

        mockMvc.perform(get("/api/v1/inventario/gerente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$[0].stock").value(100));
    }

    @Test
    public void testGetInventarioById() throws Exception {
        when(inventarioService.findById(1L)).thenReturn(inventario);

        mockMvc.perform(get("/api/v1/inventario/gerente/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$.stock").value(100));
    }

    @Test
    public void testCreateInventario() throws Exception {
        when(inventarioService.save(any(Inventario.class))).thenReturn(inventario);

        mockMvc.perform(post("/api/v1/inventario/gerente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$.stock").value(100));
    }

    @Test
    public void testUpdateInventario() throws Exception {
        when(inventarioService.findById(1L)).thenReturn(inventario);
        when(inventarioService.save(any(Inventario.class))).thenReturn(inventario);

        mockMvc.perform(put("/api/v1/inventario/gerente/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreProducto").value("Producto de prueba"))
                .andExpect(jsonPath("$.stock").value(100));
    }  

    @Test
    public void testDeleteInventario() throws Exception {
        doNothing().when(inventarioService).delete(1L);

        mockMvc.perform(delete("/api/v1/inventario/gerente/{id}", 1L))
                .andExpect(status().isOk());

        verify(inventarioService, times(1)).delete(1L);
    }
}
