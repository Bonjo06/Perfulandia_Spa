package com.perfulandia_spa.perfulandia_spa.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.perfulandia_spa.perfulandia_spa.Controller.SucursalControllerV2;
import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {

    @Override
    public EntityModel<Sucursal> toModel(Sucursal sucursal) {
        return EntityModel.of(sucursal,
                linkTo(methodOn(SucursalControllerV2.class).getSucursalById(sucursal.getId())).withSelfRel(),
                linkTo(methodOn(SucursalControllerV2.class).getAllSucursales()).withRel("sucursales"));
    }
}
