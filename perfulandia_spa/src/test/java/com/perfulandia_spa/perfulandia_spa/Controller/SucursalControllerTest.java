package com.perfulandia_spa.perfulandia_spa.Controller;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;
import com.perfulandia_spa.perfulandia_spa.Service.SucursalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

//Clase para probar el controlador SucursalController
@WebMvcTest(SucursalController.class)
public class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean
    private SucursalService sucursalService;

    @Autowired
    private ObjectMapper objectMapper;

    private Sucursal sucursal;

    @BeforeEach
    void setUp() {
        sucursal = new Sucursal();
        sucursal.setSucDireccion("Direccion Prueba");
    }

    @Test
    public void testGetAllSucursales() throws Exception {
        when(sucursalService.findAll()).thenReturn(List.of(sucursal));

        mockMvc.perform(get("/api/v1/gerente/sucursal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testGetSucursalById() throws Exception {
        when(sucursalService.findById(1L)).thenReturn(sucursal);

        mockMvc.perform(get("/api/v1/gerente/sucursal/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testCreateSucursal() throws Exception {
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(post("/api/v1/gerente/sucursal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testUpdateSucursal() throws Exception {
        when(sucursalService.findById(1L)).thenReturn(sucursal);
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(put("/api/v1/gerente/sucursal/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testDeleteSucursal() throws Exception {
        doNothing().when(sucursalService).delete(1L);

        mockMvc.perform(delete("/api/v1/gerente/sucursal/{id}", 1L))
                .andExpect(status().isOk());

        verify(sucursalService, times(1)).delete(1L);
    }
}
