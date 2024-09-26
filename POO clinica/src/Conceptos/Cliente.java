package Conceptos;
public class Cliente {
    String nombre;
    String codigo;
    String telefono;

    public Cliente(String nombre, String codigo, String telefono) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.telefono = telefono;
    }
    
    public Cliente() {
        this.nombre = "";
        this.codigo = "0";
        this.telefono = "00000000";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString(){
        return "Cliente: " + this.getNombre() + " Codigo: " + this.getCodigo() + " Telefono: " + this.getTelefono();
    }
}
