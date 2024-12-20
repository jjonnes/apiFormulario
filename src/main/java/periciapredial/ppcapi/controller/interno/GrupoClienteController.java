package periciapredial.ppcapi.controller.interno;

import periciapredial.ppcapi.dto.interno.GrupoClienteDTO;
import periciapredial.ppcapi.mapper.interno.ClienteMapper;
import periciapredial.ppcapi.model.interno.GrupoCliente;
import periciapredial.ppcapi.service.interno.GrupoClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/grupocliente")
public class GrupoClienteController {

    @Autowired
    private GrupoClienteService grupoClienteService;

    @Autowired
    private ClienteMapper mapper;

    @GetMapping
    public ResponseEntity<List<GrupoClienteDTO>> listarTodos() {
        List<GrupoClienteDTO> grupos = grupoClienteService.getAllGrupos()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(grupos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoClienteDTO> buscarPorId(@PathVariable Long id) {
        GrupoCliente grupo = grupoClienteService.getGrupoById(id);
        return ResponseEntity.ok(mapper.toDTO(grupo));
    }

    @PostMapping
    public ResponseEntity<GrupoClienteDTO> salvar(@Valid @RequestBody GrupoClienteDTO grupoDTO) {
        GrupoCliente grupo = mapper.toEntity(grupoDTO);
        GrupoCliente savedGrupo = grupoClienteService.createGrupo(grupo);
        return ResponseEntity.ok(mapper.toDTO(savedGrupo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoClienteDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody GrupoClienteDTO grupoDTO) {
        GrupoCliente grupo = mapper.toEntity(grupoDTO);
        GrupoCliente updatedGrupo = grupoClienteService.updateGrupo(id, grupo);
        return ResponseEntity.ok(mapper.toDTO(updatedGrupo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        grupoClienteService.deleteGrupo(id);
        return ResponseEntity.noContent().build();
    }
}