package periciapredial.ppcapi.mapper.interno;

import periciapredial.ppcapi.dto.interno.ClienteDTO;
import periciapredial.ppcapi.dto.interno.GrupoClienteDTO;
import periciapredial.ppcapi.model.interno.Cliente;
import periciapredial.ppcapi.model.interno.GrupoCliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setSequencia(cliente.getSequencia());
        if (cliente.getGrupo() != null) {
            dto.setGrupoId(cliente.getGrupo().getId());
        }
        return dto;
    }

    public GrupoClienteDTO toDTO(GrupoCliente grupo) {
        if (grupo == null) {
            return null;
        }

        GrupoClienteDTO dto = new GrupoClienteDTO();
        dto.setId(grupo.getId());
        dto.setNome(grupo.getNome());
        if (grupo.getClientes() != null) {
            List<ClienteDTO> clienteDTOs = grupo.getClientes()
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
            dto.setClientes(clienteDTOs);
        }

        return dto;
    }

    public Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setSequencia(dto.getSequencia());
        return cliente;
    }

    public GrupoCliente toEntity(GrupoClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        GrupoCliente grupo = new GrupoCliente();
        grupo.setId(dto.getId());
        grupo.setNome(dto.getNome());
        return grupo;
    }
}
