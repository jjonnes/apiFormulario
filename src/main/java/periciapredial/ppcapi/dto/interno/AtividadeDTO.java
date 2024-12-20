package periciapredial.ppcapi.dto.interno;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class AtividadeDTO {
    private Long id;

    @NotBlank
    private String descricao;

    private List<SubAtividadeDTO> subAtividades;

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

    public List<SubAtividadeDTO> getSubAtividades() {
        return subAtividades;
    }

    public void setSubAtividades(List<SubAtividadeDTO> subAtividades) {
        this.subAtividades = subAtividades;
    }
}
