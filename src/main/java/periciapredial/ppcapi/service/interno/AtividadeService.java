package periciapredial.ppcapi.service.interno;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import periciapredial.ppcapi.model.interno.Atividade;
import periciapredial.ppcapi.repository.interno.AtividadeRepository;

import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public List<Atividade> getAllAtividades() {
        return atividadeRepository.findAll();
    }

    public Atividade getAtividadeById(Long id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Atividade não encontrada com ID: " + id));
    }

    public Atividade createAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public Atividade updateAtividade(Long id, Atividade atividade) {
        Atividade existingAtividade = getAtividadeById(id);
        existingAtividade.setDescricao(atividade.getDescricao());
        return atividadeRepository.save(existingAtividade);
    }

    public void deleteAtividade(Long id) {
        Atividade atividade = getAtividadeById(id);
        atividadeRepository.delete(atividade);
    }
}
