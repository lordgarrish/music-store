package lordgarrish.data;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDao<E, K> {

    boolean save(E entity) throws SQLException;
    E update(E entity) throws SQLException;
    boolean delete(K id) throws SQLException;
    List<E> getAll() throws SQLException;
    E getById(K id) throws SQLException;
}
