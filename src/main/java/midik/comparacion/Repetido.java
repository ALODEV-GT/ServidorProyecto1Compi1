package midik.comparacion;

import midik.tablaSimbolos.Roles;

public class Repetido {

    private Roles rol;
    private String nombre;
    private String tipo;
    private String lugarRepitencia = "";
    private int numParametros = 0;

    public Repetido(String nombre, Roles rol, String lugarRepitencia, String tipo, int numParametros) {
        this.nombre = nombre;
        this.rol = rol;
        this.lugarRepitencia = lugarRepitencia;
        this.tipo = tipo;
        this.numParametros = numParametros;
    }

    public Repetido() {

    }

    public void setLugarRepitencia(String lugares) {
        this.lugarRepitencia = lugares;
    }

    public String getLugarRepitencia() {
        return this.lugarRepitencia;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
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

    public int getNumParametros() {
        return numParametros;
    }

    public void setNumParametros(int numParametros) {
        this.numParametros = numParametros;
    }

    @Override
    public String toString() {
        return "Repetido{" + "rol=" + rol + ", nombre=" + nombre + ", tipo=" + tipo + ", lugarRepitencia=" + lugarRepitencia + ", numParametros=" + numParametros + '}';
    }

}
