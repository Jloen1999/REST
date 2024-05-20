package es.unex.cum.tw.rest;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import es.unex.cum.tw.models.CartaData;
import es.unex.cum.tw.services.CartaService;
import es.unex.cum.tw.services.CartaServiceJDBCImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/Carta")
@Produces(MediaType.APPLICATION_JSON)
public class CartaRestController {

    private final CartaService cartaService;


    public CartaRestController() throws SQLException, NamingException {
        this.cartaService = new CartaServiceJDBCImpl();
    }

    @GET
    public Response getAllCartas() {
        try {
            List<CartaData> cartas = cartaService.findAll();
            if (cartas != null && !cartas.isEmpty()) {
                return Response.ok(cartas).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron cartas").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getCartaByIdUser(@PathParam("id") int id) {
        try {
            CartaData carta = cartaService.findCartaByIdUser(id).orElse(null);
            if (carta != null) {
                return Response.ok(carta).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Carta no encontrada").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/{idU}-{idP}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProductoToCarta(@PathParam("idU") int idU, @PathParam("idP") int idP) {
        try {
            if (cartaService.addProductoToCarta(idU, idP)) {
                return Response.ok("Producto agregado a la carta exitosamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al añadir producto a la carta").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{idU}-{idP}")
    public Response deleteProductFromCarta(@PathParam("idU") int idU, @PathParam("idP") int idP) {
        try {
            if (cartaService.deleteProductoToCarta(idU, idP)) {
                return Response.ok("Producto eliminado de la carta con éxito").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al eliminar producto de la carta").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}