package midik.comparacion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import midik.tablaSimbolos.Roles;

public class Score {

    //Grupos
    private ArrayList<Repetido> clases = new ArrayList<>();
    private ArrayList<Repetido> variables = new ArrayList<>();
    private ArrayList<Repetido> metodos = new ArrayList<>();
    private ArrayList<Repetido> comentarios = new ArrayList<>();

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
        this.agrupar();
        this.contarRepitencia();

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

    private void contarRepitencia() {
        this.contarRepitenciaVariables();
        this.contarRepitenciaComentarios();
        this.contarRepitenciaClases();
        this.contarRepitenciaMetodos();
    }

    private void contarRepitenciaVariables() {
        ArrayList<Integer> revisados = new ArrayList<>();
        ArrayList<String> lugares;

        for (int i = 0; i < this.variables.size(); i++) {
            if (!esRevisado(revisados, i)) {
                Repetido r1 = this.variables.get(i);
                lugares = new ArrayList<>(Arrays.asList(r1.getLugarRepitencia().split(",")));
                for (int j = i + 1; j < this.variables.size(); j++) {
                    Repetido r2 = this.variables.get(j);
                    if (r1.getNombre().equals(r2.getNombre())) {
                        revisados.add(j);
                        this.contadorVariablesRep++;
                    }
                }
                this.contadorVariablesRep += lugares.size();
                lugares.clear(); //limpiar
            }
        }
    }

    private void contarRepitenciaComentarios() {
        ArrayList<Integer> revisados = new ArrayList<>();
        ArrayList<String> lugares;

        for (int i = 0; i < this.comentarios.size(); i++) {
            if (!esRevisado(revisados, i)) {
                Repetido r1 = this.comentarios.get(i);
                lugares = new ArrayList<>(Arrays.asList(r1.getLugarRepitencia().split(",")));
                for (int j = i + 1; j < this.comentarios.size(); j++) {
                    Repetido r2 = this.comentarios.get(j);
                    if (r1.getNombre().equals(r2.getNombre())) {
                        revisados.add(j);
                        this.contadorComentariosRep++;
                    }
                }
                this.contadorComentariosRep += lugares.size();
                lugares.clear(); //limpiar
            }
        }
    }

    private void contarRepitenciaClases() {
        ArrayList<Integer> revisados = new ArrayList<>();
        ArrayList<String> lugares;

        for (int i = 0; i < this.clases.size(); i++) {
            if (!esRevisado(revisados, i)) {
                Repetido r1 = this.clases.get(i);
                lugares = new ArrayList<>(Arrays.asList(r1.getLugarRepitencia().split(",")));
                for (int j = i + 1; j < this.clases.size(); j++) {
                    Repetido r2 = this.clases.get(j);
                    if (r1.getNombre().equals(r2.getNombre())) {
                        revisados.add(j);
                        this.contadorClasesRep++;
                    }
                }
                this.contadorClasesRep += lugares.size();
                lugares.clear(); //limpiar
            }
        }
    }

    private void contarRepitenciaMetodos() {
        System.out.println("Metodos repetidos: " + this.metodos.size());
        ArrayList<Integer> revisados = new ArrayList<>();
        ArrayList<String> lugares;

        for (int i = 0; i < this.metodos.size(); i++) {
            if (!esRevisado(revisados, i)) {
                Repetido r1 = this.metodos.get(i);
                lugares = new ArrayList<>(Arrays.asList(r1.getLugarRepitencia().split(",")));
                for (int j = i + 1; j < this.metodos.size(); j++) {
                    Repetido r2 = this.metodos.get(j);
                    if (r1.getNombre().equals(r2.getNombre())) {
                        revisados.add(j);
                        this.contadorMetodosRep++;
                    }
                }
                this.contadorMetodosRep += lugares.size();
                lugares.clear(); //limpiar
            }
        }
    }

    private boolean esRevisado(ArrayList<Integer> revisados, int indice) {
        boolean revisado = false;
        for (int i = 0; i < revisados.size(); i++) {
            if (indice == revisados.get(i)) {
                revisado = true;
                break;
            }
        }
        return revisado;
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

    private void agrupar() {
        for (Repetido repetido : this.repetidos) {
            switch (repetido.getRol()) {
                case CLASE:
                    this.clases.add(repetido);
                    break;
                case COMENTARIO_BLOQUE:
                case COMENTARIO_LINEA:
                    this.comentarios.add(repetido);
                    break;
                case METODO:
                    this.metodos.add(repetido);
                    break;
                case VARIABLE:
                    this.variables.add(repetido);
                    break;
            }
        }
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

}
