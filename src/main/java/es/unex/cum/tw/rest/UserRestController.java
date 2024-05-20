package es.unex.cum.tw.rest;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import es.unex.cum.tw.models.Usuario;
import es.unex.cum.tw.services.UsuarioService;
import es.unex.cum.tw.services.UsuarioServiceJDBCImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestController {

    private final UsuarioService service;

    public UserRestController() throws SQLException, NamingException {
        this.service = new UsuarioServiceJDBCImpl();
    }

    @GET
    @Path("/id/{id}")
    public Response getUserById(@PathParam("id") int id) {
        try {
            Usuario user = service.findById(id).orElse(null);
            if (user != null) {
                return Response.ok(user).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    public Response getAllUsers() {
        try {
            List<Usuario> users = service.findAll();
            if (users != null && !users.isEmpty()) {
                return Response.ok(users).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron usuarios").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/authenticate")
    public Response authenticateUser(@FormParam("username") String formUsername, @FormParam("password") String formPassword,
                                     @QueryParam("username") String queryUsername, @QueryParam("password") String queryPassword) {
        try {
            String username = formUsername != null ? formUsername : queryUsername;
            String password = formPassword != null ? formPassword : queryPassword;

            if (username == null || password == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Falta el nombre de usuario o la contraseña").build();
            }

            if (service.authenticate(username, password)) {
                return Response.ok("Usuario autenticado correctamente").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Error, usuario no autenticado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Usuario user) {
        try {
            if (service.save(user)) {
                return Response.ok("Usuario agregado exitosamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al agregar usuario").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(Usuario user) {
        try {
            if (service.update(user)) {
                return Response.ok("Usuario actualizado correctamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al actualizar usuario").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        try {
            if (service.deleteById(id)) {
                return Response.ok("Usuario eliminado correctamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error en la eliminación del usuario").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/username/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        try {
            Usuario user = service.findByUsername(username).orElse(null);
            if (user != null) {
                return Response.ok(user).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


}
