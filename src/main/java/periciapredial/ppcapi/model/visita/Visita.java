package periciapredial.ppcapi.model.visita;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visitas")
public class Visita {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String grupo;
  private String cliente;
  private String funcionarios;
  private String acompanhantes;
  private String subatividades;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHoraInicio;

  @Temporal(TemporalType.TIMESTAMP)
  private Date dataHoraFim;

  private String conteudoObs;
  private String conteudoVisita;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGrupo() {
    return grupo;
  }

  public void setGrupo(String grupo) {
    this.grupo = grupo;
  }

  public String getCliente() {
    return cliente;
  }

  public void setCliente(String cliente) {
    this.cliente = cliente;
  }

  public String getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(String funcionarios) {
    this.funcionarios = funcionarios;
  }

  public String getAcompanhantes() {
    return acompanhantes;
  }

  public void setAcompanhantes(String acompanhantes) {
    this.acompanhantes = acompanhantes;
  }

  public String getSubatividades() {
    return subatividades;
  }

  public void setSubatividades(String subatividades) {
    this.subatividades = subatividades;
  }

  public Date getDataHoraInicio() {
    return dataHoraInicio;
  }

  public void setDataHoraInicio(Date dataHoraInicio) {
    this.dataHoraInicio = dataHoraInicio;
  }

  public Date getDataHoraFim() {
    return dataHoraFim;
  }

  public void setDataHoraFim(Date dataHoraFim) {
    this.dataHoraFim = dataHoraFim;
  }

  public String getConteudoObs() {
    return conteudoObs;
  }

  public void setConteudoObs(String conteudoObs) {
    this.conteudoObs = conteudoObs;
  }

  public String getConteudoVisita() {
    return conteudoVisita;
  }

  public void setConteudoVisita(String conteudoVisita) {
    this.conteudoVisita = conteudoVisita;
  }

}