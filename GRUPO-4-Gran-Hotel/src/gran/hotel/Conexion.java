//                          GRUPO 4: EL GRAN HOTEL                            //
//                                                                            //
//             BORDAGORRY MARIA FLORENCIA  -  BUNINO YAMILE NAIR              //
//                BERTERO RODOLFO  -  BUSTOS DANIEL ALEJANDRO                 //
//                                                                            //
// -------------------------------------------------------------------------- //
//                                                                            //
//                              CLASE CONEXION                                //
//                                                                            //
//ESTA CLASE PERMITE ESTABLECER LA CONEXION CON LA BASE DE DATOS.


package gran.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {
    private String url;
    private String usuario;
    private String password;

    private Connection conexion;
    
    public Conexion(String url, String usuario, String password) throws ClassNotFoundException {
        this.url = url;
        this.usuario = usuario;
        this.password = password;

        //Cargamos las clases de mariadb que implementan JDBC
        Class.forName("org.mariadb.jdbc.Driver");

    }
   
    public Connection getConexion() throws SQLException{
        if(conexion == null){
                    // Setup the connection with the DB
            conexion = DriverManager
                .getConnection(url + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
                        + "&user=" + usuario + "&password=" + password);
        }
        return conexion;
    }
    
}
