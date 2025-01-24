package periciapredial.ppcapi.service.interno;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import periciapredial.ppcapi.model.interno.Acompanhante;
import periciapredial.ppcapi.repository.interno.AcompanhanteRepository;
import periciapredial.ppcapi.service.abstracao.AbstractService;

import java.util.List;

@Service
public class AcompanhanteService extends AbstractService<Acompanhante, Long> {

    // @Autowired
    public AcompanhanteService(AcompanhanteRepository acompanhanteRepository) {
        super(acompanhanteRepository);
    }

    public List<Acompanhante> getAllAcompanhantes() {
        return super.getAll();
    }

    public Acompanhante getAcompanhanteById(Long id) {
        return super.getById(id);
    }

    public Acompanhante createAcompanhante(Acompanhante acompanhante) {
        return super.create(acompanhante);
    }

    public List<Acompanhante> createMultipleAcompanhantes(List<Acompanhante> acompanhante) {
        return super.createMultiple(acompanhante);
    }

    public Acompanhante updateAcompanhante(Long id, Acompanhante acompanhante) {
        return super.update(id, acompanhante);
    }

    public void deleteAcompanhante(Long id) {
        super.delete(id);
        ;
    }
}
