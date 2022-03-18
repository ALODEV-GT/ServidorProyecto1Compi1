package midik.tablaSimbolos;

public class Termino {

    private String nombre;
    private String tipo;
    private String ambito;
    private String visibilidad;
    private String rol;

    public Termino() {
    }

    public Termino(String nombre, String tipo, String ambito, String visibilidad, String rol) {
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

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
