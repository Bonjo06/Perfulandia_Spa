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

import com.perfulandia_spa.perfulandia_spa.Model.Inventario;
import com.perfulandia_spa.perfulandia_spa.Service.InventarioService;

@RestController

//Anotacion que indica la ruta base dentro de la API
@RequestMapping("/api/v1/inventario") 
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    //Anotacion para listar todos los productos de la BD
    @GetMapping("/gerente")
    public ResponseEntity<List<Inventario>> listar(){
        List<Inventario> inventarios = inventarioService.findAll();
        if(inventarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }

    //Anotacion para ingresar un nuevo producto en la BD
    @PostMapping ("/gerente")
    public ResponseEntity<Inventario> guardar(@RequestBody Inventario inventario){
        Inventario nuevoInventario = inventarioService.save(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInventario);
    }

    //Anotacion para buscar un producto mediante el id en la BD
    @GetMapping("/gerente/{id}")
    public ResponseEntity<Inventario> buscar(@PathVariable Long id){
        try{
            Inventario inventario = inventarioService.findById(id);
            return ResponseEntity.ok(inventario);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Anotacion para Actualizar un producto en la BD mediante el id
    @PutMapping("/gerente/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable Long id, @RequestBody Inventario inventario){
        try{
            Inventario inv = inventarioService.findById(id);
            inv.setId(id);
            inv.setNombreProducto(inventario.getNombreProducto());

            inventarioService.save(inv);
            return ResponseEntity.ok(inventario);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Anotacion para borrar un producto de la BD mediante el id
    @DeleteMapping("/gerente/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            inventarioService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Anotacion especificada para empleado logistica donde solo accede a la anotacion GET del inventario, mediante el id
    @GetMapping("/logistica/{id}")
    public ResponseEntity<Inventario> search(@PathVariable Long id){
        try{
            Inventario inventario = inventarioService.findById(id);
            return ResponseEntity.ok(inventario);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }    

    //Anotacion especificada para empleado logistica, donde solo accede a la anotacion GET del inventario, listandolo completo
    @GetMapping("/logistica")
    public ResponseEntity<List<Inventario>> searchAll(){
        List<Inventario> inventarios = inventarioService.findAll();
        if(inventarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }
     

}
