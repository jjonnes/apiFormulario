package periciapredial.ppcapi.service.interno;

import org.springframework.stereotype.Service;
import periciapredial.ppcapi.model.interno.Funcionario;
import periciapredial.ppcapi.repository.interno.FuncionarioRepository;
import periciapredial.ppcapi.service.abstracao.AbstractService;

import java.util.List;

@Service
public class FuncionarioService extends AbstractService<Funcionario, Long> {

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        super(funcionarioRepository);
    }

    public List<Funcionario> getAllFuncionarios() {
        return super.getAll();
    }

    public Funcionario getFuncionarioById(Long id) {
        return super.getById(id);
    }

    public Funcionario createFuncionario(Funcionario funcionario) {
        return super.create(funcionario);
    }

    public List<Funcionario> createMultipleFuncionarios(List<Funcionario> funcionarios) {
        return super.createMultiple(funcionarios);
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionario) {
        return super.update(id, funcionario);
    }

    public void deleteFuncionario(Long id) {
        super.delete(id);
        ;
    }
}
