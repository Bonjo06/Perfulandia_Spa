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


import com.perfulandia_spa.perfulandia_spa.Model.Sucursal;
import com.perfulandia_spa.perfulandia_spa.Service.SucursalService;

@RestController
@RequestMapping("/api/v1/gerente/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> listar(){
        List<Sucursal> sucursales = sucursalService.findAll();
        if(sucursales.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sucursales);
    }

    @PostMapping
    public ResponseEntity<Sucursal> guardar(@RequestBody Sucursal sucursal){
        Sucursal sucursalNew = sucursalService.save(sucursal);
        return ResponseEntity.status(HttpStatus.CREATED).body(sucursalNew);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscar(@PathVariable Long id){
        try{
            Sucursal sucursal = sucursalService.findById(id);
            return ResponseEntity.ok(sucursal);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Long id, @RequestBody Sucursal sucursal){
        try{
            Sucursal suc = sucursalService.findById(id);
            suc.setId(id);
            suc.setSucDireccion(sucursal.getSucDireccion());

            sucursalService.save(suc);
            return ResponseEntity.ok(sucursal);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try{
            sucursalService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    



}
