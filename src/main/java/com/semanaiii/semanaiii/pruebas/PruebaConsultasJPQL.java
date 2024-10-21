package com.semanaiii.semanaiii.pruebas;

import com.semanaiii.semanaiii.entity.Categoria;
import com.semanaiii.semanaiii.entity.Contacto;
import com.semanaiii.semanaiii.entity.CuentaBancaria;
import com.semanaiii.semanaiii.entity.ListaPrecios;
import com.semanaiii.semanaiii.entity.Pago;
import com.semanaiii.semanaiii.entity.Producto;
import com.semanaiii.semanaiii.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PruebaConsultasJPQL implements CommandLineRunner {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private ListaPreciosRepository listaPreciosRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("====================== Prueba de Consultas JPQL ======================");

        // Crear y guardar una cuenta bancaria
        CuentaBancaria cuenta = new CuentaBancaria();
        cuenta.setNumeroCuenta("987654321");
        cuenta.setBanco("Banco XYZ");

        // Crear y guardar un contacto
        Contacto contacto = new Contacto();
        contacto.setNombre("Juan Pérez");
        contacto.setTelefono("0987654321");
        contacto.setCuentaBancaria(cuenta);
        contactoRepository.save(contacto);  // Guardar contacto

        // Crear y guardar una categoría
        Categoria categoria = new Categoria();
        categoria.setNombre("Electrónica");
        categoriaRepository.save(categoria);  // Guardar categoría

        // Crear y guardar un producto
        Producto producto = new Producto();
        producto.setNombre("Laptop");

        // Asociar producto con categoría
        Producto nuevoProducto = productoRepository.save(producto);  // Guarda el producto vacío primero para obtener el ID
        categoriaRepository.save(categoria); // Asegúrate de que la categoría se guarda antes de la asociación
        nuevoProducto.getCategorias().add(categoria); // Asociar categoría al producto
        productoRepository.save(nuevoProducto);  // Guarda el producto nuevamente con la categoría asociada

        // Crear y guardar lista de precios
        ListaPrecios listaPrecio = new ListaPrecios();
        listaPrecio.setPrecio(1200.00);
        listaPrecio.setProducto(nuevoProducto);
        listaPreciosRepository.save(listaPrecio);  // Guardar lista de precios

        // Crear y guardar un pago para el contacto
        Pago pago = new Pago();
        pago.setMonto(150.00);
        pago.setFecha("2024-10-20");
        contacto.getPagos().add(pago);  // Asociar pago con contacto
        contactoRepository.save(contacto);  // Guardar contacto, lo que guarda el pago por cascada

        // 1. Obtener todos los contactos con sus cuentas bancarias
        List<Contacto> contactosConCuentas = contactoRepository.findAllContactosWithCuentas();
        System.out.println("Contactos con cuentas bancarias:");
        contactosConCuentas.forEach(c -> {
            System.out.println("Nombre: " + c.getNombre() + ", Banco: " + c.getCuentaBancaria().getBanco());
        });

        // 2. Obtener todos los pagos de un contacto específico (por ejemplo, ID del contacto creado)
        List<Pago> pagosContacto = pagoRepository.findPagosByContactoId(contacto.getId());
        System.out.println("Pagos del contacto con ID " + contacto.getId() + ":");
        pagosContacto.forEach(p -> {
            System.out.println("Monto: " + p.getMonto() + ", Fecha: " + p.getFecha());
        });

        // 3. Obtener productos por categoría (por ejemplo, "Electrónica")
        List<Producto> productosPorCategoria = productoRepository.findProductosByCategoriaNombre("Electrónica");
        System.out.println("Productos en la categoría 'Electrónica':");
        productosPorCategoria.forEach(p -> {
            System.out.println("Producto: " + p.getNombre());
        });

        // 4. Obtener lista de precios de un producto específico (por ejemplo, el producto creado)
        List<ListaPrecios> listaPreciosProducto = listaPreciosRepository.findListaPreciosByProductoId(nuevoProducto.getId());
        System.out.println("Lista de precios del producto con ID " + nuevoProducto.getId() + ":");
        listaPreciosProducto.forEach(lp -> {
            System.out.println("Precio: " + lp.getPrecio());
        });

        // 5. Obtener contactos con pagos mayores a un monto específico (por ejemplo, 100.0)
        List<Contacto> contactosConPagosAltos = contactoRepository.findContactosWithPagosAboveMonto(100.0);
        System.out.println("Contactos con pagos mayores a 100.0:");
        contactosConPagosAltos.forEach(c -> {
            System.out.println("Nombre: " + c.getNombre());
        });

        System.out.println("===========================================================================");
    }
}
