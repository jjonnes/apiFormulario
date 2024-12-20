package periciapredial.ppcapi.mapper.interno;

import periciapredial.ppcapi.dto.interno.AtividadeDTO;
import periciapredial.ppcapi.dto.interno.SubAtividadeDTO;
import periciapredial.ppcapi.model.interno.Atividade;
import periciapredial.ppcapi.model.interno.SubAtividade;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class AtividadeMapper {

    public AtividadeDTO toDTO(Atividade atividade) {
        if (atividade == null) {
            return null;
        }

        AtividadeDTO dto = new AtividadeDTO();
        dto.setId(atividade.getId());
        dto.setDescricao(atividade.getDescricao());
        if (atividade.getSubAtividades() != null) {
            List<SubAtividadeDTO> subAtividadeDTOs = atividade.getSubAtividades()
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
            dto.setSubAtividades(subAtividadeDTOs);
        }

        return dto;
    }

    public SubAtividadeDTO toDTO(SubAtividade subAtividade) {
        if (subAtividade == null) {
            return null;
        }

        SubAtividadeDTO dto = new SubAtividadeDTO();
        dto.setId(subAtividade.getId());
        dto.setDescricao(subAtividade.getDescricao());
        dto.setSequencia(subAtividade.getSequencia());
        if (subAtividade.getAtividade() != null) {
            dto.setAtividadeId(subAtividade.getAtividade().getId());
        }
        return dto;
    }

    public Atividade toEntity(AtividadeDTO dto) {
        if (dto == null) {
            return null;
        }

        Atividade atividade = new Atividade();
        atividade.setId(dto.getId());
        atividade.setDescricao(dto.getDescricao());
        return atividade;
    }

    public SubAtividade toEntity(SubAtividadeDTO dto) {
        if (dto == null) {
            return null;
        }

        SubAtividade subAtividade = new SubAtividade();
        subAtividade.setId(dto.getId());
        subAtividade.setDescricao(dto.getDescricao());
        subAtividade.setSequencia(dto.getSequencia());
        return subAtividade;
    }
}
