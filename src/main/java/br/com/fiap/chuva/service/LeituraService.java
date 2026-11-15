package br.com.fiap.chuva.service;

import br.com.fiap.chuva.domain.Leitura;
import br.com.fiap.chuva.domain.Trecho;
import br.com.fiap.chuva.dto.LeituraDTO;
import br.com.fiap.chuva.mapper.LeituraMapper;
import br.com.fiap.chuva.repository.LeituraRepository;
import br.com.fiap.chuva.repository.TrechoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LeituraService {

    private static final int LIMIAR_ALERTA = 10;

    private static final String NORMAL = "NORMAL";
    private static final String ATENCAO = "ATENCAO";
    private static final String ALERTA = "ALERTA";

    private final LeituraRepository leituraRepository;
    private final TrechoRepository trechoRepository;
    private final LeituraMapper leituraMapper;

    @Transactional(readOnly = true)
    public List<LeituraDTO> listar() {
        return leituraRepository.findAllByOrderByDataHoraDesc().stream()
                .map(leituraMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LeituraDTO buscar(Long id) {
        return leituraMapper.toDTO(buscarEntidade(id));
    }

    @Transactional
    public void salvar(LeituraDTO dto) {
        Trecho trecho = trechoRepository.findById(dto.getTrechoId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Trecho não encontrado."));
        Leitura leitura = dto.getId() == null ? new Leitura() : buscarEntidade(dto.getId());
        leituraMapper.copyToEntity(dto, leitura, trecho);
        leitura.setSituacao(calcularSituacao(leitura.getLamina()));
        leituraRepository.save(leitura);
    }

    @Transactional
    public void excluir(Long id) {
        leituraRepository.delete(buscarEntidade(id));
    }

    private String calcularSituacao(int lamina) {
        if (lamina >= LIMIAR_ALERTA) {
            return ALERTA;
        }
        if (2 * lamina >= LIMIAR_ALERTA) {
            return ATENCAO;
        }
        return NORMAL;
    }

    private Leitura buscarEntidade(Long id) {
        return leituraRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Leitura não encontrada."));
    }
}
