package es.unex.cum.tw.repositories;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.naming.NamingException;

import es.unex.cum.tw.models.Producto;
import es.unex.cum.tw.services.ProductoServiceMemoryImpl;
import es.unex.cum.tw.util.ConexionBD_DSPool;

public class ProductoRepositoryJDBCImpl implements ProductoRepository {

	private final Connection conn;
    private List<Producto> productos = null;

	public ProductoRepositoryJDBCImpl() throws SQLException, NamingException {
		conn = ConexionBD_DSPool.getConexionBD();
	}

	@Override
	public Optional<Producto> findById(int idProducto) {
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM productos WHERE idProducto = ?")) {
			ps.setInt(1, idProducto);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return Optional.of(new Producto(rs.getInt("idProducto"), rs.getString("nombreProd"),
						rs.getString("pathImagen"), rs.getString("comentarios")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}



	@Override
	public void cargarProductoABD() {

		if (productos == null) {
			productos = new ArrayList<>();
			try {
				URL stream = ProductoServiceMemoryImpl.class.getClassLoader().getResource("productos.csv");
				if (stream == null) {
					throw new IllegalArgumentException("No se ha encontrado el archivo productos.csv");
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(stream.getFile())));
				String stringRead = br.readLine();

				Connection connection = ConexionBD_DSPool.getConexionBD();
				String query = "INSERT INTO productos (idProducto, nombreProd, pathImagen, comentarios) VALUES (?, ?, ?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(query);

				while (stringRead != null) {
					StringTokenizer st = new StringTokenizer(stringRead, ",");
					// Elimina las comillas dobles
					int id = Integer.parseInt(st.nextToken().replace("\"", ""));
					String nombre = st.nextToken().replace("\"", "");
					String path = st.nextToken().replace("\"", "");
					String Comentarios = st.nextToken().replace("\"", "");

					productos.add(new Producto(id, nombre, path, Comentarios));

					preparedStatement.setInt(1, id);
					preparedStatement.setString(2, nombre);
					preparedStatement.setString(3, path);
					preparedStatement.setString(4, Comentarios);
					preparedStatement.executeUpdate();

					stringRead = br.readLine();
				}
				br.close();
				connection.close();
			} catch (IOException | SQLException | NamingException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Producto> findAllProductoCSV() {
		if(productos == null) {
			cargarProductoABD();
		}

		return productos;
	}

	@Override
	public Optional<Producto> findProductoCSVById(int id) {
		if(productos == null) {
			cargarProductoABD();
		}

		return productos.stream().filter(p -> p.getIdProducto() == id).findFirst();
	}


	@Override
	public List<Producto> findAll() {
		List<Producto> productos = new ArrayList<Producto>();
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM productos")) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				productos.add(new Producto(rs.getInt("idProducto"), rs.getString("nombreProd"),
						rs.getString("pathImagen"), rs.getString("comentarios")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productos;
	}

	@Override
	public boolean save(Producto producto) {
		try (PreparedStatement ps = conn
				.prepareStatement("INSERT INTO productos (nombreProd, pathImagen, comentarios) VALUES (?, ?, ?)")) {
			ps.setString(1, producto.getNombreProd());
			ps.setString(2, producto.getPathImagen());
			ps.setString(3, producto.getComentarios());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean update(Producto producto) {
		try (PreparedStatement ps = conn.prepareStatement(
				"UPDATE productos SET nombreProd = ?, pathImagen = ?, comentarios = ? WHERE idProducto = ?")) {
			ps.setString(1, producto.getNombreProd());
			ps.setString(2, producto.getPathImagen());
			ps.setString(3, producto.getComentarios());
			ps.setInt(4, producto.getIdProducto());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteById(int id) {

		try (PreparedStatement ps = conn.prepareStatement("DELETE FROM itemdata WHERE idProducto = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		try (PreparedStatement ps = conn.prepareStatement("DELETE FROM productos WHERE idProducto = ?")) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean delete(Producto producto) {
		return deleteById(producto.getIdProducto());
	}
}
