package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Usuario;

import java.sql.SQLException;
import java.util.Optional;

public interface UsuarioRepository extends Repository<Usuario>{
    Optional<Usuario> findByUsername(String username) throws SQLException;
    Optional<Usuario> findByEmail(String email) throws SQLException;
    Optional<Usuario> findByUsernameAndPassword(String username, String password) throws SQLException;

}
