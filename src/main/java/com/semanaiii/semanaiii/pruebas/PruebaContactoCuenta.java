package com.semanaiii.semanaiii.pruebas;

import com.semanaiii.semanaiii.entity.Contacto;
import com.semanaiii.semanaiii.entity.CuentaBancaria;
import com.semanaiii.semanaiii.repository.ContactoRepository;
import com.semanaiii.semanaiii.repository.CuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PruebaContactoCuenta implements CommandLineRunner {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Override
    public void run(String... args) throws Exception {
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setNumeroCuenta("987654321");
        cuenta.setBanco("Banco XYZ");

        // NOTA: No se guarda porque en la declaración de la relación de uno a uno se definio como tipo cascade = CascadeType.ALL
        // Por lo tanto, al guardar el contacto, se guarda automáticamente la cuenta bancaria
        // cuentaBancariaRepository.save(cuenta);

        Contacto contacto = new Contacto();
        contacto.setNombre("Juan Pérez");
        contacto.setTelefono("0987654321");
        contacto.setCuentaBancaria(cuenta);

        // Guardar el contacto, lo que automáticamente guarda también la cuenta bancaria
        contactoRepository.save(contacto);
    }
}