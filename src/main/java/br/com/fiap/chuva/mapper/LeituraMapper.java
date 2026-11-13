package br.com.fiap.chuva.mapper;

import br.com.fiap.chuva.domain.Leitura;
import br.com.fiap.chuva.domain.Trecho;
import br.com.fiap.chuva.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {

    public LeituraDTO toDTO(Leitura leitura) {
        return new LeituraDTO(
                leitura.getId(),
                leitura.getDataHora(),
                leitura.getLamina(),
                leitura.getTrecho().getId(),
                leitura.getTrecho().getLogradouro(),
                leitura.getSituacao());
    }

    /**
     * Copia os dados do DTO para a entidade (nova ou ja existente).
     * A situacao nao vem do usuario: e calculada pelo service (regra R1).
     */
    public void copyToEntity(LeituraDTO dto, Leitura leitura, Trecho trecho) {
        leitura.setDataHora(dto.getDataHora());
        leitura.setLamina(dto.getLamina());
        leitura.setTrecho(trecho);
    }
}
