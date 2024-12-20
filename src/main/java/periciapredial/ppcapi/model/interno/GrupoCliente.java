package periciapredial.ppcapi.model.interno;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "grupos_clientes")
public class GrupoCliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Cliente> clientes;

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<Cliente> getClientes() {
    return clientes;
  }

  public void setClientes(List<Cliente> clientes) {
    this.clientes = clientes;
  }
}
