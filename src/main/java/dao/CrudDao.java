package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, ID> {

    List<T> findAll();

    T save(T entity);

}
