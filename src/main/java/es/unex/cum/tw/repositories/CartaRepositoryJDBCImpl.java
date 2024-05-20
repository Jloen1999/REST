package es.unex.cum.tw.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;

import es.unex.cum.tw.models.CartaData;
import es.unex.cum.tw.models.ItemData;
import es.unex.cum.tw.util.ConexionBD_DSPool;

public class CartaRepositoryJDBCImpl implements CartaRepository {

	private final Connection conn;

	public CartaRepositoryJDBCImpl() throws SQLException, NamingException {
		conn = ConexionBD_DSPool.getConexionBD();
	}

	@Override
	public Optional<CartaData> findById(int idCarta) throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM cartadata WHERE idCartaData = ?")) {
			ps.setInt(1, idCarta);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return Optional.of(new CartaData(rs.getInt("idUsuario")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public List<CartaData> findAll() {
		List<CartaData> cartas = new ArrayList<CartaData>();
		List<ItemData> productos = new ArrayList<ItemData>();
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM cartadata")) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cartas.add(new CartaData(rs.getInt("idUsuario")));
				// Obtener los productos de la carta
				try (PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM itemdata WHERE idCartaData = ?")) {
					ps2.setInt(1, rs.getInt("idCartaData"));
					ResultSet rs2 = ps2.executeQuery();
					while (rs2.next()) {
						productos.add(new ItemData(rs2.getInt("idItemData"), rs2.getInt("idProducto"),
								rs2.getInt("idCartaData"), rs2.getInt("cantidad")));
					}
					cartas.getLast().setlProductos(productos);
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartas;
	}

	@Override
	public boolean save(CartaData cartaData) throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement("INSERT INTO cartadata (idUsuario) VALUES (?)")) {
			ps.setInt(1, cartaData.getIdUser());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(CartaData cartaData) throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement("UPDATE cartadata SET idUsuario = ? WHERE idCartaData = ?")) {
			ps.setInt(1, cartaData.getIdUser());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteById(int id) throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement("DELETE FROM cartadata WHERE idCartaData = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(CartaData cartaData) throws SQLException {
		return deleteById(cartaData.getIdUser());
	}

	@Override
	public boolean addProductoToCarta(int idUser, int idProducto) {
		Optional<CartaData> cartaData = findCartaByUser(idUser);
		if (cartaData.isPresent()) {
			try (PreparedStatement ps = conn
					.prepareStatement("INSERT INTO itemdata (idCartaData, idProducto, cantidad) VALUES (?, ?, ?)")) {
				ps.setInt(1, cartaData.get().getIdCartaData());
				ps.setInt(2, idProducto);
				ps.setInt(3, 1);
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public boolean deleteProductoToCarta(int idUser, int idProducto) {
		Optional<CartaData> cartaData = findCartaByUser(idUser);
		if (cartaData.isPresent()) {
			try (PreparedStatement ps = conn
					.prepareStatement("DELETE FROM itemdata WHERE idCartaData = ? AND idProducto = ?")) {
				ps.setInt(1, cartaData.get().getIdCartaData());
				ps.setInt(2, idProducto);
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public Optional<CartaData> findCartaByUser(int idUser) {
		List<ItemData> productos = new ArrayList<ItemData>();
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM cartadata WHERE idUsuario = ?")) {
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Optional<CartaData> cartaData = Optional.of(new CartaData(rs.getInt("idCartaData"), rs.getInt("idUsuario")));
				// Obtener los productos de la carta
				try (PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM itemdata WHERE idCartaData = ?")) {
					ps2.setInt(1, rs.getInt("idCartaData"));
					ResultSet rs2 = ps2.executeQuery();
					while (rs2.next()) {
						productos.add(new ItemData(rs2.getInt("idItemData"), rs2.getInt("idProducto"),
								rs2.getInt("idCartaData"), rs2.getInt("cantidad")));
					}
					cartaData.get().setlProductos(productos);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return cartaData;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public List<ItemData> getProductos(int idCarta) {
		CartaData cartaData = new CartaData();
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM itemdata WHERE idCartaData = ?")) {
			ps.setInt(1, idCarta);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cartaData.addProducto(new ItemData(rs.getInt("idItemData"), rs.getInt("idProducto"),
						rs.getInt("idCartaData"), rs.getInt("cantidad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cartaData.getlProductos();
	}
}
