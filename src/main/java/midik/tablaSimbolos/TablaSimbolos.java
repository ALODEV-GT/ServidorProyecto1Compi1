package midik.tablaSimbolos;

import java.util.ArrayList;

public class TablaSimbolos {

    private final ArrayList<Termino> tablaSimbolos;

    public TablaSimbolos() {
        this.tablaSimbolos = new ArrayList<>();
    }

    public void agregarTermino(Termino termino) {
        this.tablaSimbolos.add(termino);
    }

    public ArrayList<Termino> getTablaSimbolos() {
        return this.tablaSimbolos;
    }
}
