package com.perfulandia_spa.perfulandia_spa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;
import com.perfulandia_spa.perfulandia_spa.Repository.SucursalRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    //Metodo para listar todas las sucursales de la BD
    public List<Sucursal> findAll(){
        return sucursalRepository.findAll();
    }

    //Metodo para buscar una sucursal mediante el id en la BD
    public Sucursal findById(Long id){
    return sucursalRepository.findById(id).get();
    }

    //Metodo para guardar/sobreescribir una sucursal en la BD
    public Sucursal save(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }

    //Metodo para eliminar una sucursal de la BD mediante el id
    public void delete(Long id){
        sucursalRepository.deleteById(id);
    }


}
