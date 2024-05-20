package es.unex.cum.tw.listeners;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import es.unex.cum.tw.services.ProductoServiceMemory;
import es.unex.cum.tw.services.ProductoServiceMemoryImpl;
import es.unex.cum.tw.util.DatabaseInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("Iniciando la aplicación");
        servletContext = sce.getServletContext();

        // Crear Base de Datos
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.crearBD();

        ProductoServiceMemory productoServiceMemory;
        try{
            productoServiceMemory = new ProductoServiceMemoryImpl();
            productoServiceMemory.cargarProductoABD();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("Petición recibida desde la IP: " + sre.getServletRequest().getRemoteAddr());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Sesión creada: " + se.getSession().getId());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("Destruyendo la aplicación!");
        // Cerrar la conexión con la base de datos
        AbandonedConnectionCleanupThread.checkedShutdown();
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("Petición finalizada");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Sesión destruida: " + se.getSession().getId());
    }
}
