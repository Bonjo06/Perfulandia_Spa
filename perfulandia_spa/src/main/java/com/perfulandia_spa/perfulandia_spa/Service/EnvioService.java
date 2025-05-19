package com.perfulandia_spa.perfulandia_spa.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfulandia_spa.perfulandia_spa.Model.Envio;
import com.perfulandia_spa.perfulandia_spa.Repository.EnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    //Metodo para listar todos los envios de la BD
    public List<Envio> findAll(){
        return envioRepository.findAll();
    }

    //Metodo para buscar un envio mediante el id en la BD
    public Envio findById(Long id){
        return envioRepository.findById(id).get();
    }

    //Metodo para guardar/sobreescribir un envio en la BD
    public Envio save(Envio envio){
        return envioRepository.save(envio);
    }

    //Metodo para eliminar un envio de la BD mediante el id
    public void delete(Long id){
        envioRepository.deleteById(id);
    }

}
