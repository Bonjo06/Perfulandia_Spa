package com.example.bibliotecaduoc.controller;

import com.example.bibliotecaduoc.model.libro;
import com.example.bibliotecaduoc.service.libroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/libros")

public class libroController {

    @Autowired
    private libroService libroService;


    //Metodo para retornar todos los libros
    @GetMapping("/")
    public List<libro> getLibros(){
        return libroService.getLibros();
    }


    //Metodo para guardar un libro
    @PostMapping("/")
    public libro saveLibro(@RequestBody libro lib){
        return libroService.saveLibro(lib);
    }


    //Metodo para buscar un libro por id
    @GetMapping("{id}")
    public libro getLibroId(@PathVariable int id){
        return libroService.getLibroId(id);
    }


    //Metodo para actuailzar un libro 
    @PutMapping("{id}")
    public libro actualizarLibro(@PathVariable int id, @RequestBody libro lib){
        return libroService.actualizar(lib);
    }


    //Metodo para eliminar un libro 
    @DeleteMapping("{id}")
    public String deleteLibro(@PathVariable int id){
        return libroService.deleteLibro(id);
    }

}