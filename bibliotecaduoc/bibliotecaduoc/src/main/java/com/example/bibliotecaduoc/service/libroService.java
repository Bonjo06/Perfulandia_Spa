package com.example.bibliotecaduoc.service;


import com.example.bibliotecaduoc.model.libro;
import com.example.bibliotecaduoc.repository.libroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service

public class libroService {

    @Autowired
    private main.java.bibliotecaduoc.repository.libroRepository libroRepository;


    //Metodo para retornar todos los libros
    public List<libro> getLibros(){
        return libroRepository.obtenerLibros();
    }


    //Metodo para buscar un libro por id
    public libro saveLibro(libro lib){
        return libroRepository.guardar(lib);
    }


    //Metodo para buscar un libro por id
    public libro getLibroId(int id){
        return libroRepository.buscarPorId(id);
    }


    //Metodo para actuailzar un libro 
    public libro actualizar(libro lib){
        return libroRepository.actualizar(lib);
    }


    //Metodo para eliminar un libro 
    public String deleteLibro(int id){
        libroRepository.eliminar(id);
        return "libro eliminado";
    }

}