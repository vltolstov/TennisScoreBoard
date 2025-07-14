package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, ID> {

    T save(T entity);

}
