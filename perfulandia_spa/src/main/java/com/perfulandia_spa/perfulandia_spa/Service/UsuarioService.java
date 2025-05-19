package com.perfulandia_spa.perfulandia_spa.Service;

import java.util.List;
import com.perfulandia_spa.perfulandia_spa.Model.Usuario;
import com.perfulandia_spa.perfulandia_spa.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;



@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Metodo para listar todos los usuarios de la BD
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    //Metodo para buscar un usuario mediante el id en la BD
    public Usuario findById(Long id){
        return usuarioRepository.findById(id).get();
    }

    //Metodo para guardar/sobreescribir un usuario en la BD
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Metodo para eliminar un usuario de la BD mediante el id
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

}
