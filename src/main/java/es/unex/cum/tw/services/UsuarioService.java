package es.unex.cum.tw.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import es.unex.cum.tw.models.Usuario;

public interface UsuarioService {
	Boolean authenticate(String username, String password);

	List<Usuario> findAll();

	Optional<Usuario> findById(int id);

	boolean save(Usuario usuario);

	boolean update(Usuario usuario);

	boolean deleteById(int id);

	boolean delete(Usuario usuario);

	Optional<Usuario> findByUsername(String username) throws SQLException;

}
