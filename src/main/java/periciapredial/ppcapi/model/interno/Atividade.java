package periciapredial.ppcapi.model.interno;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubAtividade> subAtividades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<SubAtividade> getSubAtividades() {
        return subAtividades;
    }

    public void setSubAtividades(List<SubAtividade> subAtividades) {
        this.subAtividades = subAtividades;
    }
}
