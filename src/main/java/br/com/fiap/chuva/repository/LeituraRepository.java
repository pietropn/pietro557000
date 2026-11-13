package br.com.fiap.chuva.repository;

import br.com.fiap.chuva.domain.Leitura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {

    List<Leitura> findAllByOrderByDataHoraDesc();

    boolean existsByTrechoId(Long trechoId);
}
