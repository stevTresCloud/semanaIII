package com.semanaiii.semanaiii.pruebas;

import java.util.List;
import java.util.Optional;

import com.semanaiii.semanaiii.entity.Contacto;
import com.semanaiii.semanaiii.entity.Pago;
import com.semanaiii.semanaiii.repository.ContactoRepository;
import com.semanaiii.semanaiii.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class PruebaContactoPago implements CommandLineRunner {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear un nuevo contacto
        Contacto contacto = new Contacto();
        contacto.setNombre("Juan Pérez");
        contacto.setTelefono("0987654321");

        // Crear pagos asociados
        Pago pago1 = new Pago();
        pago1.setMonto(100.0);
        pago1.setFecha("2024-10-20");



        Pago pago2 = new Pago();
        pago2.setMonto(200.0);
        pago2.setFecha("2024-10-21");

        // Relación unidireccional muchos a uno (@OneToMany)
        contacto.getPagos().add(pago1);
        contacto.getPagos().add(pago2);

        // Guardar el contacto, lo que también guarda automáticamente los pagos
        contactoRepository.save(contacto);
        System.out.println("Contacto guardado: " + contacto.getNombre());

        // Leer el contacto guardado por ID
        Optional<Contacto> contactoGuardado = contactoRepository.findById(contacto.getId());
        contactoGuardado.ifPresent(c -> {
            System.out.println("Contacto leído por ID: " + c.getNombre());
            c.setNombre("Nuevo nombre del contacto");
            contactoRepository.save(c);
            System.out.println("Contacto actualizado: " + c.getNombre());
        });

        // Listar todos los contactos
        List<Contacto> contactos = contactoRepository.findAll();
        System.out.println("Lista de contactos: ");
        for (Contacto c : contactos) {
            System.out.println("- " + c.getNombre());
        }

        // Eliminar el contacto
        contactoRepository.delete(contacto);
        System.out.println("Contacto eliminado");
    }
}
