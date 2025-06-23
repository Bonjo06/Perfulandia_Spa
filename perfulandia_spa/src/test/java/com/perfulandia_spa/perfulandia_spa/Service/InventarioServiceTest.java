package com.perfulandia_spa.perfulandia_spa.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.perfulandia_spa.perfulandia_spa.Model.Inventario;
import com.perfulandia_spa.perfulandia_spa.Repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

//Clase para probar el servicio InventarioService
@SpringBootTest
@ActiveProfiles("test")
public class InventarioServiceTest {

    @Autowired
    private InventarioService inventarioService;

    @MockitoBean
    private InventarioRepository inventarioRepository;

    @Test
    public void testFindAll() {
        Inventario inventario = new Inventario();
        inventario.setId(1L);
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));

        List<Inventario> inventarios = inventarioService.findAll();

        assertNotNull(inventarios);
        assertEquals(1L, inventarios.size());
        assertEquals(inventario.getId(), inventarios.get(0).getId());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Inventario inventario = new Inventario();
        inventario.setId(id);
        when(inventarioRepository.findById(id)).thenReturn(Optional.of(inventario));

        Inventario found = inventarioService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Inventario inventario = new Inventario();
        inventario.setId(1L);
        when(inventarioRepository.save(inventario)).thenReturn(inventario);

        Inventario saved = inventarioService.save(inventario);
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(inventarioRepository).deleteById(id);

        inventarioService.delete(id);
        verify(inventarioRepository, times(1)).deleteById(id);
    }
}
