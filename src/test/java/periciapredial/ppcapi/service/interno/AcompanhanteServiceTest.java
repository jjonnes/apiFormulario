package periciapredial.ppcapi.service.interno;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import periciapredial.ppcapi.model.interno.Acompanhante;
import periciapredial.ppcapi.repository.interno.AcompanhanteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AcompanhanteServiceTest {

  @InjectMocks
  private AcompanhanteService acompanhanteService;

  @Mock
  private AcompanhanteRepository acompanhanteRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllAcompanhantes() {
    Acompanhante acompanhante1 = new Acompanhante();
    Acompanhante acompanhante2 = new Acompanhante();
    List<Acompanhante> acompanhantes = Arrays.asList(acompanhante1, acompanhante2);

    when(acompanhanteRepository.findAll()).thenReturn(acompanhantes);

    List<Acompanhante> result = acompanhanteService.getAllAcompanhantes();

    assertEquals(2, result.size());
    verify(acompanhanteRepository, times(1)).findAll();
  }

  @Test
  public void testGetAcompanhanteById_Success() {
    Acompanhante acompanhante = new Acompanhante();
    acompanhante.setId(1L);

    when(acompanhanteRepository.findById(1L)).thenReturn(Optional.of(acompanhante));

    Acompanhante result = acompanhanteService.getAcompanhanteById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    verify(acompanhanteRepository, times(1)).findById(1L);
  }

  @Test
  public void testGetAcompanhanteById_NotFound() {
    when(acompanhanteRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      acompanhanteService.getAcompanhanteById(1L);
    });

    assertEquals("Entidade não encontrada com ID: 1", exception.getMessage());
    verify(acompanhanteRepository, times(1)).findById(1L);
  }

  @Test
  public void testCreateAcompanhante() {
    Acompanhante acompanhante = new Acompanhante();

    when(acompanhanteRepository.save(any(Acompanhante.class))).thenReturn(acompanhante);

    Acompanhante result = acompanhanteService.createAcompanhante(acompanhante);

    assertNotNull(result);
    verify(acompanhanteRepository, times(1)).save(acompanhante);
  }

  @Test
  public void testUpdateAcompanhante() {
    Acompanhante existingAcompanhante = new Acompanhante();
    existingAcompanhante.setId(1L);
    existingAcompanhante.setNome("Nome Antigo");

    Acompanhante updatedAcompanhante = new Acompanhante();
    updatedAcompanhante.setNome("Nome Novo");

    when(acompanhanteRepository.findById(1L)).thenReturn(Optional.of(existingAcompanhante));
    when(acompanhanteRepository.save(any(Acompanhante.class))).thenReturn(existingAcompanhante);

    Acompanhante result = acompanhanteService.updateAcompanhante(1L, updatedAcompanhante);

    assertEquals("Nome Novo", result.getNome());
    verify(acompanhanteRepository, times(1)).findById(1L);
    verify(acompanhanteRepository, times(1)).save(existingAcompanhante);
  }

  @Test
  public void testDeleteAcompanhante() {
    Acompanhante acompanhante = new Acompanhante();
    acompanhante.setId(1L);

    when(acompanhanteRepository.findById(1L)).thenReturn(Optional.of(acompanhante));

    assertDoesNotThrow(() -> {
      acompanhanteService.deleteAcompanhante(1L);
    });

    verify(acompanhanteRepository, times(1)).delete(acompanhante);
  }

  @Test
  public void testCreateMultipleAcompanhantes() {
    Acompanhante acompanhante1 = new Acompanhante();
    Acompanhante acompanhante2 = new Acompanhante();
    List<Acompanhante> acompanhantes = Arrays.asList(acompanhante1, acompanhante2);

    when(acompanhanteRepository.save(any(Acompanhante.class))).thenReturn(acompanhante1).thenReturn(acompanhante2);

    List<Acompanhante> result = acompanhanteService.createMultipleAcompanhantes(acompanhantes);

    assertEquals(2, result.size());
    verify(acompanhanteRepository, times(2)).save(any(Acompanhante.class));
  }
}