package periciapredial.ppcapi.dto.interno;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ClienteDTO {
    private BigDecimal id;

    @NotBlank
    private String nome;

    private Long grupoId;

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

    public Long getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(Long grupoId) {
        this.grupoId = grupoId;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }
}
