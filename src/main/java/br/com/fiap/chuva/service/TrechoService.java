package br.com.fiap.chuva.service;

import br.com.fiap.chuva.domain.Bairro;
import br.com.fiap.chuva.domain.Trecho;
import br.com.fiap.chuva.dto.TrechoDTO;
import br.com.fiap.chuva.mapper.TrechoMapper;
import br.com.fiap.chuva.repository.BairroRepository;
import br.com.fiap.chuva.repository.LeituraRepository;
import br.com.fiap.chuva.repository.TrechoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TrechoService {

    public static final List<String> TIPOS_CALCADA =
            List.of("Regular", "Irregular", "Escadaria", "Sem iluminação");

    private final TrechoRepository trechoRepository;
    private final BairroRepository bairroRepository;
    private final LeituraRepository leituraRepository;
    private final TrechoMapper trechoMapper;

    @Transactional(readOnly = true)
    public List<TrechoDTO> listar() {
        return trechoRepository.findAllByOrderByLogradouroAsc().stream()
                .map(trechoMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TrechoDTO buscar(Long id) {
        return trechoMapper.toDTO(buscarEntidade(id));
    }

    @Transactional
    public void salvar(TrechoDTO dto) {
        Bairro bairro = bairroRepository.findById(dto.getBairroId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Bairro não encontrado."));
        Trecho trecho = dto.getId() == null ? new Trecho() : buscarEntidade(dto.getId());
        trechoMapper.copyToEntity(dto, trecho, bairro);
        trechoRepository.save(trecho);
    }

    @Transactional
    public void excluir(Long id) {
        Trecho trecho = buscarEntidade(id);
        if (leituraRepository.existsByTrechoId(id)) {
            throw new RegraNegocioException("O trecho \"" + trecho.getLogradouro()
                    + "\" não pode ser excluído porque tem leituras cadastradas.");
        }
        trechoRepository.delete(trecho);
    }

    private Trecho buscarEntidade(Long id) {
        return trechoRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Trecho não encontrado."));
    }
}
