package periciapredial.ppcapi.service.interno;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import periciapredial.ppcapi.model.interno.SubAtividade;
import periciapredial.ppcapi.model.interno.Atividade;
import periciapredial.ppcapi.repository.interno.SubAtividadeRepository;
import periciapredial.ppcapi.repository.interno.AtividadeRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SubAtividadeServiceTest {

  @InjectMocks
  private SubAtividadeService subAtividadeService;

  @Mock
  private SubAtividadeRepository subAtividadeRepository;

  @Mock
  private AtividadeRepository atividadeRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllSubAtividades() {
    SubAtividade subAtividade1 = new SubAtividade();
    SubAtividade subAtividade2 = new SubAtividade();
    List<SubAtividade> subAtividades = Arrays.asList(subAtividade1, subAtividade2);

    when(subAtividadeRepository.findAll()).thenReturn(subAtividades);

    List<SubAtividade> result = subAtividadeService.getAllSubAtividades();

    assertEquals(2, result.size());
    verify(subAtividadeRepository, times(1)).findAll();
  }

  @Test
  public void testGetSubAtividadeById_Success() {
    SubAtividade subAtividade = new SubAtividade();
    subAtividade.setId(BigDecimal.valueOf(1));

    when(subAtividadeRepository.findById(BigDecimal.valueOf(1))).thenReturn(Optional.of(subAtividade));

    SubAtividade result = subAtividadeService.getSubAtividadeById(BigDecimal.valueOf(1));

    assertNotNull(result);
    assertEquals(BigDecimal.valueOf(1), result.getId());
    verify(subAtividadeRepository, times(1)).findById(BigDecimal.valueOf(1));
  }

  @Test
  public void testGetSubAtividadeById_NotFound() {
    when(subAtividadeRepository.findById(BigDecimal.valueOf(1))).thenReturn(Optional.empty());

    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      subAtividadeService.getSubAtividadeById(BigDecimal.valueOf(1));
    });

    assertEquals("SubAtividade não encontrada com ID: 1", exception.getMessage());
    verify(subAtividadeRepository, times(1)).findById(BigDecimal.valueOf(1));
  }

  @Test
  public void testCreateSubAtividade() {
    SubAtividade subAtividade = new SubAtividade();
    Atividade atividade = new Atividade();
    atividade.setId(1L);

    when(atividadeRepository.findById(1L)).thenReturn(Optional.of(atividade));
    when(subAtividadeRepository.save(any(SubAtividade.class))).thenReturn(subAtividade);

    SubAtividade result = subAtividadeService.createSubAtividade(subAtividade, 1L);

    assertNotNull(result);
    verify(subAtividadeRepository, times(1)).save(subAtividade);
  }

  @Test
  public void testUpdateSubAtividade() {
    SubAtividade existingSubAtividade = new SubAtividade();
    existingSubAtividade.setId(BigDecimal.valueOf(1));
    existingSubAtividade.setDescricao("Descrição Antiga");

    SubAtividade updatedSubAtividade = new SubAtividade();
    updatedSubAtividade.setDescricao("Descrição Nova");

    when(subAtividadeRepository.findById(BigDecimal.valueOf(1))).thenReturn(Optional.of(existingSubAtividade));
    when(subAtividadeRepository.save(any(SubAtividade.class))).thenReturn(existingSubAtividade);

    SubAtividade result = subAtividadeService.updateSubAtividade(BigDecimal.valueOf(1), updatedSubAtividade);

    assertEquals("Descrição Nova", result.getDescricao());
    verify(subAtividadeRepository, times(1)).findById(BigDecimal.valueOf(1));
    verify(subAtividadeRepository, times(1)).save(existingSubAtividade);
  }

  @Test
  public void testDeleteSubAtividade() {
    SubAtividade subAtividade = new SubAtividade();
    subAtividade.setId(BigDecimal.valueOf(1));

    when(subAtividadeRepository.findById(BigDecimal.valueOf(1))).thenReturn(Optional.of(subAtividade));

    assertDoesNotThrow(() -> {
      subAtividadeService.deleteSubAtividade(BigDecimal.valueOf(1));
    });

    verify(subAtividadeRepository, times(1)).delete(subAtividade);
  }
}
