package midik.tablaSimbolos;

public enum Roles {
    CLASE("CLASE"),
    METODO("METODO"),
    VARIABLE("VARIABLE"),
    PARAMETRO("PARAMETRO"),
    COMENTARIO_LINEA("COMENTARIO_LINEA"),
    COMENTARIO_BLOQUE("COMENTARIO_BLOQUE");

    private String descripcion = "";

    private Roles(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.descripcion;
    }
}
