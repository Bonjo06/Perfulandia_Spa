package com.perfulandia_spa.perfulandia_spa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia_spa.perfulandia_spa.Assemblers.SucursalModelAssembler;
import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;
import com.perfulandia_spa.perfulandia_spa.Service.SucursalService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//Anotacion que indica la ruta base dentro de la API
@RequestMapping("api/v2/gerente/sucursal")
public class SucursalControllerV2 {

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private SucursalModelAssembler assembler;

    //Anotacion para listar todas las sucursales de la BD
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar todas las sucursales", description = "Lista todas las sucursales registradas en la BD")
    public CollectionModel<EntityModel<Sucursal>>getAllSucursales() {
        List<EntityModel<Sucursal>> sucursales = sucursalService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sucursales,
                linkTo(methodOn(SucursalControllerV2.class).getAllSucursales()).withSelfRel());
    }

    //Anotacion para buscar una sucursal en la BD mediante el ID
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener una sucursal por ID", description = "Obtiene una sucursal especifica de la BD por su ID")
    public EntityModel<Sucursal> getSucursalById(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        return assembler.toModel(sucursal);
    }

    //Anotacion para ingresar una nueva sucursal en la BD
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Registrar una nueva sucursal", description = "Registra una nueva sucursal en la BD")
    public ResponseEntity<EntityModel<Sucursal>> createSucursal(@RequestBody Sucursal sucursal) {
        Sucursal newSucursal = sucursalService.save(sucursal);
        return ResponseEntity
                .created(linkTo(methodOn(SucursalControllerV2.class).getSucursalById(newSucursal.getId())).toUri())
                .body(assembler.toModel(newSucursal));
    }

    //Anotacion para actualizar una sucursal en la BD mediante el ID
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar datos de una sucursal", description = "Actualiza los datos de una sucursal registrada en la BD por su ID")
    public ResponseEntity<EntityModel<Sucursal>> updateSucursal(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        sucursal.setId(id);
        Sucursal updatedSucursal = sucursalService.save(sucursal);
        return ResponseEntity
                .ok(assembler.toModel(updatedSucursal));
    }

    //Anotacion para eliminar una sucursal de la BDm mediante el ID
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal registrada en la BD por su ID")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
