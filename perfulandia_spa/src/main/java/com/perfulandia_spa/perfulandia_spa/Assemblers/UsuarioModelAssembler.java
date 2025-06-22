package com.perfulandia_spa.perfulandia_spa.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.perfulandia_spa.perfulandia_spa.Controller.UsuarioControllerV2;
import com.perfulandia_spa.perfulandia_spa.Model.Usuario;


@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>>{
@Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControllerV2.class).getUsuarioById(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withRel("usuarios"));
        
    }
}
