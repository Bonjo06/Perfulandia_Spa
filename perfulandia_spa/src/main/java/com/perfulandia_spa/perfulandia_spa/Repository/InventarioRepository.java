package com.perfulandia_spa.perfulandia_spa.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.perfulandia_spa.perfulandia_spa.Model.Inventario;


public interface InventarioRepository extends JpaRepository<Inventario, Long>{

    //Aquí se heredan los metodos para el CRUD de la tabla Inventario


}
