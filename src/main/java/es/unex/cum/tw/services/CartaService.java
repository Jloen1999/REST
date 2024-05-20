package es.unex.cum.tw.services;

import es.unex.cum.tw.models.CartaData;
import es.unex.cum.tw.models.ItemData;

import java.util.List;
import java.util.Optional;

public interface CartaService {
    boolean addProductoToCarta(int idUser, int idProducto);
    boolean deleteProductoToCarta(int idUser, int idProducto);
    Optional<CartaData> findCartaByIdUser(int idUser);
    List<ItemData> getProductos(int idCarta);
    List<CartaData> findAll();

}
