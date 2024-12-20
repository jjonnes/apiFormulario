package periciapredial.ppcapi.dto.interno;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class SubAtividadeDTO {
    private BigDecimal id;

    @NotBlank
    private String descricao;

    private Long atividadeId;

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

    public Long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Long atividadeId) {
        this.atividadeId = atividadeId;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }
}