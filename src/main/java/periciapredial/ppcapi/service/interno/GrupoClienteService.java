package periciapredial.ppcapi.service.interno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import periciapredial.ppcapi.model.interno.GrupoCliente;
import periciapredial.ppcapi.repository.interno.GrupoClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GrupoClienteService {

    @Autowired
    private GrupoClienteRepository grupoClienteRepository;

    public List<GrupoCliente> getAllGrupos() {
        return grupoClienteRepository.findAll();
    }

    public GrupoCliente getGrupoById(Long id) {
        return grupoClienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado com ID: " + id));
    }

    public GrupoCliente createGrupo(GrupoCliente grupo) {
        return grupoClienteRepository.save(grupo);
    }

    public GrupoCliente updateGrupo(Long id, GrupoCliente grupo) {
        GrupoCliente existingGrupo = getGrupoById(id);
        existingGrupo.setNome(grupo.getNome());
        return grupoClienteRepository.save(existingGrupo);
    }

    public void deleteGrupo(Long id) {
        GrupoCliente grupo = getGrupoById(id);
        grupoClienteRepository.delete(grupo);
    }
}
