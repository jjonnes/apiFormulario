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
import periciapredial.ppcapi.model.interno.Funcionario;
import periciapredial.ppcapi.repository.interno.FuncionarioRepository;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

  @Mock
  private FuncionarioRepository funcionarioRepository;

  @InjectMocks
  private FuncionarioService funcionarioService;

  private Funcionario funcionario;

  @BeforeEach
  void setUp() {
    funcionario = new Funcionario();
    funcionario.setId(1L);
    funcionario.setNome("Funcionário Teste");
  }

  @Test
  void getAllFuncionarios_DeveRetornarListaDeFuncionarios() {

    List<Funcionario> funcionarios = Arrays.asList(funcionario);
    when(funcionarioRepository.findAll()).thenReturn(funcionarios);

    List<Funcionario> resultado = funcionarioService.getAllFuncionarios();

    assertNotNull(resultado);
    assertEquals(1, resultado.size());
    assertEquals(funcionario.getNome(), resultado.get(0).getNome());
    verify(funcionarioRepository).findAll();
  }

  @Test
  void getFuncionarioById_QuandoFuncionarioExiste_DeveRetornarFuncionario() {

    Long id = 1L;
    when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

    Funcionario resultado = funcionarioService.getFuncionarioById(id);

    assertNotNull(resultado);
    assertEquals(funcionario.getNome(), resultado.getNome());
    verify(funcionarioRepository).findById(id);
  }

  @Test
  void getFuncionarioById_QuandoFuncionarioNaoExiste_DeveLancarException() {

    Long id = 999L;
    when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> funcionarioService.getFuncionarioById(id));
    verify(funcionarioRepository).findById(id);
  }

  @Test
  void createFuncionario_QuandoDadosValidos_DeveCriarFuncionario() {

    Funcionario novoFuncionario = new Funcionario();
    novoFuncionario.setNome("Novo Funcionário");
    when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

    Funcionario resultado = funcionarioService.createFuncionario(novoFuncionario);

    assertNotNull(resultado);
    assertEquals(funcionario.getNome(), resultado.getNome());
    verify(funcionarioRepository).save(any(Funcionario.class));
  }

  @Test
  void createMultipleFuncionarios_QuandoDadosValidos_DeveCriarMultiplosFuncionarios() {

    Funcionario funcionario2 = new Funcionario();
    funcionario2.setId(2L);
    funcionario2.setNome("Funcionário Teste 2");

    List<Funcionario> funcionarios = Arrays.asList(funcionario, funcionario2);
    when(funcionarioRepository.save(any(Funcionario.class)))
        .thenReturn(funcionario)
        .thenReturn(funcionario2);

    List<Funcionario> resultado = funcionarioService.createMultipleFuncionarios(funcionarios);

    assertNotNull(resultado);
    assertEquals(2, resultado.size());
    assertEquals(funcionario.getNome(), resultado.get(0).getNome());
    assertEquals(funcionario2.getNome(), resultado.get(1).getNome());
    verify(funcionarioRepository, times(2)).save(any(Funcionario.class));
  }

  @Test
  void updateFuncionario_QuandoFuncionarioExiste_DeveAtualizarFuncionario() {

    Long id = 1L;
    Funcionario funcionarioAtualizado = new Funcionario();
    funcionarioAtualizado.setNome("Funcionário Atualizado");

    when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));
    when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionarioAtualizado);

    Funcionario resultado = funcionarioService.updateFuncionario(id, funcionarioAtualizado);

    assertNotNull(resultado);
    assertEquals(funcionarioAtualizado.getNome(), resultado.getNome());
    verify(funcionarioRepository).findById(id);
    verify(funcionarioRepository).save(any(Funcionario.class));
  }

  @Test
  void updateFuncionario_QuandoFuncionarioNaoExiste_DeveLancarException() {

    Long id = 999L;
    Funcionario funcionarioAtualizado = new Funcionario();
    funcionarioAtualizado.setNome("Funcionário Atualizado");

    when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> funcionarioService.updateFuncionario(id, funcionarioAtualizado));
    verify(funcionarioRepository).findById(id);
    verify(funcionarioRepository, never()).save(any(Funcionario.class));
  }

  @Test
  void deleteFuncionario_QuandoFuncionarioExiste_DeveDeletarFuncionario() {

    Long id = 1L;
    when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));
    doNothing().when(funcionarioRepository).delete(funcionario);

    funcionarioService.deleteFuncionario(id);

    verify(funcionarioRepository).findById(id);
    verify(funcionarioRepository).delete(funcionario);
  }

  @Test
  void deleteFuncionario_QuandoFuncionarioNaoExiste_DeveLancarException() {

    Long id = 999L;
    when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(EntityNotFoundException.class, () -> funcionarioService.deleteFuncionario(id));
    verify(funcionarioRepository).findById(id);
    verify(funcionarioRepository, never()).delete(any(Funcionario.class));
  }
}
