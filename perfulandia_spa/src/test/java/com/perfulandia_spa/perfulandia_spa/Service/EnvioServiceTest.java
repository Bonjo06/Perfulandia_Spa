package com.perfulandia_spa.perfulandia_spa.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.perfulandia_spa.perfulandia_spa.Model.Envio;
import com.perfulandia_spa.perfulandia_spa.Repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class EnvioServiceTest {

    @Autowired
    private EnvioService envioService;

    @MockitoBean
    private EnvioRepository envioRepository;

    @Test
    public void testFindAll() {
        Envio envio = new Envio();
        envio.setId(1L);
        when(envioRepository.findAll()).thenReturn(List.of(envio));

        List<Envio> envios = envioService.findAll();

        assertNotNull(envios);
        assertEquals(1, envios.size());
        assertEquals(envio.getId(), envios.get(0).getId(), envios.get(0).getId());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Envio envio = new Envio();
        envio.setId(id);
        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));

        Envio found = envioService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }   
    
    @Test
    public void testSave() {
        Envio envio = new Envio();
        envio.setId(1L);
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio saved = envioService.save(envio);
        assertNotNull(saved);
        assertEquals(1L, saved.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(envioRepository).deleteById(id);

        envioService.delete(id);
        verify(envioRepository, times(1)).deleteById(id);
    }
}