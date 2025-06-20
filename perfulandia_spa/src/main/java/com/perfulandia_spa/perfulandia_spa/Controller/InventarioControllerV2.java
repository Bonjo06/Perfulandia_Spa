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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v2/inventario")
public class InventarioControllerV2 {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioModelAssembler assembler;

    @GetMapping(value= "/gerente", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inventario>> getAllInventarios() {
        List<EntityModel<Inventario>> inventarios = inventarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).getAllInventarios()).withSelfRel());
    }
    
    @GetMapping(value = "/gerente/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Inventario> getInventarioById(@PathVariable Long id) {
        Inventario inventario = inventarioService.findById(id);
        return assembler.toModel(inventario);
    }

    @PostMapping(value = "/gerente", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> createInventario(@RequestBody Inventario inventario) {
        Inventario newInventario = inventarioService.save(inventario);
        return ResponseEntity
                .created(linkTo(methodOn(InventarioControllerV2.class).getInventarioById(newInventario.getId())).toUri())
                .body(assembler.toModel(newInventario));
    }

    @PutMapping(value = "/gerente/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inventario>> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        Inventario updatedInventario = inventarioService.save(inventario);
        return ResponseEntity
                .ok(assembler.toModel(updatedInventario));
    }

    @DeleteMapping(value = "/gerente/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value= "/logistica", produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inventario>> getAllInventariosLogistica() {
        List<EntityModel<Inventario>> inventarios = inventarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inventarios,
                linkTo(methodOn(InventarioControllerV2.class).getAllInventariosLogistica()).withSelfRel());
    }

    @GetMapping(value = "/logistica/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Inventario> getInventarioByIdLogistica(@PathVariable Long id) {
        Inventario inventario = inventarioService.findById(id);
        return assembler.toModel(inventario);
    }
}
