package com.semanaiii.semanaiii.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;

    // Relación uno a uno
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuenta_bancaria_id", referencedColumnName = "id")
    private CuentaBancaria cuentaBancaria;

    // Relación Uno a Muchos
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacto_id")
    private List<Pago> pagos = new ArrayList<>();
}
