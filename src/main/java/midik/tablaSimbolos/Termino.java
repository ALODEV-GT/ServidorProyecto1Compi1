package midik.tablaSimbolos;

public class Termino {

    private String nombre;
    private String tipo;
    private String ambito;
    private Visibilidad visibilidad;
    private Roles rol;

    public Termino() {
    }

    public Termino(String nombre, String tipo, String ambito, Visibilidad visibilidad, Roles rol) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ambito = ambito;
        this.visibilidad = visibilidad;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Visibilidad getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(Visibilidad visibilidad) {
        this.visibilidad = visibilidad;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Termino{" + "nombre=" + nombre + ", tipo=" + tipo + ", ambito=" + ambito + ", visibilidad=" + visibilidad + ", rol=" + rol + '}';
    }

}
