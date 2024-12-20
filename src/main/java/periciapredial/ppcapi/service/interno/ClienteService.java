package periciapredial.ppcapi.service.interno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import periciapredial.ppcapi.model.interno.Cliente;
import periciapredial.ppcapi.model.interno.GrupoCliente;
import periciapredial.ppcapi.repository.interno.ClienteRepository;
import periciapredial.ppcapi.repository.interno.GrupoClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private GrupoClienteRepository grupoClienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(BigDecimal id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com ID: " + id));
    }

    public Cliente createCliente(Cliente cliente, Long grupoId) {
        GrupoCliente grupo = grupoClienteRepository.findById(grupoId)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado com ID: " + grupoId));

        Integer sequenciaAtual = clienteRepository.findMaxSequenciaByGrupoId(grupoId);
        Integer novaSequencia = (sequenciaAtual != null) ? sequenciaAtual + 1 : 1;

        BigDecimal idCliente = new BigDecimal(grupoId + "." + novaSequencia);
        cliente.setId(idCliente);
        cliente.setSequencia(novaSequencia);
        cliente.setGrupo(grupo);

        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(BigDecimal id, Cliente cliente) {
        Cliente existingCliente = getClienteById(id);
        existingCliente.setNome(cliente.getNome());

        if (cliente.getGrupo() != null && cliente.getGrupo().getId() != null) {
            GrupoCliente gurpo = grupoClienteRepository.findById(cliente.getGrupo().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Grupo não encontrado com ID: " + cliente.getGrupo().getId()));
            existingCliente.setGrupo(gurpo);
        }

        return clienteRepository.save(existingCliente);
    }

    public void deleteCliente(BigDecimal id) {
        Cliente cliente = getClienteById(id);
        clienteRepository.delete(cliente);
    }
}
