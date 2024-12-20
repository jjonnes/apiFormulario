package periciapredial.ppcapi.controller.visita;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import periciapredial.ppcapi.dto.visita.VisitaDTO;
import periciapredial.ppcapi.mapper.visita.VisitaMapper;
import periciapredial.ppcapi.model.visita.Visita;
import periciapredial.ppcapi.service.visita.VisitaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/visitas")
public class VisitaController {

  @Autowired
  private VisitaService visitaService;

  @Autowired
  private VisitaMapper mapper;

  @GetMapping
  public ResponseEntity<List<VisitaDTO>> listarTodos() {
    List<VisitaDTO> visitas = visitaService.getAllVisitas()
        .stream()
        .map(mapper::toDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(visitas);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VisitaDTO> buscarPorId(@PathVariable Long id) {
    Visita visita = visitaService.getVisitaById(id);
    if (visita == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(mapper.toDTO(visita));
  }

  @PostMapping
  public ResponseEntity<VisitaDTO> salvar(@Valid @RequestBody VisitaDTO visitaDTO) {
    Visita visita = mapper.toEntity(visitaDTO);
    Visita savedVisita = visitaService.createVisita(visita);
    return ResponseEntity.ok(mapper.toDTO(savedVisita));
  }

  @PutMapping("/{id}")
  public ResponseEntity<VisitaDTO> atualizar(@PathVariable Long id,
      @Valid @RequestBody VisitaDTO visitaDTO) {
    Visita visita = mapper.toEntity(visitaDTO);
    Visita updatedVisita = visitaService.updateVisita(id, visita);
    if (updatedVisita == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(mapper.toDTO(updatedVisita));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable Long id) {
    visitaService.deleteVisita(id);
    return ResponseEntity.noContent().build();
  }
}