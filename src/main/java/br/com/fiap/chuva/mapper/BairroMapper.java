package br.com.fiap.chuva.mapper;

import br.com.fiap.chuva.domain.Bairro;
import br.com.fiap.chuva.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {

    public BairroDTO toDTO(Bairro bairro) {
        return new BairroDTO(
                bairro.getId(),
                bairro.getNome(),
                bairro.getZona(),
                bairro.getPopulacao());
    }

    public void copyToEntity(BairroDTO dto, Bairro bairro) {
        bairro.setNome(dto.getNome().trim());
        bairro.setZona(dto.getZona());
        bairro.setPopulacao(dto.getPopulacao());
    }
}
