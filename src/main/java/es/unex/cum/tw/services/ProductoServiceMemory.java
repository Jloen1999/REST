package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductoServiceMemory {
    void cargarProductoABD();
    List<Producto> findAll() throws SQLException;
    Optional<Producto> findProductoById(int id);

}
