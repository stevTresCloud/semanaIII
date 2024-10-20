package com.semanaiii.semanaiii.repository;

import com.semanaiii.semanaiii.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

