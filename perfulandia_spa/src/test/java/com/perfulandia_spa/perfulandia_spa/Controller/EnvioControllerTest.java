package com.perfulandia_spa.perfulandia_spa.Controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.perfulandia_spa.perfulandia_spa.Model.Envio;
import com.perfulandia_spa.perfulandia_spa.Service.EnvioService;
import com.perfulandia_spa.perfulandia_spa.Controller.EnvioController;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(EnvioController.class)
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
        envio.setEnvioDireccion(envioDireccion:"Av siempre viva 747");
        envio.setFechaInicio(new Date());
        envio.setFechaTermino(new Date(System.currentTimeMillis() + 3600000));
    }
    @Test
    public void testGetAllEnvios() throws Exception{
        when(envioService.findAll()).thenReturn(List.of(Envio));

        mockMvc.perform(get(urlTemplate:"/Test/Envio/1"));
        
    }
}
