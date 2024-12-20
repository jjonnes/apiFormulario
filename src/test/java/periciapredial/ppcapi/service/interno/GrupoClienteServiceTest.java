package periciapredial.ppcapi.service.interno;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import periciapredial.ppcapi.model.interno.GrupoCliente;
import periciapredial.ppcapi.repository.interno.GrupoClienteRepository;

@ExtendWith(MockitoExtension.class)
public class GrupoClienteServiceTest {

  @Mock
  private GrupoClienteRepository grupoClienteRepository;

  @InjectMocks
  private GrupoClienteService grupoClienteService;

  private GrupoCliente grupoCliente;

  @BeforeEach
  void setUp() {
    grupoCliente = new GrupoCliente();
    grupoCliente.setId(1L);
    grupoCliente.setNome("Grupo Teste");
  }

  @Test
  void getAllGrupos_DeveRetornarListaDeGrupos() {

    List<GrupoCliente> grupos = Arrays.asList(grupoCliente);
    when(grupoClienteRepository.findAll()).thenReturn(grupos);

    List<GrupoCliente> resultado = grupoClienteService.getAllGrupos();

    assertNotNull(resultado);
    assertEquals(1, resultado.size());
    assertEquals(grupoCliente.getNome(), resultado.get(0).getNome());
    verify(grupoClienteRepository).findAll();
  }

  @Test
  void getGrupoById_QuandoGrupoExiste_DeveRetornarGrupo() {

    Long id = 1L;
    when(grupoClienteRepository.findById(id)).thenReturn(Optional.of(grupoCliente));

    GrupoCliente resultado = grupoClienteService.getGrupoById(id);

    assertNotNull(resultado);
    assertEquals(grupoCliente.getNome(), resultado.getNome());
    verify(grupoClienteRepository).findById(id);
  }

  @Test
  void getGrupoById_QuandoGrupoNaoExiste_DeveLancarException() {

    Long id = 999L;
    when(grupoClienteRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> grupoClienteService.getGrupoById(id));
    verify(grupoClienteRepository).findById(id);
  }

  @Test
  void createGrupo_QuandoDadosValidos_DeveCriarGrupo() {

    GrupoCliente novoGrupo = new GrupoCliente();
    novoGrupo.setNome("Novo Grupo");
    when(grupoClienteRepository.save(any(GrupoCliente.class))).thenReturn(grupoCliente);

    GrupoCliente resultado = grupoClienteService.createGrupo(novoGrupo);

    assertNotNull(resultado);
    assertEquals(grupoCliente.getNome(), resultado.getNome());
    verify(grupoClienteRepository).save(any(GrupoCliente.class));
  }

  @Test
  void updateGrupo_QuandoGrupoExiste_DeveAtualizarGrupo() {

    Long id = 1L;
    GrupoCliente grupoAtualizado = new GrupoCliente();
    grupoAtualizado.setNome("Grupo Atualizado");

    when(grupoClienteRepository.findById(id)).thenReturn(Optional.of(grupoCliente));
    when(grupoClienteRepository.save(any(GrupoCliente.class))).thenReturn(grupoAtualizado);

    GrupoCliente resultado = grupoClienteService.updateGrupo(id, grupoAtualizado);

    assertNotNull(resultado);
    assertEquals(grupoAtualizado.getNome(), resultado.getNome());
    verify(grupoClienteRepository).findById(id);
    verify(grupoClienteRepository).save(any(GrupoCliente.class));
  }

  @Test
  void updateGrupo_QuandoGrupoNaoExiste_DeveLancarException() {

    Long id = 999L;
    GrupoCliente grupoAtualizado = new GrupoCliente();
    grupoAtualizado.setNome("Grupo Atualizado");

    when(grupoClienteRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> grupoClienteService.updateGrupo(id, grupoAtualizado));
    verify(grupoClienteRepository).findById(id);
    verify(grupoClienteRepository, never()).save(any(GrupoCliente.class));
  }

  @Test
  void deleteGrupo_QuandoGrupoExiste_DeveDeletarGrupo() {

    Long id = 1L;
    when(grupoClienteRepository.findById(id)).thenReturn(Optional.of(grupoCliente));
    doNothing().when(grupoClienteRepository).delete(grupoCliente);

    grupoClienteService.deleteGrupo(id);

    verify(grupoClienteRepository).findById(id);
    verify(grupoClienteRepository).delete(grupoCliente);
  }

  @Test
  void deleteGrupo_QuandoGrupoNaoExiste_DeveLancarException() {

    Long id = 999L;
    when(grupoClienteRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> grupoClienteService.deleteGrupo(id));
    verify(grupoClienteRepository).findById(id);
    verify(grupoClienteRepository, never()).delete(any(GrupoCliente.class));
  }
}
