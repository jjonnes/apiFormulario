package periciapredial.ppcapi.controller.interno;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import periciapredial.ppcapi.dto.interno.AtividadeDTO;
import periciapredial.ppcapi.mapper.interno.AtividadeMapper;
import periciapredial.ppcapi.model.interno.Atividade;
import periciapredial.ppcapi.service.interno.AtividadeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private AtividadeMapper mapper;

    @GetMapping
    public ResponseEntity<List<AtividadeDTO>> listarTodos() {
        List<AtividadeDTO> atividades = atividadeService.getAllAtividades()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeDTO> buscarPorId(@PathVariable Long id) {
        Atividade atividade = atividadeService.getAtividadeById(id);
        return ResponseEntity.ok(mapper.toDTO(atividade));
    }

    @PostMapping
    public ResponseEntity<AtividadeDTO> salvar(@Valid @RequestBody AtividadeDTO atividadeDTO) {
        Atividade atividade = mapper.toEntity(atividadeDTO);
        Atividade savedAtividade = atividadeService.createAtividade(atividade);
        return ResponseEntity.ok(mapper.toDTO(savedAtividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtividadeDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody AtividadeDTO atividadeDTO) {
        Atividade atividade = mapper.toEntity(atividadeDTO);
        Atividade updatedAtividade = atividadeService.updateAtividade(id, atividade);
        return ResponseEntity.ok(mapper.toDTO(updatedAtividade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }
}
