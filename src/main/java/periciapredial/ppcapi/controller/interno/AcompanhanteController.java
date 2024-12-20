package periciapredial.ppcapi.controller.interno;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import periciapredial.ppcapi.dto.interno.AcompanhanteDTO;
import periciapredial.ppcapi.mapper.interno.AcompanhanteMapper;
import periciapredial.ppcapi.model.interno.Acompanhante;
import periciapredial.ppcapi.service.interno.AcompanhanteService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/acompanhantes")
public class AcompanhanteController {

    @Autowired
    private AcompanhanteService acompanhanteService;

    @Autowired
    private AcompanhanteMapper mapper;

    @GetMapping
    public ResponseEntity<List<AcompanhanteDTO>> listarTodos() {
        List<AcompanhanteDTO> acompanhantes = acompanhanteService.getAllAcompanhantes()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(acompanhantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcompanhanteDTO> buscarPorId(@PathVariable Long id) {
        Acompanhante acompanhante = acompanhanteService.getAcompanhanteById(id);
        return ResponseEntity.ok(mapper.toDTO(acompanhante));
    }

    @PostMapping
    public ResponseEntity<AcompanhanteDTO> salvar(@Valid @RequestBody AcompanhanteDTO acompanhanteDTO) {
        Acompanhante acompanhante = mapper.toEntity(acompanhanteDTO);
        Acompanhante savedAcompanhante = acompanhanteService.createAcompanhante(acompanhante);
        return ResponseEntity.ok(mapper.toDTO(savedAcompanhante));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<AcompanhanteDTO>> salvarVarios(
            @Valid @RequestBody List<AcompanhanteDTO> acompanhanteDTO) {
        List<Acompanhante> acompanhante = acompanhanteDTO
                .stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        List<Acompanhante> savedAcompanhante = acompanhanteService.createMultipleAcompanhantes(acompanhante);
        List<AcompanhanteDTO> savedAcompanhanteDTO = savedAcompanhante
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(savedAcompanhanteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcompanhanteDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody AcompanhanteDTO acompanhanteDTO) {
        Acompanhante acompanhante = mapper.toEntity(acompanhanteDTO);
        Acompanhante updatedAcompanhante = acompanhanteService.updateAcompanhante(id, acompanhante);
        return ResponseEntity.ok(mapper.toDTO(updatedAcompanhante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        acompanhanteService.deleteAcompanhante(id);
        return ResponseEntity.noContent().build();
    }
}
