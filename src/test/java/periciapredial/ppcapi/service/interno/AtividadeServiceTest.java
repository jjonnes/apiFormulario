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
import periciapredial.ppcapi.model.interno.Atividade;
import periciapredial.ppcapi.repository.interno.AtividadeRepository;

@ExtendWith(MockitoExtension.class)
public class AtividadeServiceTest {

  @Mock
  private AtividadeRepository atividadeRepository;

  @InjectMocks
  private AtividadeService atividadeService;

  private Atividade atividade;

  @BeforeEach
  void setUp() {
    atividade = new Atividade();
    atividade.setId(1L);
    atividade.setDescricao("Atividade Teste");
  }

  @Test
  void getAllAtividades_DeveRetornarListaDeAtividades() {

    List<Atividade> atividades = Arrays.asList(atividade);
    when(atividadeRepository.findAll()).thenReturn(atividades);

    List<Atividade> resultado = atividadeService.getAllAtividades();

    assertNotNull(resultado);
    assertEquals(1, resultado.size());
    assertEquals(atividade.getDescricao(), resultado.get(0).getDescricao());
    verify(atividadeRepository).findAll();
  }

  @Test
  void getAtividadeById_QuandoAtividadeExiste_DeveRetornarAtividade() {

    Long id = 1L;
    when(atividadeRepository.findById(id)).thenReturn(Optional.of(atividade));

    Atividade resultado = atividadeService.getAtividadeById(id);

    assertNotNull(resultado);
    assertEquals(atividade.getDescricao(), resultado.getDescricao());
    verify(atividadeRepository).findById(id);
  }

  @Test
  void getAtividadeById_QuandoAtividadeNaoExiste_DeveLancarException() {

    Long id = 999L;
    when(atividadeRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> atividadeService.getAtividadeById(id));
    verify(atividadeRepository).findById(id);
  }

  @Test
  void createAtividade_QuandoDadosValidos_DeveCriarAtividade() {

    Atividade novaAtividade = new Atividade();
    novaAtividade.setDescricao("Nova Atividade");
    when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividade);

    Atividade resultado = atividadeService.createAtividade(novaAtividade);

    assertNotNull(resultado);
    assertEquals(atividade.getDescricao(), resultado.getDescricao());
    verify(atividadeRepository).save(any(Atividade.class));
  }

  @Test
  void updateAtividade_QuandoAtividadeExiste_DeveAtualizarAtividade() {

    Long id = 1L;
    Atividade atividadeAtualizada = new Atividade();
    atividadeAtualizada.setDescricao("Atividade Atualizada");

    when(atividadeRepository.findById(id)).thenReturn(Optional.of(atividade));
    when(atividadeRepository.save(any(Atividade.class))).thenReturn(atividadeAtualizada);

    Atividade resultado = atividadeService.updateAtividade(id, atividadeAtualizada);

    assertNotNull(resultado);
    assertEquals(atividadeAtualizada.getDescricao(), resultado.getDescricao());
    verify(atividadeRepository).findById(id);
    verify(atividadeRepository).save(any(Atividade.class));
  }

  @Test
  void updateAtividade_QuandoAtividadeNaoExiste_DeveLancarException() {

    Long id = 999L;
    Atividade atividadeAtualizada = new Atividade();
    atividadeAtualizada.setDescricao("Atividade Atualizada");

    when(atividadeRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> atividadeService.updateAtividade(id, atividadeAtualizada));
    verify(atividadeRepository).findById(id);
    verify(atividadeRepository, never()).save(any(Atividade.class));
  }

  @Test
  void deleteAtividade_QuandoAtividadeExiste_DeveDeletarAtividade() {

    Long id = 1L;
    when(atividadeRepository.findById(id)).thenReturn(Optional.of(atividade));
    doNothing().when(atividadeRepository).delete(atividade);

    atividadeService.deleteAtividade(id);

    verify(atividadeRepository).findById(id);
    verify(atividadeRepository).delete(atividade);
  }

  @Test
  void deleteAtividade_QuandoAtividadeNaoExiste_DeveLancarException() {

    Long id = 999L;
    when(atividadeRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> atividadeService.deleteAtividade(id));
    verify(atividadeRepository).findById(id);
    verify(atividadeRepository, never()).delete(any(Atividade.class));
  }
}
