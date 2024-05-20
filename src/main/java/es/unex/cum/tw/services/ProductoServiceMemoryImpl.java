package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Producto;
import es.unex.cum.tw.repositories.ProductoRepository;
import es.unex.cum.tw.repositories.ProductoRepositoryJDBCImpl;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceMemoryImpl implements ProductoServiceMemory {

    private final ProductoRepository productoRepository;

    public ProductoServiceMemoryImpl() throws SQLException, NamingException {
        this.productoRepository = new ProductoRepositoryJDBCImpl();
    }

    @Override
    public void cargarProductoABD() {
        this.productoRepository.cargarProductoABD();
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        return this.productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findProductoById(int id) {
        return this.productoRepository.findProductoCSVById(id);
    }
}
