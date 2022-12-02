import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection conn;
    private static Conexion instancia;

    private static final String url = "jdbc:mysql://localhost:3306/farmacia";
    private static final String user = "root";
    private static final String pass = "";

    public Connection conectar () throws SQLException {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion Exitosa :D");
            return conn;
        } catch (Exception e){
            System.out.println("Falla conetion :c");
        }
        return conn;
    }
    public static void closeCone() throws SQLException {
        try {
            System.out.println("Conex close ");
            conn.close();
        } catch (Exception e) {
            System.out.println("Conex Close ");
            conn.close();
        } finally {
            conn.close();
        }
    }
    public static Conexion getInstancia(){
        if (instancia == null){
            instancia = new Conexion();
        }
        return instancia;

    }
}
