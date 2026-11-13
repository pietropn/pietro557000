package br.com.fiap.chuva.service;

import br.com.fiap.chuva.domain.Bairro;
import br.com.fiap.chuva.dto.BairroDTO;
import br.com.fiap.chuva.mapper.BairroMapper;
import br.com.fiap.chuva.repository.BairroRepository;
import br.com.fiap.chuva.repository.TrechoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BairroService {

    /** Valores fixos de zona (guardados como texto, sem enum). */
    public static final List<String> ZONAS = List.of("Centro", "Norte", "Sul", "Leste", "Oeste");

    private final BairroRepository bairroRepository;
    private final TrechoRepository trechoRepository;
    private final BairroMapper bairroMapper;

    /** Lista os bairros por nome, em ordem alfabetica. */
    @Transactional(readOnly = true)
    public List<BairroDTO> listar() {
        return bairroRepository.findAllByOrderByNomeAsc().stream()
                .map(bairroMapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public BairroDTO buscar(Long id) {
        return bairroMapper.toDTO(buscarEntidade(id));
    }

    /** Inclui (id nulo) ou altera (id preenchido) um bairro. */
    @Transactional
    public void salvar(BairroDTO dto) {
        Bairro bairro = dto.getId() == null ? new Bairro() : buscarEntidade(dto.getId());
        bairroMapper.copyToEntity(dto, bairro);
        bairroRepository.save(bairro);
    }

    /** R2: um bairro que tem trechos cadastrados nao pode ser excluido. */
    @Transactional
    public void excluir(Long id) {
        Bairro bairro = buscarEntidade(id);
        if (trechoRepository.existsByBairroId(id)) {
            throw new RegraNegocioException("O bairro \"" + bairro.getNome()
                    + "\" não pode ser excluído porque tem trechos cadastrados.");
        }
        bairroRepository.delete(bairro);
    }

    private Bairro buscarEntidade(Long id) {
        return bairroRepository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Bairro não encontrado."));
    }
}
