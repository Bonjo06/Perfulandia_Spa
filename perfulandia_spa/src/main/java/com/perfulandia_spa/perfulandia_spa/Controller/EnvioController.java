package com.perfulandia_spa.perfulandia_spa.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia_spa.perfulandia_spa.Model.Envio;
import com.perfulandia_spa.perfulandia_spa.Service.EnvioService;

@RestController

//Anotacion que indica la ruta base dentro de la API
@RequestMapping("/api/v1/logistica/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    //Anotacion para listar todos los envios que hay en la BD
    @GetMapping
    public ResponseEntity<List<Envio>> listar(){
        List<Envio> envios = envioService.findAll();
        if (envios.isEmpty()){
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(envios);
    }

    //Anotacion para ingresar un nuevo envio en la BD
    @PostMapping
    public ResponseEntity<Envio> guardar(@RequestBody Envio envios){
        Envio envioNuevo = envioService.save(envios);
        return ResponseEntity.status(HttpStatus.CREATED).body(envioNuevo); 
    }

    //Anotacion para buscar un envio en la BD mediante el id
    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscar(@PathVariable Long id){
        try{
            Envio envio = envioService.findById(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Anotacion para actualizar un envio en la BD mediante el id
    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizar(@PathVariable Long id, @RequestBody Envio envio){
        try{
            Envio env = envioService.findById(id);
            env.setId(id);          
            env.setEnvioDireccion(envio.getEnvioDireccion());
            env.setFechaInicio(envio.getFechaInicio());
            env.setFechaTermino(envio.getFechaTermino());
            env.setEnvioEstado(envio.getEnvioEstado());
            envioService.save(env);
            return ResponseEntity.ok(env);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Anotacion para eliminar un envio en la BD mediante el id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            envioService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
