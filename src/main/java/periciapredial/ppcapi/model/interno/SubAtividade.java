package periciapredial.ppcapi.model.interno;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "sub_atividades")
public class SubAtividade {

    @Id
    private BigDecimal id;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    @Column(nullable = false)
    private Integer sequencia;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }
}
