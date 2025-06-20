package com.perfulandia_spa.perfulandia_spa.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.perfulandia_spa.perfulandia_spa.Controller.InventarioControllerV2;
import com.perfulandia_spa.perfulandia_spa.Model.Inventario;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioControllerV2.class).getInventarioById(inventario.getId())).withSelfRel(),
                linkTo(methodOn(InventarioControllerV2.class).getAllInventarios()).withRel("inventarios"));
    }
}
