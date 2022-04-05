package midik.comparacion;

import java.util.ArrayList;

public class GenerarJson {

    private ArrayList<Repetido> repetidos = null;
    private final ArrayList<Repetido> clases = new ArrayList<>();
    private final ArrayList<Repetido> variables = new ArrayList<>();
    private final ArrayList<Repetido> metodos = new ArrayList<>();
    private final ArrayList<Repetido> comentarios = new ArrayList<>();
    private final double score;
    private String json = "{\n";

    public GenerarJson(ArrayList<Repetido> repetidos, double score) {
        this.repetidos = repetidos;
        this.score = score;
    }

    public String generar() {
        this.agrupar();

        //Score
        json += "\tScore:\"" + score + "\",\n";

        //Clases
        json += "\tClases:[" + listarClases() + "\n\t],\n";

        //Variables
        json += "\tVariables:[" + listarVariables() + "\n\t],\n";

        //Metodos
        json += "\tMetodos:[" + listarMetodos() + "\n\t],\n";

        //Comentarios
        json += "\tComentarios:[" + listarComentarios() + "\n\t]\n";

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
            lista += "\n\t\t{Nombre:\"" + this.clases.get(i).getNombre() + "\"}";
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
            lista += "\n\t\t{Nombre:\"" + r.getNombre() + "\", Tipo:\"" + r.getTipo() + "\",Funcion:\"" + lugares + "\"}";
            if (i != variables.size() - 1) {
                lista += ",";
            }
        }
        return lista;
    }

    private String listarMetodos() {
        String lista = "";
        for (int i = 0; i < this.metodos.size(); i++) {
            Repetido r = this.metodos.get(i);
            lista += "\n\t\t{Nombre:\"" + r.getNombre() + "\", Tipo:\"" + r.getTipo() + "\",Parametros:" + r.getNumParametros() + "}";
            if (i != metodos.size() - 1) {
                lista += ",";
            }
        }
        return lista;
    }

    private String listarComentarios() {
        String lista = "";
        for (int i = 0; i < this.comentarios.size(); i++) {
            lista += "\n\t\t{Texto:\"" + this.comentarios.get(i).getNombre() + "\"}";
            if (i != comentarios.size() - 1) {
                lista += ",";
            }
        }
        return lista;
    }

}
