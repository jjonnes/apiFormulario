package periciapredial.ppcapi.service.interno;

import org.springframework.stereotype.Service;
import periciapredial.ppcapi.model.interno.GrupoCliente;
import periciapredial.ppcapi.repository.interno.GrupoClienteRepository;
import periciapredial.ppcapi.service.abstracao.AbstractService;
import java.util.List;

@Service
public class GrupoClienteService extends AbstractService<GrupoCliente, Long> {

    public GrupoClienteService(GrupoClienteRepository grupoClienteRepository) {
        super(grupoClienteRepository);
    }

    public List<GrupoCliente> getAllGrupos() {
        return super.getAll();
    }

    public GrupoCliente getGrupoById(Long id) {
        return super.getById(id);
    }

    public GrupoCliente createGrupo(GrupoCliente grupo) {
        return super.create(grupo);
    }

    public List<GrupoCliente> createMultipleGrupo(List<GrupoCliente> grupo) {
        return super.createMultiple(grupo);
    }

    public GrupoCliente updateGrupo(Long id, GrupoCliente grupo) {
        return super.update(id, grupo);
    }

    public void deleteGrupo(Long id) {
        super.delete(id);
    }
}
