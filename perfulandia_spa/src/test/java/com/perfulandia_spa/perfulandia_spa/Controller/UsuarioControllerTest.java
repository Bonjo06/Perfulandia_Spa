package com.perfulandia_spa.perfulandia_spa.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.perfulandia_spa.perfulandia_spa.Model.Usuario;
import com.perfulandia_spa.perfulandia_spa.Service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;


//Clase para probar el controlador UsuarioController
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
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
        usuario.setUserDireccion("Av España 123");
    }

    @Test
    public void testGetAllUsuarios() throws Exception{
        when(usuarioService.findAll()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/v1/admin/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombres").value("Elias Miguel"))
                .andExpect(jsonPath("$[0].apellidos").value("Torres Atencio"))
                .andExpect(jsonPath("$[0].correo").value("mi.torres@catac"))
                .andExpect(jsonPath("$[0].userDireccion").value("Av España 123"));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        when(usuarioService.findById(1L)).thenReturn(usuario);
        mockMvc.perform(get("/api/v1/admin/usuario/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Elias Miguel"))
                .andExpect(jsonPath("$.apellidos").value("Torres Atencio"))
                .andExpect(jsonPath("$.correo").value("mi.torres@catac"))
                .andExpect(jsonPath("$.userDireccion").value("Av España 123"));
    }


    @Test
    public void testCreateUsuario() throws Exception {
        // Define el comportamiento del mock: cuando se llame a save(), devuelve el objeto 
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        // Realiza una petición POST a /Test/usuarios con el objeto  en formato JSON y verifica que la respuesta sea correcta
        mockMvc.perform(post("/api/v1/admin/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario))) // Convierte el objeto  a JSON
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombres").value("Elias Miguel"))
                .andExpect(jsonPath("$.apellidos").value("Torres Atencio"))
                .andExpect(jsonPath("$.correo").value("mi.torres@catac"))
                .andExpect(jsonPath("$.userDireccion").value("Av España 123"));
    }
    @Test
    public void testUpdateUsuario() throws Exception {
        when(usuarioService.findById(1L)).thenReturn(usuario);
        // Define el comportamiento del mock: cuando se llame a save(), devuelve el objeto  actualizado
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        // Realiza una petición PUT a /Test/usuarios con el objeto  en formato JSON y verifica que la respuesta sea correcta
        mockMvc.perform(put("/api/v1/admin/usuario/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario))) // Convierte el objeto  a JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres").value("Elias Miguel"))
                .andExpect(jsonPath("$.apellidos").value("Torres Atencio"))
                .andExpect(jsonPath("$.correo").value("mi.torres@catac"));
    }
    @Test
    public void testDeleteUsuario() throws Exception {
        // Define el comportamiento del mock: cuando se llame a delete(), no hace nada
        doNothing().when(usuarioService).delete(1L);

        // Realiza una petición DELETE a /Test/usuarios/1 y verifica que la respuesta sea correcta
        mockMvc.perform(delete("/api/v1/admin/usuario/{id}", 1L))
                .andExpect(status().isOk());

        // Verifica que el método delete() fue llamado una vez con el ID 1
        verify(usuarioService, times(1)).delete(1L);
    }
}
