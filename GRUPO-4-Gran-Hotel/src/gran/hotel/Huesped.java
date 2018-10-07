//                          GRUPO 4: EL GRAN HOTEL                            //
//                                                                            //
//             BORDAGORRY MARIA FLORENCIA  -  BUNINO YAMILE NAIR              //
//                BERTERO RODOLFO  -  BUSTOS DANIEL ALEJANDRO                 //
//                                                                            //
// -------------------------------------------------------------------------- //
//                                                                            //
//                              CLASE HUESPED                                 //
//                                                                            //
//ESTA CLASE DEFINE UN OBJETO DE TIPO HUESPED


package gran.hotel;


public class Huesped {
    
    //ATRIBUTOS
    private int id_huesped;
    private String  dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String correo;
    private String telefono;
    
    
    //CONSTRUCTOR
    public Huesped(int id,String nombre, String apellido, String dni, String domicilio, String correo, String telefono) {
        this.id_huesped = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
    }
    
    public Huesped() {

    }
    
    //Sobrecarga de constructor:
    public Huesped(String nombre, String apellido, String dni, String domicilio, String correo, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.correo = correo;
        this.telefono = telefono;
    }
     
    
    //GETTERS Y SETTERS
    public int getId_huesped() {
        return id_huesped;
    }

    public void setId_huesped(int id_huesped) {
        this.id_huesped = id_huesped;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
