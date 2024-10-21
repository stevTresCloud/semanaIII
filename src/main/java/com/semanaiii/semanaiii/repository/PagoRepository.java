package com.semanaiii.semanaiii.repository;

import com.semanaiii.semanaiii.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {

    // Cambiar la consulta para que use un campo que exista en la entidad Pago
    @Query("SELECT p FROM Pago p WHERE p.id IN (SELECT pago.id FROM Contacto c JOIN c.pagos pago WHERE c.id = :contactoId)")
    List<Pago> findPagosByContactoId(@Param("contactoId") Long contactoId);
}
