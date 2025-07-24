package com.senai.reservei.repository;

import com.senai.reservei.model.Reserva;
import com.senai.reservei.model.StatusReservaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByDataEntradaLessThanEqualAndDataSaidaGreaterThanEqualAndStatusAndQuartoId(Date dataSaida, Date dataEntrada, StatusReservaEnum status, Long quarto);
    List<Reserva> findAllByHospedeDocumentoAndStatus(String documento, StatusReservaEnum status);
}
