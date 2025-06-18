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

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("api/v2/logistica/envio")
public class EnvioControllerV2 {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<EntityModel<Envio>> envios = envioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Envio> getEnvioById(@PathVariable Long id) {
        Envio envio = envioService.findById(id);
        return assembler.toModel(envio);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> createEnvio(@RequestBody Envio envio) {
        Envio newEnvio = envioService.save(envio);
        return ResponseEntity
                .created(linkTo(methodOn(EnvioControllerV2.class).getEnvioById(newEnvio.getId())).toUri())
                .body(assembler.toModel(newEnvio));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Envio>> updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        envio.setId(id);
        Envio updatedEnvio = envioService.save(envio);
        return ResponseEntity
                .ok(assembler.toModel(updatedEnvio));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEnvio(@PathVariable Long id) {
        envioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
