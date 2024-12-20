package periciapredial.ppcapi.service.interno;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import periciapredial.ppcapi.model.interno.Cliente;
import periciapredial.ppcapi.model.interno.GrupoCliente;
import periciapredial.ppcapi.repository.interno.ClienteRepository;
import periciapredial.ppcapi.repository.interno.GrupoClienteRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private GrupoClienteRepository grupoClienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private GrupoCliente grupoCliente;

    @BeforeEach
    void setUp() {
        grupoCliente = new GrupoCliente();
        grupoCliente.setId(1L);
        grupoCliente.setNome("Grupo Teste");

        cliente = new Cliente();
        cliente.setId(new BigDecimal("1.1"));
        cliente.setNome("Cliente Teste");
        cliente.setGrupo(grupoCliente);
        cliente.setSequencia(1);
    }

    @Test
    void getAllClientes_DeveRetornarListaDeClientes() {

        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> resultado = clienteService.getAllClientes();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(cliente.getNome(), resultado.get(0).getNome());
        verify(clienteRepository).findAll();
    }

    @Test
    void getClienteById_QuandoClienteExiste_DeveRetornarCliente() {

        BigDecimal id = new BigDecimal("1.1");
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.getClienteById(id);

        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.getNome());
        verify(clienteRepository).findById(id);
    }

    @Test
    void getClienteById_QuandoClienteNaoExiste_DeveLancarException() {

        BigDecimal id = new BigDecimal("999.999");
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteService.getClienteById(id));
        verify(clienteRepository).findById(id);
    }

    @Test
    void createCliente_QuandoDadosValidos_DeveCriarCliente() {

        Long grupoId = 1L;
        when(grupoClienteRepository.findById(grupoId)).thenReturn(Optional.of(grupoCliente));
        when(clienteRepository.findMaxSequenciaByGrupoId(grupoId)).thenReturn(0);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Novo Cliente");

        Cliente resultado = clienteService.createCliente(novoCliente, grupoId);

        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.getNome());
        verify(grupoClienteRepository).findById(grupoId);
        verify(clienteRepository).findMaxSequenciaByGrupoId(grupoId);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void createCliente_QuandoGrupoNaoExiste_DeveLancarException() {

        Long grupoId = 999L;
        when(grupoClienteRepository.findById(grupoId)).thenReturn(Optional.empty());

        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Novo Cliente");

        assertThrows(EntityNotFoundException.class, () -> clienteService.createCliente(novoCliente, grupoId));
        verify(grupoClienteRepository).findById(grupoId);
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void updateCliente_QuandoClienteExiste_DeveAtualizarCliente() {

        BigDecimal id = new BigDecimal("1.1");
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("Cliente Atualizado");
        clienteAtualizado.setGrupo(grupoCliente);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        when(grupoClienteRepository.findById(grupoCliente.getId())).thenReturn(Optional.of(grupoCliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = clienteService.updateCliente(id, clienteAtualizado);

        assertNotNull(resultado);
        assertEquals(clienteAtualizado.getNome(), resultado.getNome());
        verify(clienteRepository).findById(id);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    void deleteCliente_QuandoClienteExiste_DeveDeletarCliente() {

        BigDecimal id = new BigDecimal("1.1");
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        doNothing().when(clienteRepository).delete(cliente);

        clienteService.deleteCliente(id);

        verify(clienteRepository).findById(id);
        verify(clienteRepository).delete(cliente);
    }

    @Test
    void deleteCliente_QuandoClienteNaoExiste_DeveLancarException() {

        BigDecimal id = new BigDecimal("999.999");
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clienteService.deleteCliente(id));
        verify(clienteRepository).findById(id);
        verify(clienteRepository, never()).delete(any(Cliente.class));
    }
}
