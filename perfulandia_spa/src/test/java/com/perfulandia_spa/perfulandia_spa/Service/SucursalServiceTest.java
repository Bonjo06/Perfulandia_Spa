package com.perfulandia_spa.perfulandia_spa.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;
import com.perfulandia_spa.perfulandia_spa.Repository.SucursalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SucursalServiceTest {

    @Autowired
    private SucursalService sucursalService;

    @MockitoBean
    private SucursalRepository sucursalRepository;

    @Test
    public void testFindAll() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        when(sucursalRepository.findAll()).thenReturn(List.of(sucursal));

        List<Sucursal> sucursales = sucursalService.findAll();

        assertNotNull(sucursales);
        assertEquals(1, sucursales.size());
        assertEquals(sucursal.getId(), sucursales.get(0).getId());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Sucursal sucursal = new Sucursal(id, "Direccion Test");
        sucursal.setId(id);
        when(sucursalRepository.findById(id)).thenReturn(Optional.of(sucursal));

        Sucursal found = sucursalService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        when(sucursalRepository.save(sucursal)).thenReturn(sucursal);

        Sucursal saved = sucursalService.save(sucursal);
        assertNotNull(saved);
        assertEquals(sucursal.getId(), saved.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(sucursalRepository).deleteById(id);

        sucursalService.delete(id);
        verify(sucursalRepository, times(1)).deleteById(id);
    }
}
