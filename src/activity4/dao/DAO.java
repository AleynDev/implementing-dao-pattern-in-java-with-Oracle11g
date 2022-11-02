package activity4.dao;

import java.util.List;

public interface DAO<T, K> {

    void insert(T a) throws DAOException;

    void update(T a) throws DAOException;

    void delete(T a) throws DAOException;

    T getById(K id) throws DAOException;

    List<T> getAll() throws DAOException;

}
