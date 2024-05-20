package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    Optional<Producto> findProductoById(int id) throws SQLException;

    List<Producto> findAll() throws SQLException;

    boolean saveProducto(Producto producto) throws SQLException;

    boolean update(Producto producto) throws SQLException;

    boolean deleteProductoById(int id) throws SQLException;

    boolean deleteProducto(Producto producto) throws SQLException;


}
