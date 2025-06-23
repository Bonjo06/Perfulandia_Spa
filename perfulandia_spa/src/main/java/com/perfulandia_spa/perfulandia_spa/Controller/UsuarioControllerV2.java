package com.perfulandia_spa.perfulandia_spa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia_spa.perfulandia_spa.Assemblers.UsuarioModelAssembler;
import com.perfulandia_spa.perfulandia_spa.Model.Usuario;
import com.perfulandia_spa.perfulandia_spa.Service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//Anotacion que indica la ruta base dentro de la API
@RequestMapping("api/v2/admin/usuario")
public class UsuarioControllerV2 {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler assembler;


    //Anotacion para listar todos los usuarios de la BD
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Listar todos los usuarios", description = "Obtiene todos los usuarios registrados en la BD")
    public CollectionModel<EntityModel<Usuario>>getAllUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioControllerV2.class).getAllUsuarios()).withSelfRel());
    }

    //Anotacion para buscar un usuario en la BD mediante el ID
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener un usuario por ID", description = "Obtiene un usuario específico de la BD por su ID")
    public EntityModel<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return assembler.toModel(usuario);
    }

    //Anotacion para agregar un nuevo usuario en la BD
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario en la BD")
    public ResponseEntity<EntityModel<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioControllerV2.class).getUsuarioById(newUsuario.getId())).toUri())
                .body(assembler.toModel(newUsuario));
    }

    //Anotacion para actualizar un usuario en la BD mediante el ID
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar datos de un usuario", description = "Actualiza los datos de un usuario registrado en la BD por su ID")
    public ResponseEntity<EntityModel<Usuario>> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        Usuario updatedUsuario = usuarioService.save(usuario);
        return ResponseEntity
                .ok(assembler.toModel(updatedUsuario));
    }

    //Anotacion para eliminar un usuario en la BD mediante el ID
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario registrado en la BD por su ID")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
