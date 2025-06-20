package com.perfulandia_spa.perfulandia_spa;

import com.perfulandia_spa.perfulandia_spa.Model.*;
import com.perfulandia_spa.perfulandia_spa.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import java.util.Date;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        //Generar envíos
        for (int i = 0; i < 4; i++) {
            Envio envio = new Envio();
           //envio.setId((long)i+1);
            envio.setEnvioDireccion(faker.address().fullAddress());
            envio.setFechaInicio(new java.sql.Date(new Date().getTime()));
            envio.setFechaTermino(new java.sql.Date(new Date().getTime()));
            envio.setEnvioEstado(faker.options().option("Pendiente", "Enviado", "Entregado", "Cancelado"));
            envioRepository.save(envio);

        }

        //Generar inventario
        for (int i = 0; i < 10; i++) {
            Inventario inventario = new Inventario();
            //inventario.setId((long)i+1);
            inventario.setNombreProducto(faker.commerce().productName());
            inventario.setStock(random.nextInt(100) + 1);
            inventarioRepository.save(inventario);

        }

        //Generar sucursales
        for (int i = 0; i < 3; i++) {
            Sucursal sucursal = new Sucursal();
            //sucursal.setId((long)i+1);
            sucursal.setSucDireccion(faker.address().fullAddress());
            sucursalRepository.save(sucursal);

        }

        //Generar usuarios
        for (int i = 0; i < 25; i++) {
            Usuario usuario = new Usuario();
            //usuario.setId((long)i+1);
            usuario.setNombres(faker.name().firstName());
            usuario.setApellidos(faker.name().lastName());
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setUserDireccion(faker.address().fullAddress());
            usuarioRepository.save(usuario);
        }
    }

}
