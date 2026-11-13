package br.com.fiap.chuva.repository;

import br.com.fiap.chuva.domain.Trecho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrechoRepository extends JpaRepository<Trecho, Long> {

    List<Trecho> findAllByOrderByLogradouroAsc();

    boolean existsByBairroId(Long bairroId);
}
