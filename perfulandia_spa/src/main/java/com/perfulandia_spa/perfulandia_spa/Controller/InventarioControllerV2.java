package com.perfulandia_spa.perfulandia_spa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia_spa.perfulandia_spa.Assemblers.InventarioModelAssembler;
import com.perfulandia_spa.perfulandia_spa.Model.Inventario;
import com.perfulandia_spa.perfulandia_spa.Service.InventarioService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;


@RestController
//Anotacion que indica la ruta base dentro de la API
@RequestMapping("api/v2/inventario")
public class InventarioControllerV2 {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioModelAssembler assembler;

    //Anotacion para listar todos los productos de la BD (Perfil de Gerente)
    @GetMapping(value= "/gerente", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar todos los inventarios", description = "Obtiene todos los inventarios registrados en la BD")
    public CollectionModel<EntityModel<Inventario>> getAllInventarios() {
        List<EntityModel<Inventario>> inventarios = inventarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).getAllInventarios()).withSelfRel());
    }
    
    //Anotacion para buscar un producto mediante el ID en la BD (Perfil de Gerente)
    @GetMapping(value = "/gerente/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un producto del inventario por ID", description = "Obtiene un producto específico del inventario por su ID dentro de la BD")
    public EntityModel<Inventario> getInventarioById(@PathVariable Long id) {
        Inventario inventario = inventarioService.findById(id);
        return assembler.toModel(inventario);
    }

    //Anotacion para ingresar un nuevo producto en la BD (Perfil de Gerente)
    @PostMapping(value = "/gerente", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto del inventario en la BD")
    public ResponseEntity<EntityModel<Inventario>> createInventario(@RequestBody Inventario inventario) {
        Inventario newInventario = inventarioService.save(inventario);
        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).getInventarioById(newInventario.getId())).toUri())
                .body(assembler.toModel(newInventario));
    }

    //Anotacion para Actualizar un producto en la BD mediante el ID (Perfil de Gerente)
    @PutMapping(value = "/gerente/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un producto del inventario", description = "Actualiza un producto existente del inventario por su ID")
    public ResponseEntity<EntityModel<Inventario>> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        Inventario updatedInventario = inventarioService.save(inventario);
        return ResponseEntity
                .ok(assembler.toModel(updatedInventario));
    }

    //Anotacion para borrar un producto de la BD mediante el ID (Perfil de Gerente)
    @DeleteMapping(value = "/gerente/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un producto del inventario", description = "Elimina un producto existente del inventario por su id")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //Anotacion para listar todos los productos de la BD (Perfil de Logística)
    @GetMapping(value= "/logistica", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar todos los inventarios", description = "Obtiene todos los inventarios registrados en la BD")
    public CollectionModel<EntityModel<Inventario>> getAllInventariosLogistica() {
        List<EntityModel<Inventario>> inventarios = inventarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).getAllInventariosLogistica()).withSelfRel());
    }

    //Anotacion para buscar un producto mediante el ID en la BD (Perfil de Logística)
    @GetMapping(value = "/logistica/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un producto del inventario por ID", description = "Obtiene un producto específico del inventario por su ID dentro de la BD")
    public EntityModel<Inventario> getInventarioByIdLogistica(@PathVariable Long id) {
        Inventario inventario = inventarioService.findById(id);
        return assembler.toModel(inventario);
    }
}
