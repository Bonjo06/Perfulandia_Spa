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

    public List<Envio> findAll(){
        return envioRepository.findAll();
    }

    public Envio findById(Long id){
        return envioRepository.findById(id).get();
    }

    public Envio save(Envio envio){
        return envioRepository.save(envio);
    }

    public void delete(Long id){
        envioRepository.deleteById(id);
    }

}
