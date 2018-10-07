//                          GRUPO 4: EL GRAN HOTEL                            //
//                                                                            //
//             BORDAGORRY MARIA FLORENCIA  -  BUNINO YAMILE NAIR              //
//                BERTERO RODOLFO  -  BUSTOS DANIEL ALEJANDRO                 //
//                                                                            //
// -------------------------------------------------------------------------- //
//                                                                            //
//                            CLASE HUESPED DATA                              //
//                                                                            //
//EN ESTA CLASE ENCONTRAREMOS LA CONEXION A BASE DE DATOS CON LA CUAL, PODEMOS
//MANIPULAR LA TABLA QUE ALMACENA LA INFORMACION DE CADA UNO DE LOS HUESPEDES
//REGISTRADOS.


package gran.hotel;

//IMPORTAMOS LAS LIBRERIAS NECESARIAS:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//DEFINIMOS LA CLASE:
public class HuespedData {
    private Connection connection = null; //Definimos atributo tipo Connection
    
    //En el constructor realizmos la conexion con la base de datos:
    public HuespedData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion HuespedData");
        }
    }
    
    //El siguiente método permite registrar un nuevo huesped en nuestra base de datos:
    //(falta agregar el control de que el huesped no exista)
    public void registrarHuesped(Huesped huesped){
        try {
            
            String sql = "INSERT INTO huesped (nombre, apellido, dni, domicilio, correo, telefono) VALUES ( ? , ? , ? , ? , ? , ? );";

            PreparedStatement statment = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statment.setString(1, huesped.getNombre());
                statment.setString(2, huesped.getApellido());
                statment.setString(3, huesped.getDni());
                statment.setString(4, huesped.getDomicilio());
                statment.setString(5, huesped.getCorreo());
                statment.setString(6, huesped.getTelefono());

                //Ejecutamos el Insert:
                statment.executeUpdate();
                
                //Obtenemos el ID que se le fue asignado al nuevo huesped
                ResultSet rs = statment.getGeneratedKeys();
                
                //Verificamos si no hubo errores:
                if (rs.next()) {
                    huesped.setId_huesped(rs.getInt(1));
                } else {
                    System.out.println("No se pudo obtener el id luego de insertar un huesped");
                } 
                statment.close();
            
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un huesped: " + ex.getMessage());
        }
    }
    
    //El siguiente método devuelve el listado de todos los huespedes registrados:
    public List<Huesped> obtenerHuespedes(){
        List<Huesped> huespedes = new ArrayList<>();
            
        try {
            String sql = "SELECT * FROM huesped;";
            try (PreparedStatement statment = connection.prepareStatement(sql)) {
                ResultSet resultSet = statment.executeQuery();
                Huesped huesped;
                while(resultSet.next()){
                    huesped = new Huesped();
                    huesped.setId_huesped(resultSet.getInt("id_huesped"));
                    huesped.setNombre(resultSet.getString("nombre"));
                    huesped.setApellido(resultSet.getString("apellido"));
                    huesped.setDni(resultSet.getString("dni"));
                    huesped.setDomicilio(resultSet.getString("domicilio"));
                    huesped.setCorreo(resultSet.getString("correo"));
                    huesped.setTelefono(resultSet.getString("telefono"));
                    huespedes.add(huesped);
                }
                statment.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener los huespedes: " + ex.getMessage());
        }
         
        return huespedes;
    }
    
    //El siguiente método permite eliminar un huesped, identificandolo solo con su ID:
    public void borrarHuesped(int id_huesped){
    try {
            
            String sql = "DELETE FROM huesped WHERE id_huesped =?;";

            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setInt(1, id_huesped);
           
            
            statment.executeUpdate();
            
            
            statment.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un huesped: " + ex.getMessage());
        }
    }
    
    
    //El siguiente es una sobre-carga del metodo borrarHuesped, el cual nos permite
    //eliminar un huesped identicandolo por su numero de dni:
    public void borrarHuesped(String dni){
    try {
            
            String sql = "DELETE FROM huesped WHERE dni = ?;";

            PreparedStatement statment = connection.prepareStatement(sql);
            statment.setString(1, dni);
           
            statment.executeUpdate();
            
            statment.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar un huesped: " + ex.getMessage());
        }
    }
    
    
    //El siguiente método permite modificar y/o actualizar datos de un huesped:
    public void actualizarHuesped(Huesped huesped){
    
        try {
            
            String sql = "UPDATE huesped SET nombre = ?, apellido = ?, dni = ? , domicilio =?, correo = ?, telefono = ?  WHERE id_huesped = ?;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getApellido());
            statement.setString(3, huesped.getDni());
            statement.setString(4, huesped.getDomicilio());
            statement.setString(5, huesped.getCorreo());
            statement.setString(6, huesped.getTelefono());
            statement.setInt(7, huesped.getId_huesped());
            statement.executeUpdate();
            
          
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un huesped: " + ex.getMessage());
        }
    
    }
    
    
    //El siguiente metodo devuelve los datos de un huesped a partir de su ID:
    public Huesped buscarHuesped(int id_huesped){
    Huesped huesped=null;
    try {
            
            String sql = "SELECT * FROM huesped WHERE id_huesped = ? ;";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id_huesped);
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                huesped = new Huesped();
                huesped.setId_huesped(resultSet.getInt("id_huesped"));
                huesped.setNombre(resultSet.getString("nombre"));
                huesped.setApellido(resultSet.getString("apellido"));
                huesped.setDni(resultSet.getString("dni"));
                huesped.setDomicilio(resultSet.getString("domicilio"));
                huesped.setCorreo(resultSet.getString("correo"));
                huesped.setTelefono(resultSet.getString("telefono"));  
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al buscar un huesped: " + ex.getMessage());
        }
        
        return huesped;
    }
}

