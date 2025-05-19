package com.perfulandia_spa.perfulandia_spa.Repository;

import com.perfulandia_spa.perfulandia_spa.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    //Aquí se heredan los metodos para el CRUD de la tabla Usuario

}
