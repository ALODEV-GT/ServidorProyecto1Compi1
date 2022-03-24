package midik.comparacion;

import java.util.ArrayList;

public class GenerarJson {

    private ArrayList<Repetido> repetidos = null;
    private ArrayList<Repetido> clases = new ArrayList<>();
    private ArrayList<Repetido> variables = new ArrayList<>();
    private ArrayList<Repetido> metodos = new ArrayList<>();
    private ArrayList<Repetido> comentarios = new ArrayList<>();
    private double score;
    private String json = "{";

    public GenerarJson(ArrayList<Repetido> repetidos, double score) {
        this.repetidos = repetidos;
        this.score = score;
    }

    public String generar() {
        this.agrupar();

        //Score
        json += "Score:\"" + score + "\",";

        //Clases
        json += "Clases:[" + listarClases() + "],";

        //Variables
        json += "Variables:[" + listarVariables() + "],";

        //Metodos
        json += "Metodos:[" + listarMetodos() + "],";

        //Comentarios
        json += "Comentarios:[" + listarComentarios() + "]";

        //FIN
        json += "}";

        return json;

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

    private String listarClases() {
        String lista = "";
        for (int i = 0; i < clases.size(); i++) {
            lista += "{Nombre:\"" + this.clases.get(i).getNombre() + "\"}";
            if (i != clases.size() - 1) {
                lista += ",";
            }
        }
        return lista;
    }

    private String listarVariables() {
        String lista = "";
        for (int i = 0; i < variables.size(); i++) {
            Repetido r = this.variables.get(i);
            String partes[] = r.getLugarRepitencia().split(",");
            String lugares = "";
            for (int j = 0; j < partes.length; j++) {
                lugares += partes[j];
                if (j != partes.length - 1) {
                    lugares += ",";
                }
            }
            lista += "{Nombre:\"" + r.getNombre() + "\", Tipo:\"" + r.getTipo() + "\",Funcion:\"" + lugares + "\"}";
            if (i != variables.size() - 1) {
                lista += ",";
            }
        }
        return lista;
    }

    private String listarMetodos() {
        String lista = "";
        for (int i = 0; i < clases.size(); i++) {
            Repetido r = this.metodos.get(i);
            lista += "{Nombre:\"" + r.getNombre() + "\", Tipo:\"" + r.getTipo() + "\",Parametros:" + r.getNumParametros() + "}";
            if (i != clases.size() - 1) {
                lista += ",";
            }
        }
        return lista;
    }

    private String listarComentarios() {
        String lista = "";
        for (int i = 0; i < this.comentarios.size(); i++) {
            lista += "{Texto:\"" + this.comentarios.get(i).getNombre() + "\"}";
            if (i != clases.size() - 1) {
                lista += ",";
            }
        }
        return lista;
    }

}
