package com.perfulandia_spa.perfulandia_spa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia_spa.perfulandia_spa.Assemblers.EnvioModelAssembler;
import com.perfulandia_spa.perfulandia_spa.Model.Envio;
import com.perfulandia_spa.perfulandia_spa.Service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
//Anotacion que indica la ruta base dentro de la API
@RequestMapping("api/v2/logistica/envio")
public class EnvioControllerV2 {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioModelAssembler assembler;

    //Anotacion para listar todos los envios que hay en la BD
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar todos los envíos", description = "Obtiene todos los envíos registrados en la BD")
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<EntityModel<Envio>> envios = envioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withSelfRel());
    }

    //Anotacion para buscar un envio en la BD mediante el ID
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un envío por ID", description = "Obtiene un envío específico por su ID")
    public EntityModel<Envio> getEnvioById(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        return assembler.toModel(envio);
    }

    //Anotacion para ingresar un nuevo envio en la BD
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear un nuevo envío", description = "Registra un nuevo envío en la BD")
    public ResponseEntity<EntityModel<Envio>> createEnvio(@RequestBody Envio envio) {
        Envio newEnvio = envioService.save(envio);
        return ResponseEntity
                .created(linkTo(methodOn(EnvioControllerV2.class).getEnvioById(newEnvio.getId())).toUri())
                .body(assembler.toModel(newEnvio));
    }

    //Anotacion para actualizar un envio en la BD mediante el ID
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar un envío", description = "Actualiza un envío existente por su ID")
    public ResponseEntity<EntityModel<Envio>> updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id);
        Envio updatedEnvio = envioService.save(envio);
        return ResponseEntity
                .ok(assembler.toModel(updatedEnvio));
    }

    //Anotacion para eliminar un envio en la BD mediante el ID
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un envío", description = "Elimina un envío existente por su ID")
    public ResponseEntity<?> deleteEnvio(@PathVariable Long id) {
        envioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
