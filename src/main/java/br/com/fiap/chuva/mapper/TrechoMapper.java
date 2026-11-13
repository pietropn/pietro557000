package br.com.fiap.chuva.mapper;

import br.com.fiap.chuva.domain.Bairro;
import br.com.fiap.chuva.domain.Trecho;
import br.com.fiap.chuva.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {

    public TrechoDTO toDTO(Trecho trecho) {
        return new TrechoDTO(
                trecho.getId(),
                trecho.getLogradouro(),
                trecho.getTipoCalcada(),
                trecho.getAltitude(),
                trecho.getBairro().getId(),
                trecho.getBairro().getNome());
    }

    /** Copia os dados do DTO para a entidade (nova ou ja existente). */
    public void copyToEntity(TrechoDTO dto, Trecho trecho, Bairro bairro) {
        trecho.setLogradouro(dto.getLogradouro().trim());
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setAltitude(dto.getAltitude());
        trecho.setBairro(bairro);
    }
}
