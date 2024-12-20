package periciapredial.ppcapi.service.interno;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import periciapredial.ppcapi.model.interno.Acompanhante;
import periciapredial.ppcapi.repository.interno.AcompanhanteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcompanhanteService {

    @Autowired
    private AcompanhanteRepository acompanhanteRepository;

    public List<Acompanhante> getAllAcompanhantes() {
        return acompanhanteRepository.findAll();
    }

    public Acompanhante getAcompanhanteById(Long id) {
        return acompanhanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Acompanhante não encontrado com ID: " + id));
    }

    public Acompanhante createAcompanhante(Acompanhante acompanhante) {
        return acompanhanteRepository.save(acompanhante);
    }

    public List<Acompanhante> createMultipleAcompanhantes(List<Acompanhante> acompanhante) {
        return acompanhante
                .stream()
                .map(this::createAcompanhante)
                .collect(Collectors.toList());
    }

    public Acompanhante updateAcompanhante(Long id, Acompanhante acompanhante) {
        Acompanhante existingAcompanhante = getAcompanhanteById(id);
        existingAcompanhante.setNome(acompanhante.getNome());
        return acompanhanteRepository.save(existingAcompanhante);
    }

    public void deleteAcompanhante(Long id) {
        Acompanhante acompanhante = getAcompanhanteById(id);
        acompanhanteRepository.delete(acompanhante);
    }
}
