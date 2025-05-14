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

    public List<Sucursal> findAll(){
        return sucursalRepository.findAll();
    }

    public Sucursal findById(Long id){
    return sucursalRepository.findById(id).get();
    }

    public Sucursal save(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }

    public void delete(Long id){
        sucursalRepository.deleteById(id);
    }


}
