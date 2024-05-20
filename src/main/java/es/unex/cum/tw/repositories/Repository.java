package es.unex.cum.tw.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    Optional<T> findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean save(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean deleteById(int id) throws SQLException;

    boolean delete(T t) throws SQLException;
}
