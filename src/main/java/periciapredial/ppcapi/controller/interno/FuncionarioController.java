package periciapredial.ppcapi.controller.interno;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import periciapredial.ppcapi.dto.interno.FuncionarioDTO;
import periciapredial.ppcapi.mapper.interno.FuncionarioMapper;
import periciapredial.ppcapi.model.interno.Funcionario;
import periciapredial.ppcapi.service.interno.FuncionarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioMapper mapper;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodos() {
        List<FuncionarioDTO> funcionarios = funcionarioService.getAllFuncionarios()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.getFuncionarioById(id);
        return ResponseEntity.ok(mapper.toDTO(funcionario));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvar(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = mapper.toEntity(funcionarioDTO);
        Funcionario savedFuncionario = funcionarioService.createFuncionario(funcionario);
        return ResponseEntity.ok(mapper.toDTO(savedFuncionario));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<FuncionarioDTO>> salvarVarios(@Valid @RequestBody List<FuncionarioDTO> funcionariosDTO) {
        List<Funcionario> funcionarios = funcionariosDTO
                .stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        List<Funcionario> savedFuncionarios = funcionarioService.createMultipleFuncionarios(funcionarios);
        List<FuncionarioDTO> savedFuncionariosDTO = savedFuncionarios
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(savedFuncionariosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = mapper.toEntity(funcionarioDTO);
        Funcionario updatedFuncionario = funcionarioService.updateFuncionario(id, funcionario);
        return ResponseEntity.ok(mapper.toDTO(updatedFuncionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
