package periciapredial.ppcapi.controller.interno;

import periciapredial.ppcapi.dto.interno.ClienteDTO;
import periciapredial.ppcapi.mapper.interno.ClienteMapper;
import periciapredial.ppcapi.model.interno.Cliente;
import periciapredial.ppcapi.service.interno.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteDTO> clientes = clienteService.getAllClientes()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable BigDecimal id) {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(mapper.toDTO(cliente));
    }

    @PostMapping("/{grupoId}")
    public ResponseEntity<ClienteDTO> salvar(@PathVariable Long grupoId,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = mapper.toEntity(clienteDTO);
        Cliente savedCliente = clienteService.createCliente(cliente, grupoId);
        return ResponseEntity.ok(mapper.toDTO(savedCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable BigDecimal id,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = mapper.toEntity(clienteDTO);
        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(mapper.toDTO(updatedCliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable BigDecimal id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}