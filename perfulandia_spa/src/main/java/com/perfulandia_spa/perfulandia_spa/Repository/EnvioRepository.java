package com.perfulandia_spa.perfulandia_spa.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfulandia_spa.perfulandia_spa.Model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long>{

    //Encontrar envío por el estado
    List<Envio> findByEnvioEstado(String envioEstado);
    

}
