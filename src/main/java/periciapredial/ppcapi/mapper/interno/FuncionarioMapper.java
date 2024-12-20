package periciapredial.ppcapi.mapper.interno;

import org.springframework.stereotype.Component;

import periciapredial.ppcapi.dto.interno.FuncionarioDTO;
import periciapredial.ppcapi.model.interno.Funcionario;

@Component
public class FuncionarioMapper {

    public FuncionarioDTO toDTO(Funcionario funcionario) {
        if (funcionario == null) {
            return null;
        }

        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        return dto;
    }

    public Funcionario toEntity(FuncionarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setId(dto.getId());
        funcionario.setNome(dto.getNome());
        return funcionario;
    }
}
