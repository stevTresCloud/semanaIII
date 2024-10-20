package com.semanaiii.semanaiii.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.semanaiii.semanaiii.entity.Producto;
import com.semanaiii.semanaiii.entity.Categoria;
import com.semanaiii.semanaiii.repository.ProductoRepository;
import com.semanaiii.semanaiii.repository.CategoriaRepository;

import java.util.Optional;

@Component
@Transactional
public class PruebaProductoCategoria implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void run(String... args) throws Exception {
        System.out.println("====================== Prueba de Producto y Categoría ======================");

        // Crear productos
        Producto producto1 = new Producto();
        producto1.setNombre("Producto A");

        Producto producto2 = new Producto();
        producto2.setNombre("Producto B");

        // Crear categorías
        Categoria categoria1 = new Categoria();
        categoria1.setNombre("Categoría X");

        Categoria categoria2 = new Categoria();
        categoria2.setNombre("Categoría Y");

        // Asociar productos a categorías
        producto1.getCategorias().add(categoria1);
        producto2.getCategorias().add(categoria2);

        producto2.getCategorias().add(categoria1);

        // Persistir los productos
        productoRepository.save(producto1);
        productoRepository.save(producto2);

        // Recuperar un producto y una categoría
        Producto productoRecuperado = productoRepository.findById(producto1.getId()).orElse(null);
        System.out.println("Producto recuperado: " + productoRecuperado.getNombre());

        Categoria categoriaRecuperada = categoriaRepository.findById(categoria1.getId()).orElse(null);
        System.out.println("Categoría recuperada: " + categoriaRecuperada.getNombre());

        productoRepository.deleteById(producto1.getId());
        productoRepository.deleteById(producto2.getId());
        categoriaRepository.deleteById(categoria1.getId());
        categoriaRepository.deleteById(categoria2.getId());

        System.out.println("===========================================================================");
    }
}
