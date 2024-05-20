package es.unex.cum.tw.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;

import es.unex.cum.tw.models.CartaData;
import es.unex.cum.tw.models.ItemData;
import es.unex.cum.tw.repositories.CartaRepository;
import es.unex.cum.tw.repositories.CartaRepositoryJDBCImpl;

public class CartaServiceJDBCImpl implements CartaService{

    private final CartaRepository cartaRepository;

    public CartaServiceJDBCImpl() throws SQLException, NamingException{
        this.cartaRepository = new CartaRepositoryJDBCImpl();
    }


    @Override
    public boolean addProductoToCarta(int idUser, int idProducto) {
        return cartaRepository.addProductoToCarta(idUser, idProducto);
    }

    @Override
    public boolean deleteProductoToCarta(int idUser, int idProducto) {
        return cartaRepository.deleteProductoToCarta(idUser, idProducto);
    }

    @Override
    public Optional<CartaData> findCartaByIdUser(int idUser) {
        return cartaRepository.findCartaByUser(idUser);
    }

    @Override
    public List<ItemData> getProductos(int idCarta) {
        return cartaRepository.getProductos(idCarta);
    }

    @Override
    public List<CartaData> findAll() {
        return cartaRepository.findAll();
    }
}
