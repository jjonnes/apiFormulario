package periciapredial.ppcapi.service.abstracao;

import jakarta.persistence.EntityNotFoundException;
import periciapredial.ppcapi.service.interfaces.IGenericService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractService<T, ID> implements IGenericService<T, ID> {

  protected final JpaRepository<T, ID> repository;

  public AbstractService(JpaRepository<T, ID> repository) {
    this.repository = repository;
  }

  @Override
  public List<T> getAll() {
    return repository.findAll();
  }

  @Override
  public T getById(ID id) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada com ID: " + id));
  }

  @Override
  public T create(T entity) {
    return repository.save(entity);
  }

  @Override
  public List<T> createMultiple(List<T> entity) {
    return entity
        .stream()
        .map(this::create)
        .collect(Collectors.toList());
  }

  @Override
  public T update(ID id, T entity) {
    T existingEntity = getById(id);

    try {
      Method getNomeMethod = entity.getClass().getMethod("getNome");
      Method setNomeMethod = existingEntity.getClass().getMethod("setNome", String.class);
      String nome = (String) getNomeMethod.invoke(entity);
      setNomeMethod.invoke(existingEntity, nome);
      return repository.save(existingEntity);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(
          "O método getNome() ou setNome() não foi encontrado na entidade " + entity.getClass().getSimpleName(), e);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException("Erro ao invocar os métodos dinamicamente", e);
    }
  }

  @Override
  public void delete(ID id) {
    T entity = getById(id);
    repository.delete(entity);
  }
}
