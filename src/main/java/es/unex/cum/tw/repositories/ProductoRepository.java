package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends Repository<Producto>{
    void cargarProductoABD();
    List<Producto> findAllProductoCSV();
    Optional<Producto> findProductoCSVById(int id);
}
