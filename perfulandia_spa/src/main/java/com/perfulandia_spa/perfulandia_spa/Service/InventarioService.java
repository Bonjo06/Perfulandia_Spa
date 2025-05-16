package com.perfulandia_spa.perfulandia_spa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia_spa.perfulandia_spa.Model.Inventario;
import com.perfulandia_spa.perfulandia_spa.Repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> findAll(){
        return inventarioRepository.findAll();
    }

    public Inventario findById(Long id){
    return inventarioRepository.findById(id).get();
    }

    public Inventario save(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    public void delete(Long id){
        inventarioRepository.deleteById(id);
    }



}
