package com.perfulandia_spa.perfulandia_spa.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.perfulandia_spa.perfulandia_spa.Model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long>{    

    //Aquí se heredan los metodos para el CRUD de la tabla Envio

}
