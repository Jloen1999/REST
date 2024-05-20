package es.unex.cum.tw.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Objects;

public class DatabaseInitializer {

    public void crearBD() {
        try {
            // Carga el driver de la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Crea una conexión a la base de datos
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "JoseLuis19..");

            // Carga el archivo SQL
            BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(DatabaseInitializer.class.getResourceAsStream("/tw.sql"))));

            // Crea un Statement
            Statement stmt = conn.createStatement();

            // Lee y ejecuta cada línea del archivo SQL
            String line;
            while ((line = reader.readLine()) != null) {
                stmt.execute(line);
            }

            // Cierra los recursos
            stmt.close();
            conn.close();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar la base de datos", e);
        }
    }

}