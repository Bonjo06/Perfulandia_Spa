package com.perfulandia_spa.perfulandia_spa.Controller;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(SucursalController.class)
public class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
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

        mockMvc.perform(get("/Test/sucursales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testGetSucursalById() throws Exception {
        when(sucursalService.findById(1L)).thenReturn(sucursal);

        mockMvc.perform(get("/Test/sucursales/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testCreateSucursal() throws Exception {
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(post("/Test/sucursales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testUpdateSucursal() throws Exception {
        when(sucursalService.save(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(put("/Test/sucursales/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sucDireccion").value("Direccion Prueba"));
    }

    @Test
    public void testDeleteSucursal() throws Exception {
        doNothing().when(sucursalService).delete(1L);

        mockMvc.perform(delete("/Test/sucursales/1"))
                .andExpect(status().isNoContent());

        verify(sucursalService, times(1)).delete(1L);
    }
}
