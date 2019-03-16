package sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private static Connection conexion = null;
    //constantes para obtener la conexion con la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/CONTROL_REPUESTOS";
    private static final String USER = "root";
    private static final String CONTRA = "kadabrakiller";
    
    //metodo que me devuelve la conexion
    public static Connection getConection() throws SQLException, ClassNotFoundException {
        if (conexion == null) {//si no se ha creado una conexion
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection(URL,USER,CONTRA);// creamos una
                System.out.println("conexion exitosa");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return conexion;//y la retornamos
    }
//metodo para cerrar la conexion
    public static void closeConexion() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
}
