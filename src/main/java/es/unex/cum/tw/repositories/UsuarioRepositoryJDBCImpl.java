package es.unex.cum.tw.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;

import es.unex.cum.tw.models.Usuario;
import es.unex.cum.tw.models.UsuarioBuilder;
import es.unex.cum.tw.util.ConexionBD_DSPool;

public class UsuarioRepositoryJDBCImpl implements UsuarioRepository {

    private final Connection conn;

    public UsuarioRepositoryJDBCImpl() throws SQLException, NamingException {
        conn = ConexionBD_DSPool.getConexionBD();
    }

    @Override
    public Optional<Usuario> findByUsername(String username) throws SQLException {
        Usuario user;
        try(PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE username = ?")){
            statement.setString(1, username);
            user = getUsuario(statement);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(user);

    }

    @Override
    public Optional<Usuario> findByEmail(String email) throws SQLException {
        Usuario user = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE email = ?")) {
            statement.setString(1, email);
            user = getUsuario(statement);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<Usuario> findByUsernameAndPassword(String username, String password) throws SQLException {
        Usuario user = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            user = getUsuario(statement);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<Usuario> findById(int id) throws SQLException {
        Usuario user = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE idUsuario = ?")) {
            statement.setLong(1, id);
            user = getUsuario(statement);
        }
        return Optional.ofNullable(user);
    }

    private Usuario getUsuario(PreparedStatement statement) throws SQLException {
        Usuario user = null;
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                user = new UsuarioBuilder().setId(rs.getInt("idUsuario"))
                        .setNombre(rs.getString("nombre"))
                        .setApellidos(rs.getString("apellidos"))
                        .setEmail(rs.getString("email"))
                        .setUsername(rs.getString("username"))
                        .setPassword(rs.getString("password"))
                        .build();

            }
        }
        return user;
    }


    @Override
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> users = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Usuario user = new UsuarioBuilder().setId(rs.getInt("idUsuario"))
                            .setNombre(rs.getString("nombre"))
                            .setApellidos(rs.getString("apellidos"))
                            .setEmail(rs.getString("email"))
                            .setUsername(rs.getString("username"))
                            .setPassword(rs.getString("password"))
                            .build();
                    users.add(user);
                }
            }
        }
        return users;
    }

    @Override
    public boolean save(Usuario user) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO usuarios (nombre, apellidos, email, username, password) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellidos());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Usuario user) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(
                "UPDATE usuarios SET nombre = ?, apellidos = ?, email = ?, username = ?, password = ? WHERE idUsuario = ?")) {
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellidos());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setLong(6, user.getId());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM usuarios WHERE idUsuario = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(Usuario user) throws SQLException {
        return deleteById(user.getId());
    }
}
