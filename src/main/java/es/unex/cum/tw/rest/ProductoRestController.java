package es.unex.cum.tw.rest;

import es.unex.cum.tw.models.Producto;
import es.unex.cum.tw.services.ProductoService;
import es.unex.cum.tw.services.ProductoServiceJDBCImpl;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

@RequestScoped
@Path("/Producto")
@Produces(MediaType.APPLICATION_JSON)
public class ProductoRestController {


    private final ProductoService productoService;

    public ProductoRestController() throws SQLException, NamingException {
        this.productoService = new ProductoServiceJDBCImpl();
    }

    @GET
    public Response getAllProducts() {
        try {
            List<Producto> productos = productoService.findAll();
            if (productos != null && !productos.isEmpty()) {
                return Response.ok(productos).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron productos").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") int id) {
        try {
            Producto producto = productoService.findProductoById(id).orElse(null);
            if (producto != null) {
                return Response.ok(producto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Producto no encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProduct(Producto producto) {
        try {
            if (productoService.saveProducto(producto)) {
                return Response.ok("Producto agregado exitosamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al agregar producto").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(Producto producto) {
        try {
            if (productoService.update(producto)) {
                return Response.ok("Producto actualizado exitosamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al actualizar el producto").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        try {
            if (productoService.deleteProductoById(id)) {
                return Response.ok("Producto eliminado exitosamente").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Error al eliminar el producto").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}