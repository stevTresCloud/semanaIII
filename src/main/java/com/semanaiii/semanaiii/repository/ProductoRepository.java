package com.semanaiii.semanaiii.repository;

import com.semanaiii.semanaiii.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p JOIN p.categorias c WHERE c.nombre = :categoriaNombre")
    List<Producto> findProductosByCategoriaNombre(@Param("categoriaNombre") String categoriaNombre);

}

