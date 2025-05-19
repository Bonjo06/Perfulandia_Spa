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

    //Metodo para listar todos los productos de la BD
    public List<Inventario> findAll(){
        return inventarioRepository.findAll();
    }

    //Metodo para buscar un producto mediante el id en la BD
    public Inventario findById(Long id){
    return inventarioRepository.findById(id).get();
    }

    //Metodo para guardar/sobreescribir un producto en la BD
    public Inventario save(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    //Metodo para eliminar un producto de la BD mediante el id
    public void delete(Long id){
        inventarioRepository.deleteById(id);
    }



}
