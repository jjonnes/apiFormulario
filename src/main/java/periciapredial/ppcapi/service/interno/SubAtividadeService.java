package periciapredial.ppcapi.service.interno;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import periciapredial.ppcapi.model.interno.Atividade;
import periciapredial.ppcapi.model.interno.SubAtividade;
import periciapredial.ppcapi.repository.interno.AtividadeRepository;
import periciapredial.ppcapi.repository.interno.SubAtividadeRepository;

import java.util.List;
import java.math.BigDecimal;

@Service
public class SubAtividadeService {

    @Autowired
    private SubAtividadeRepository subAtividadeRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<SubAtividade> getAllSubAtividades() {
        return subAtividadeRepository.findAll();
    }

    public SubAtividade getSubAtividadeById(BigDecimal id) {
        return subAtividadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubAtividade não encontrada com ID: " + id));
    }

    public SubAtividade createSubAtividade(SubAtividade subAtividade, long atividadeId) {
        Atividade atividade = atividadeRepository.findById(atividadeId)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada"));

        Integer sequenciaAtual = subAtividadeRepository.findMaxSequenciaByAtividadeId(atividadeId);
        Integer novaSequencia = (sequenciaAtual != null) ? sequenciaAtual + 1 : 1;

        BigDecimal idSubAtividade = new BigDecimal(atividadeId + "." + novaSequencia);
        subAtividade.setId(idSubAtividade);
        subAtividade.setSequencia(novaSequencia);
        subAtividade.setAtividade(atividade);

        return subAtividadeRepository.save(subAtividade);
    }

    public SubAtividade updateSubAtividade(BigDecimal id, SubAtividade subAtividade) {
        SubAtividade existingSubAtividade = getSubAtividadeById(id);
        existingSubAtividade.setDescricao(subAtividade.getDescricao());

        if (subAtividade.getAtividade() != null && subAtividade.getAtividade().getId() != null) {
            Atividade atividade = atividadeRepository.findById(subAtividade.getAtividade().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Atividade não encontrada com ID: " + subAtividade.getAtividade().getId()));
            existingSubAtividade.setAtividade(atividade);
        }

        return subAtividadeRepository.save(existingSubAtividade);
    }

    public void deleteSubAtividade(BigDecimal id) {
        SubAtividade subAtividade = getSubAtividadeById(id);
        subAtividadeRepository.delete(subAtividade);
    }
}