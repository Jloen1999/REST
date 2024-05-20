package es.unex.cum.tw.models;

public class UsuarioBuilder {
    private int id;
    private String nombre;
    private String apellidos;
    private String email;
    private String username;
    private String password;

    public UsuarioBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioBuilder setApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public UsuarioBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UsuarioBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UsuarioBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public Usuario build() {
        return new Usuario(id, nombre, apellidos, email, username, password);
    }
}
