package periciapredial.ppcapi.service.interno;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import periciapredial.ppcapi.model.interno.Funcionario;
import periciapredial.ppcapi.repository.interno.FuncionarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario getFuncionarioById(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com ID: " + id));
    }

    public Funcionario createFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> createMultipleFuncionarios(List<Funcionario> funcionarios) {
        return funcionarios
                .stream()
                .map(this::createFuncionario)
                .collect(Collectors.toList());
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionario) {
        Funcionario existingFuncionario = getFuncionarioById(id);
        existingFuncionario.setNome(funcionario.getNome());
        return funcionarioRepository.save(existingFuncionario);
    }

    public void deleteFuncionario(Long id) {
        Funcionario funcionario = getFuncionarioById(id);
        funcionarioRepository.delete(funcionario);
    }
}
