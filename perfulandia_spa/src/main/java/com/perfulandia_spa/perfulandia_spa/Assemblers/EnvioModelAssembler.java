package com.perfulandia_spa.perfulandia_spa.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.perfulandia_spa.perfulandia_spa.Controller.EnvioControllerV2;
import com.perfulandia_spa.perfulandia_spa.Model.Envio;



@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {

    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioControllerV2.class).getEnvioById(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withRel("envios"));
        
    }
}