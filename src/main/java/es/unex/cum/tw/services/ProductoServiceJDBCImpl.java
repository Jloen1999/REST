package es.unex.cum.tw.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;

import es.unex.cum.tw.models.Producto;
import es.unex.cum.tw.repositories.ProductoRepository;
import es.unex.cum.tw.repositories.ProductoRepositoryJDBCImpl;

public class ProductoServiceJDBCImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    public ProductoServiceJDBCImpl() throws SQLException, NamingException{
        this.productoRepository = new ProductoRepositoryJDBCImpl();
    }


    @Override
    public Optional<Producto> findProductoById(int id) throws SQLException {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        return productoRepository.findAll();
    }

    @Override
    public boolean saveProducto(Producto producto) throws SQLException {
        return productoRepository.save(producto);
    }

    @Override
    public boolean update(Producto producto) throws SQLException {
        return productoRepository.update(producto);
    }

    @Override
    public boolean deleteProductoById(int id) throws SQLException {
        return productoRepository.deleteById(id);
    }

    @Override
    public boolean deleteProducto(Producto producto) throws SQLException {
        return productoRepository.delete(producto);
    }

}
