package br.com.fiap.chuva.repository;

import br.com.fiap.chuva.domain.Bairro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    List<Bairro> findAllByOrderByNomeAsc();
}
