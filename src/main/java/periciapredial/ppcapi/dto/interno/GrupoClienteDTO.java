package periciapredial.ppcapi.dto.interno;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class GrupoClienteDTO {
    private Long id;

    @NotBlank
    private String nome;

    private List<ClienteDTO> clientes;

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

    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteDTO> clientes) {
        this.clientes = clientes;
    }
}
