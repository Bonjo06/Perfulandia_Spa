package com.example.bibliotecaduoc.repository;

import com.example.bibliotecaduoc.model.libro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository

public class libroRepository {

    //Arreglo que guarda los libros 
    private List<libro> listaLibros = new ArrayList<>();

    //Metodo para retornar todos los libros
    public List<libro> getAllLibros(){
        return listaLibros;
    }


    //Metodo para buscar un libro por id
    public libro buscarPorId(int id){
        for (libro librito : listaLibros){
            if (librito.getId() == id){
                return librito;
            }
        }
        return null;
    }

    //Metodo para buscar un libro por isbn
    public libro buscarPorIsbn(String isbn){
        for (libro librito : listaLibros){
            if (librito.getIsbn() == isbn){
                return librito;
            }
        }
        return null;
    }

    //Metodo para guardar libro en el arreglo y devolverlo
    public libro guardar(libro lib){
        listaLibros.add(lib);
        return lib;
    }


    //Metodo para actuailzar un libro 
    public Libro actualizar(Libro lib){
        int id = 0;
        int idPosicion = 0;

        for(int i = 0; i < listaLibros.size(); i++){
            if (listaLibros.get(i).getId() == lib.getId()){
                id = lib.getId();
                idPosicion = i;
            }
        }


        libro libro1 = new Libro();
        libro1.setId(id);
        libro1.setTitulo(lib.getTitulo());
        libro1.setAutor(lib.getAutor());
        libro1.setFechaPublicacion(lib.getFechaPublicacion());
        libro1.setEditorial(lib.getEditorial());
        libro1.setIsbn(lib.getIsbn());

        listaLibros.set(idPosicion, libro1);
        return libro1;

    }

    public void eliminar(int id){
        //alternativa 1
        Libro libro = buscarPorId(id);
        if(libro != null){
            listaLibros.remove(libro);
        }

        //alternativa 2
        int idPosicion = 0;
        for (int i = 0; i < listaLibros.size(); i++){
            if (listaLibros.get(i).getId() == id){
                idPosicion = i;
                break;
            }
        }
        if (idPosicion > 0){
            listaLibros.remove(idPosicion);
        }

        //alternativa 3
        listaLibros.removeIf(x -> x.getId() == id);

    }

}