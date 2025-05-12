package com.perfulandia_spa.perfulandia_spa.Repository;

import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Long>{

    //Encontrar sucursal por dirección
    List<Sucursal> findBySucDireccion(String sucDireccion);

}
