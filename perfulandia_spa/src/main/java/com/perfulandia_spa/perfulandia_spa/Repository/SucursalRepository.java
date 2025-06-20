package com.perfulandia_spa.perfulandia_spa.Repository;

import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
   
    //Aquí se heredan los metodos para el CRUD de la tabla Sucursal

}
