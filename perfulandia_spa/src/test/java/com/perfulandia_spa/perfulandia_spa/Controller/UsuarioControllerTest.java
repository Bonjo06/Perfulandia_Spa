package com.perfulandia_spa.perfulandia_spa.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.perfulandia_spa.perfulandia_spa.Model.Usuario;
import com.perfulandia_spa.perfulandia_spa.Service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.annotation.Before;
import org.hibernate.validator.constraints.ModCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

@WebMvcTest(UsuarioService.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setNombres("Elias Miguel");
        usuario.setApellidos("Torres Atencio");
        usuario.setCorreo("mi.torres@catac");
    }
    @Test
    public void testGetAllUsuarios() throws Exception{
        when(usuarioService.findAll()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/Test/usuarios"))
                .andExpect(Status().isOk())
                .andExpect(jsonPath("$nombres").value("Elias Miguel"));
    }

    @Test
    public void testCreateUsuario() throws Exception {
        // Define el comportamiento del mock: cuando se llame a save(), devuelve el objeto 
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        // Realiza una petición POST a /api/estudiantes con el objeto  en formato JSON y verifica que la respuesta sea correcta
        mockMvc.perform(post("/Test/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario))) // Convierte el objeto  a JSON
                .andExpect(Status().isOk())
                .andExpect(jsonPath("$nombres").value("Elias Miguel"));
    }
}
