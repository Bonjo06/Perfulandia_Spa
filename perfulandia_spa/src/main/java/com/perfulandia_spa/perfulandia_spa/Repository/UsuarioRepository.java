package com.perfulandia_spa.perfulandia_spa.Repository;

import com.perfulandia_spa.perfulandia_spa.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    //Encontrar usuario por apellido
    List<Usuario> findByApellidos(String apellidos);

    //Encontrar usuario por correo
    List<Usuario> findByCorreo(String correo);

    //Encontrar usuario por nombre y apellido
    List<Usuario> findByNombresAndApellidos(String nombres, String apellidos);

}
