package com.senai.reservei.repository;

import com.senai.reservei.model.Quarto;
import com.senai.reservei.model.StatusQuartoEnum;
import com.senai.reservei.model.TipoQuartoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    List<Quarto> findAllByStatus(StatusQuartoEnum status);
    List<Quarto> findAllByTipo(TipoQuartoEnum tipo);
}
