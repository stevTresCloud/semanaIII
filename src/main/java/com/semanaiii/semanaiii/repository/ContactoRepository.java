package com.semanaiii.semanaiii.repository;

import com.semanaiii.semanaiii.entity.Contacto;
import com.semanaiii.semanaiii.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {

    @Query("SELECT c FROM Contacto c JOIN FETCH c.cuentaBancaria")
    List<Contacto> findAllContactosWithCuentas();

    @Query("SELECT DISTINCT c FROM Contacto c JOIN c.pagos p WHERE p.monto > :monto")
    List<Contacto> findContactosWithPagosAboveMonto(@Param("monto") Double monto);
}
