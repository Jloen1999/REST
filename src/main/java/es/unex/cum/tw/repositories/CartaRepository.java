package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.CartaData;
import es.unex.cum.tw.models.ItemData;

import java.util.List;
import java.util.Optional;

public interface CartaRepository extends Repository<CartaData>{
    boolean addProductoToCarta(int idUser, int idProducto);
    boolean deleteProductoToCarta(int idUser, int idProducto);
    Optional<CartaData> findCartaByUser(int idUser);
    List<ItemData> getProductos(int idCarta);
    List<CartaData> findAll();
}
