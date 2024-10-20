package com.semanaiii.semanaiii.pruebas;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.semanaiii.semanaiii.entity.Producto;
import com.semanaiii.semanaiii.entity.ListaPrecios;
import com.semanaiii.semanaiii.repository.ProductoRepository;
import com.semanaiii.semanaiii.repository.ListaPreciosRepository;

@Component
public class PruebaProductoListaPrecio implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ListaPreciosRepository listaPreciosRepository;

    @Override
    public void run(String... args) throws Exception {
        // Crear un nuevo producto
        Producto producto = new Producto();
        producto.setNombre("Producto A");

        // Crear dos listas de precios y asociarlas al producto
        ListaPrecios lista1 = new ListaPrecios();
        lista1.setPrecio(100.0);
        lista1.setProducto(producto);

        ListaPrecios lista2 = new ListaPrecios();
        lista2.setPrecio(200.0);
        lista2.setProducto(producto);

        // Agregar las listas de precios al producto
        producto.getListasPrecios().add(lista1);
        producto.getListasPrecios().add(lista2);

        // Persistir el producto (y automáticamente las listas de precios asociadas)
        productoRepository.save(producto);

        // Obtener el producto guardado
        Optional<Producto> productoGuardado = productoRepository.findById(producto.getId());
        if (productoGuardado.isPresent()) {
            Producto productoObtenido = productoGuardado.get();
            System.out.println("Producto obtenido: " + productoObtenido.getNombre());

            // Actualizar el nombre del producto
            productoObtenido.setNombre("Producto B");
            productoRepository.save(productoObtenido);
            System.out.println("Producto actualizado: " + productoObtenido.getNombre());

            // Crear una nueva lista de precios y asociarla al producto actualizado
            ListaPrecios lista3 = new ListaPrecios();
            lista3.setPrecio(300.0);
            lista3.setProducto(productoObtenido);
            listaPreciosRepository.save(lista3);
            System.out.println("Lista de precios creada: " + lista3.getPrecio());

            // Eliminar una lista de precios
            Optional<ListaPrecios> listaEliminar = listaPreciosRepository.findById(lista1.getId());
            if (listaEliminar.isPresent()) {
                listaPreciosRepository.delete(listaEliminar.get());
                System.out.println("Lista de precios eliminada: " + listaEliminar.get().getPrecio());
            } else {
                System.out.println("Lista de precios no encontrada");
            }
        } else {
            System.out.println("Producto no encontrado");
        }
    }
}
