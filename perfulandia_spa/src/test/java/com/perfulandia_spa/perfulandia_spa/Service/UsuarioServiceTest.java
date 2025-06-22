package com.perfulandia_spa.perfulandia_spa.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.perfulandia_spa.perfulandia_spa.Model.Usuario;
import com.perfulandia_spa.perfulandia_spa.Repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        Usuario usuario = new Usuario(1L, "Elias Miguel", "Torres Atencio", "mi.torres@catac", "San Pablo #5555");
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));

        List<Usuario> usuarios = usuarioService.findAll();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Usuario usuario = new Usuario(id, "Elias Miguel", "Torres Atencio", "mi.torres@catac", "San Pablo #5555");
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Usuario found = usuarioService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1L, "Elias Miguel", "Torres Atencio", "mi.torres@catac", "Av España 123");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.save(usuario);
        assertNotNull(saved);
        assertEquals("Elias Miguel", saved.getNombres());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.delete(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
