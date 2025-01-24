package periciapredial.ppcapi.service.abstracao;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AbstractServiceTest {

  @InjectMocks
  private TestService testService; // TestService é uma classe de teste que estende AbstractService

  @Mock
  private JpaRepository<TestEntity, Long> testRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAll() {
    TestEntity entity1 = new TestEntity();
    TestEntity entity2 = new TestEntity();
    List<TestEntity> entities = Arrays.asList(entity1, entity2);

    when(testRepository.findAll()).thenReturn(entities);

    List<TestEntity> result = testService.getAll();

    assertEquals(2, result.size());
    verify(testRepository, times(1)).findAll();
  }

  @Test
  public void testGetById_Success() {
    TestEntity entity = new TestEntity();
    entity.setId(1L);

    when(testRepository.findById(1L)).thenReturn(Optional.of(entity));

    TestEntity result = testService.getById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    verify(testRepository, times(1)).findById(1L);
  }

  @Test
  public void testGetById_NotFound() {
    when(testRepository.findById(1L)).thenReturn(Optional.empty());

    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      testService.getById(1L);
    });

    assertEquals("Entidade não encontrada com ID: 1", exception.getMessage());
    verify(testRepository, times(1)).findById(1L);
  }

  @Test
  public void testCreate() {
    TestEntity entity = new TestEntity();

    when(testRepository.save(any(TestEntity.class))).thenReturn(entity);

    TestEntity result = testService.create(entity);

    assertNotNull(result);
    verify(testRepository, times(1)).save(entity);
  }

  @Test
  public void testUpdate() {
    TestEntity existingEntity = new TestEntity();
    existingEntity.setId(1L);
    existingEntity.setNome("Old Name");

    TestEntity updatedEntity = new TestEntity();
    updatedEntity.setNome("New Name");

    when(testRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
    when(testRepository.save(any(TestEntity.class))).thenReturn(existingEntity);

    TestEntity result = testService.update(1L, updatedEntity);

    assertEquals("New Name", result.getNome());
    verify(testRepository, times(1)).findById(1L);
    verify(testRepository, times(1)).save(existingEntity);
  }

  @Test
  public void testDelete() {
    TestEntity entity = new TestEntity();
    entity.setId(1L);

    when(testRepository.findById(1L)).thenReturn(Optional.of(entity));

    assertDoesNotThrow(() -> {
      testService.delete(1L);
    });

    verify(testRepository, times(1)).delete(entity);
  }

  // Classe de teste para simular a entidade
  private static class TestEntity {
    private Long id;
    private String nome;

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
  }

  // Classe de teste para simular o serviço
  private static class TestService extends AbstractService<TestEntity, Long> {
    public TestService(JpaRepository<TestEntity, Long> repository) {
      super(repository);
    }
  }
}