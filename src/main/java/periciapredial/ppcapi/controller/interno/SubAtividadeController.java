package periciapredial.ppcapi.controller.interno;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import periciapredial.ppcapi.dto.interno.SubAtividadeDTO;
import periciapredial.ppcapi.mapper.interno.AtividadeMapper;
import periciapredial.ppcapi.model.interno.SubAtividade;
import periciapredial.ppcapi.service.interno.SubAtividadeService;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/subatividades")
public class SubAtividadeController {

    @Autowired
    private SubAtividadeService subAtividadeService;

    @Autowired
    private AtividadeMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubAtividadeDTO>> listarTodos() {
        List<SubAtividadeDTO> subAtividades = subAtividadeService.getAllSubAtividades()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subAtividades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubAtividadeDTO> buscarPorId(@PathVariable BigDecimal id) {
        SubAtividade subAtividade = subAtividadeService.getSubAtividadeById(id);
        return ResponseEntity.ok(mapper.toDTO(subAtividade));
    }

    @PostMapping("/{atividadeId}")
    public ResponseEntity<SubAtividadeDTO> salvar(@PathVariable long atividadeId,
            @Valid @RequestBody SubAtividadeDTO subAtividadeDTO) {
        SubAtividade subAtividade = mapper.toEntity(subAtividadeDTO);
        SubAtividade savedSubAtividade = subAtividadeService.createSubAtividade(subAtividade, atividadeId);
        return ResponseEntity.ok(mapper.toDTO(savedSubAtividade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubAtividadeDTO> atualizar(@PathVariable BigDecimal id,
            @Valid @RequestBody SubAtividadeDTO subAtividadeDTO) {
        SubAtividade subAtividade = mapper.toEntity(subAtividadeDTO);
        SubAtividade updatedSubAtividade = subAtividadeService.updateSubAtividade(id, subAtividade);
        return ResponseEntity.ok(mapper.toDTO(updatedSubAtividade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable BigDecimal id) {
        subAtividadeService.deleteSubAtividade(id);
        return ResponseEntity.noContent().build();
    }
}
