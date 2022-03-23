package midik.comparacion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import midik.tablaSimbolos.Roles;

public class Score {

    //Contadores de terminos de los proyectos
    private int contadorComentarios = 0;
    private int contadorVariables = 0;
    private int contadorMetodos = 0;
    private int contadorClases = 0;

    //Contadores de terminos repetidos
    private int contadorComentariosRep = 0;
    private int contadorVariablesRep = 0;
    private int contadorMetodosRep = 0;
    private int contadorClasesRep = 0;
    private ArrayList<Repetido> repetidos = null;

    public Score() {

    }

    public double calcularScore() {
        this.recorrerRepetidos();
        double score = 0;

        if (contadorComentarios > 0) {
            score += (double) contadorComentariosRep / (double) contadorComentarios * 0.25;
        }
        if (contadorVariables > 0) {
            score += (double) contadorVariablesRep / (double) contadorVariables * 0.25;
        }
        if (contadorMetodos > 0) {
            score += (double) contadorMetodosRep / (double) contadorMetodos * 0.25;
        }
        if (contadorClases > 0) {
            score += (double) contadorClasesRep / (double) contadorClases * 0.25;
        }

        this.imprimirContadores();
        this.imprimirContadoresRep();
        DecimalFormat df = new DecimalFormat("#.0000");
        return Double.valueOf(df.format(score));
    }

    public void contar(Roles rol) {
        switch (rol) {
            case CLASE:
                this.contadorClases++;
                break;
            case COMENTARIO_BLOQUE:
            case COMENTARIO_LINEA:
                this.contadorComentarios++;
                break;
            case METODO:
                this.contadorMetodos++;
                break;
            case VARIABLE:
                this.contadorVariables++;
        }
    }

    private void recorrerRepetidos() {
        for (Repetido term : this.repetidos) {
            contarRepetidos(term);
        }
    }

    private void contarRepetidos(Repetido term) {
        Roles rol = term.getRol();
        switch (rol) {
            case CLASE:
                this.contadorClasesRep++;
                break;
            case COMENTARIO_BLOQUE:
            case COMENTARIO_LINEA:
                this.contadorComentariosRep++;
                break;
            case METODO:
                this.contadorMetodosRep++;
                break;
            case VARIABLE:
                int repeticiones = term.getLugarRepitencia().split(",").length;
                this.contadorVariablesRep += repeticiones - 1; //-1 ya que no se cuenta la aparicion en el proyecto 1
                break;
        }
    }

    private void imprimirContadores() {
        System.out.println("------------ TOTAL DE TERMINOS ------------");
        System.out.println("Comentarios: " + contadorComentarios);
        System.out.println("Variables: " + contadorVariables);
        System.out.println("Metodos: " + contadorMetodos);
        System.out.println("Clases: " + contadorClases);
    }

    private void imprimirContadoresRep() {
        System.out.println("------------ TOTAL DE TERMINOS REPETIDOS ------------");
        System.out.println("Comentarios: " + contadorComentariosRep);
        System.out.println("Variables: " + contadorVariablesRep);
        System.out.println("Metodos: " + contadorMetodosRep);
        System.out.println("Clases: " + contadorClasesRep);
    }

    public void setRepetidos(ArrayList<Repetido> repetidos) {
        this.repetidos = repetidos;
    }

}
