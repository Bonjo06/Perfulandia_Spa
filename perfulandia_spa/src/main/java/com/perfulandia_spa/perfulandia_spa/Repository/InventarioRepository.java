package com.perfulandia_spa.perfulandia_spa.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perfulandia_spa.perfulandia_spa.Model.Inventario;


public interface InventarioRepository extends JpaRepository<Inventario, Long>{



    //Encontrar inventario por nombre de producto
    List<Inventario> findByNombreProducto(String nombreProducto);

}
