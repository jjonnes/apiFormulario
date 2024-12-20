package periciapredial.ppcapi.mapper.interno;

import org.springframework.stereotype.Component;

import periciapredial.ppcapi.dto.interno.AcompanhanteDTO;
import periciapredial.ppcapi.model.interno.Acompanhante;

@Component
public class AcompanhanteMapper {

    public AcompanhanteDTO toDTO(Acompanhante acompanhante) {
        if (acompanhante == null) {
            return null;
        }

        AcompanhanteDTO dto = new AcompanhanteDTO();
        dto.setId(acompanhante.getId());
        dto.setNome(acompanhante.getNome());
        return dto;
    }

    public Acompanhante toEntity(AcompanhanteDTO dto) {
        if (dto == null) {
            return null;
        }

        Acompanhante acompanhante = new Acompanhante();
        acompanhante.setId(dto.getId());
        acompanhante.setNome(dto.getNome());
        return acompanhante;
    }
}
