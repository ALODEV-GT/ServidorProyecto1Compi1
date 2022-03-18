package midik.tablaSimbolos;

public enum Visibilidad {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE"),
    PROTECTED("PROTECTED"),
    DEFAULT("DEFAULT");

    private String descripcion = "";

    private Visibilidad(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }

}
