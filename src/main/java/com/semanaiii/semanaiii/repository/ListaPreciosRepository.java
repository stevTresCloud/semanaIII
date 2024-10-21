package com.semanaiii.semanaiii.repository;

import com.semanaiii.semanaiii.entity.ListaPrecios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ListaPreciosRepository extends JpaRepository<ListaPrecios, Long> {

    @Query("SELECT lp FROM ListaPrecios lp WHERE lp.producto.id = :id")
    List<ListaPrecios> findListaPreciosByProductoId(@Param("id") Long id);
}