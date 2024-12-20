package periciapredial.ppcapi.model.interno;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "clientes")
public class Cliente {

  @Id
  private BigDecimal id;

  @Column(nullable = false)
  private String nome;

  @ManyToOne
  @JoinColumn(name = "grupo_id", nullable = false)
  private GrupoCliente grupo;

  @Column(nullable = false)
  private Integer sequencia;

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public GrupoCliente getGrupo() {
    return grupo;
  }

  public void setGrupo(GrupoCliente grupo) {
    this.grupo = grupo;
  }

  public Integer getSequencia() {
    return sequencia;
  }

  public void setSequencia(Integer sequencia) {
    this.sequencia = sequencia;
  }
}
