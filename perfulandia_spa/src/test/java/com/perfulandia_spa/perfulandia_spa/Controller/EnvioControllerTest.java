package com.perfulandia_spa.perfulandia_spa.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.perfulandia_spa.perfulandia_spa.Model.Envio;
import com.perfulandia_spa.perfulandia_spa.Service.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(EnvioControllerTest.class)
@AutoConfigureMockMvc(addFilters = false)
public class EnvioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioService envioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Envio envio;

    @BeforeEach
    void setUp() {
        envio = new Envio();
        envio.setEnvioDireccion("Av siempre viva 747");
        envio.setFechaInicio(new java.sql.Date(System.currentTimeMillis()));
        envio.setFechaTermino(new java.sql.Date(System.currentTimeMillis() + 3600000));
    }
    @Test
    public void testGetAllEnvios() throws Exception{
        when(envioService.findAll()).thenReturn(List.of(envio));

        mockMvc.perform(get("/Test/Envio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.envioDireccion").value(envio.getEnvioDireccion()))
                .andExpect(jsonPath("$.fechaInicio").value(envio.getFechaInicio()))
                .andExpect(jsonPath("$.fechaTermino").value(envio.getFechaTermino()));
    }
    @Test
    public void testGetEnvioById() throws Exception {
        when(envioService.findById(1L)).thenReturn(envio);

        mockMvc.perform(get("/Test/Envio/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.envioDireccion").value(envio.getEnvioDireccion()))
                .andExpect(jsonPath("$.fechaInicio").value(envio.getFechaInicio()))
                .andExpect(jsonPath("$.fechaTermino").value(envio.getFechaTermino()));
    }
    @Test
    public void testCreateEnvio() throws Exception {
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(post("/Test/Envio")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.envioDireccion").value(envio.getEnvioDireccion()))
                .andExpect(jsonPath("$.fechaInicio").value(envio.getFechaInicio()))
                .andExpect(jsonPath("$.fechaTermino").value(envio.getFechaTermino()));
    }
    @Test
    public void testUpdateEnvio() throws Exception {
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(put("/Test/Envio/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.envioDireccion").value(envio.getEnvioDireccion()))
                .andExpect(jsonPath("$.fechaInicio").value(envio.getFechaInicio()))
                .andExpect(jsonPath("$.fechaTermino").value(envio.getFechaTermino()));
    }
    @Test
    public void testDeleteEnvio() throws Exception {
        doNothing().when(envioService).delete(1L);

        mockMvc.perform(delete("/Test/Envio/1"))
                .andExpect(status().isOk());

        verify(envioService, times(1)).delete(1L);
    }
}
