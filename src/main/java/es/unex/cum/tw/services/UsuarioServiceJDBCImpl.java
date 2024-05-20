
package es.unex.cum.tw.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;

import es.unex.cum.tw.models.Usuario;
import es.unex.cum.tw.repositories.UsuarioRepository;
import es.unex.cum.tw.repositories.UsuarioRepositoryJDBCImpl;

public class UsuarioServiceJDBCImpl implements UsuarioService {

    private final UsuarioRepository userRepository;

    public UsuarioServiceJDBCImpl() throws SQLException, NamingException {
        this.userRepository = new UsuarioRepositoryJDBCImpl();
    }


    @Override
    public Boolean authenticate(String username, String password) {
        try {
            return userRepository.findByUsernameAndPassword(username, password).isPresent();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            return userRepository.findAll();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> findById(int id) {
        try {
            return userRepository.findById(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean save(Usuario user) {
        try {
            return userRepository.save(user);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean update(Usuario user) {
        try {
            return userRepository.update(user);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            return userRepository.deleteById(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean delete(Usuario user) {
        try {
            return userRepository.delete(user);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) throws SQLException {
        try {
            return userRepository.findByUsername(username);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

}