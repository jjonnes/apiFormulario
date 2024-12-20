package periciapredial.ppcapi.mapper.visita;

import org.springframework.stereotype.Component;
import periciapredial.ppcapi.dto.visita.VisitaDTO;
import periciapredial.ppcapi.model.visita.Visita;

@Component
public class VisitaMapper {

  public VisitaDTO toDTO(Visita visita) {
    if (visita == null) {
      return null;
    }

    VisitaDTO dto = new VisitaDTO();
    dto.setId(visita.getId());
    dto.setGrupo(visita.getGrupo());
    dto.setCliente(visita.getCliente());
    dto.setFuncionarios(visita.getFuncionarios());
    dto.setAcompanhantes(visita.getAcompanhantes());
    dto.setSubatividades(visita.getSubatividades());
    dto.setDataHoraInicio(visita.getDataHoraInicio());
    dto.setDataHoraFim(visita.getDataHoraFim());
    dto.setConteudoObs(visita.getConteudoObs());
    dto.setConteudoVisita(visita.getConteudoVisita());

    return dto;
  }

  public Visita toEntity(VisitaDTO dto) {
    if (dto == null) {
      return null;
    }

    Visita visita = new Visita();
    visita.setId(dto.getId());
    visita.setGrupo(dto.getGrupo());
    visita.setCliente(dto.getCliente());
    visita.setFuncionarios(dto.getFuncionarios());
    visita.setAcompanhantes(dto.getAcompanhantes());
    visita.setSubatividades(dto.getSubatividades());
    visita.setDataHoraInicio(dto.getDataHoraInicio());
    visita.setDataHoraFim(dto.getDataHoraFim());
    visita.setConteudoObs(dto.getConteudoObs());
    visita.setConteudoVisita(dto.getConteudoVisita());

    return visita;
  }
}