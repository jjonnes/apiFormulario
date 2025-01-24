package periciapredial.ppcapi.service.interfaces;

import java.util.List;

public interface IGenericService<T, ID> {
  List<T> getAll();

  T getById(ID id);

  T create(T entity);

  List<T> createMultiple(List<T> entity);

  T update(ID id, T entity);

  void delete(ID id);
}