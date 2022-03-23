package midik.comparacion;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import midik.cup.parser;
import midik.jflex.AnalizadorLexico;
import midik.tablaSimbolos.Roles;
import midik.tablaSimbolos.TablaSimbolos;
import midik.tablaSimbolos.Termino;

public class Comparar {

    private final File directorioP1 = new File("Proyecto1");
    private final File directorioP2 = new File("Proyecto2");
    private final ArrayList<TablaSimbolos> tablasSimbolosP1 = new ArrayList<>();
    private final ArrayList<TablaSimbolos> tablasSimbolosP2 = new ArrayList<>();
    private final javax.swing.JTextArea taErrores;
    private final ArrayList<Repetido> repetidos = new ArrayList<>();
    private final DataOutputStream out;
    private boolean errores = false;

    public Comparar(javax.swing.JTextArea taErrores, DataOutputStream out) {
        this.taErrores = taErrores;
        this.out = out;
    }

    public void comparar() throws IOException {
        this.analizarArchivos();
        if (errores) {
            out.writeUTF("No se pudo generar el json, hay errores");
        } else {
            this.iniciarComparacion();
        }
    }

    private void iniciarComparacion() throws IOException {
        for (int i = 0; i < tablasSimbolosP1.size(); i++) {
            ArrayList<Termino> tsArchivo = tablasSimbolosP1.get(i).getTablaSimbolos(); //Archivo
            for (int j = 0; j < tsArchivo.size(); j++) {
                Termino termino = tsArchivo.get(j); //Termino
                compararTerminoConTablas(termino, tsArchivo);
            }
        }
        if (this.repetidos.size() > 0) {
            out.writeUTF("Comparacion finalizada, se genero el json: \n" + crearRespuesta());
        } else {
            out.writeUTF("Comparacion finalizada, NO SE ENCONTRARON REPETIDOS");
        }
    }

    private String crearRespuesta() {
        String respuesta = "";
        for (Repetido res : this.repetidos) {
            respuesta += res.toString() + "\n";
        }
        return respuesta;
    }

    private ArrayList<Termino> obtenerParametrosFuncion(ArrayList<Termino> terminos, String nombreFuncion) {
        ArrayList<Termino> parametros = new ArrayList<>();
        for (int i = 0; i < terminos.size(); i++) {
            if (terminos.get(i).isParametro() && terminos.get(i).getAmbito().equals("Metodo " + nombreFuncion)) {
                parametros.add(terminos.get(i));
            }
        }
        return parametros;
    }
    
    private ArrayList<Termino> obtenerMetodosClase(ArrayList<Termino> terminos) {
        ArrayList<Termino> metodos = new ArrayList<>();
        for (int i = 0; i < terminos.size(); i++) {
            if (terminos.get(i).getRol().equals(Roles.METODO)) {
                metodos.add(terminos.get(i));
            }
        }
        return metodos;
    }

    private void compararTerminoConTablas(Termino terminoP1, ArrayList<Termino> tsArchivoP1) {
        Roles rol = terminoP1.getRol();
        String lugarRepitencia = "";
        String tipo = "";
        boolean repetido = false;
        int numParametros = 0;

        for (int i = 0; i < tablasSimbolosP2.size(); i++) {
            ArrayList<Termino> tsArchivo = tablasSimbolosP2.get(i).getTablaSimbolos(); //Archivo
            for (int j = 0; j < tsArchivo.size(); j++) {
                Termino termino = tsArchivo.get(j); //termino

                if (termino.getRol().equals(rol)) {
                    switch (rol) {
                        case CLASE:
                            if (terminoP1.getNombre().equals(termino.getNombre())) {
                                boolean iguales = compararClases(tsArchivo, tsArchivoP1);
                                repetido = iguales;
                            }
                            break;
                        case COMENTARIO_BLOQUE:
                        case COMENTARIO_LINEA:
                            if (terminoP1.getNombre().equals(termino.getNombre())) {
                                repetido = true;
                                break;
                            }
                            break;
                        case METODO:
                            if (terminoP1.getNombre().equals(termino.getNombre()) && terminoP1.getTipo().equals(termino.getTipo())) {
                                ArrayList<Termino> parametrosFuncion = obtenerParametrosFuncion(tsArchivoP1, terminoP1.getNombre());
                                boolean iguales = compararParametros(tsArchivo, parametrosFuncion, terminoP1.getNombre());
                                numParametros = parametrosFuncion.size();
                                repetido = iguales;
                            }
                            break;
                        case VARIABLE:
                            if (terminoP1.getNombre().equals(termino.getNombre()) && terminoP1.getTipo().equals(termino.getTipo())) {
                                lugarRepitencia += termino.getAmbito() + ",";
                                repetido = true;
                            }
                            break;
                    }
                }

            }
        }

        if (repetido) {
            this.repetidos.add(new Repetido(terminoP1.getNombre(), rol, terminoP1.getAmbito() + "," + lugarRepitencia, terminoP1.getTipo(), numParametros));
        }
    }

    private boolean compararParametros(ArrayList<Termino> tsArchivoP2, ArrayList<Termino> parametrosFuncion, String nombreFuncion) {
        boolean iguales = false;
        ArrayList<Termino> parametrosArchivoP2 = obtenerParametrosFuncion(tsArchivoP2, nombreFuncion);
        int contador = 0;
        if (parametrosFuncion.size() == parametrosArchivoP2.size()) {
            for (int i = 0; i < parametrosFuncion.size(); i++) {
                if (parametrosFuncion.get(i).getNombre().equals(parametrosArchivoP2.get(i).getNombre())
                        && parametrosFuncion.get(i).getTipo().equals(parametrosArchivoP2.get(i).getTipo())) {
                    contador++;
                }
            }

            if (parametrosFuncion.size() == contador) {
                iguales = true;
            }
        }

        return iguales;
    }
    
    private boolean compararClases(ArrayList<Termino> tsArchivoP1, ArrayList<Termino> tsArchivoP2) {
        boolean iguales = false;
        ArrayList<Termino> metodosArchivoP1 = obtenerMetodosClase(tsArchivoP1);
        ArrayList<Termino> metodosArchivoP2 = obtenerMetodosClase(tsArchivoP2);
        int contador = 0;
        if (metodosArchivoP1.size() == metodosArchivoP2.size()) {
            for (int i = 0; i < metodosArchivoP1.size(); i++) {
                for (int j = 0; j < metodosArchivoP2.size(); j++) {
                    if (metodosArchivoP1.get(i).getNombre().equals(metodosArchivoP2.get(j).getNombre()) ) {
                        contador++;
                        break;
                    }
                }
            }

            if (metodosArchivoP1.size() == contador) {
                iguales = true;
            }
        }
        return iguales;
    }

    private void analizarArchivos() {
        File[] archivosP1 = directorioP1.listFiles();
        File[] archivosP2 = directorioP2.listFiles();

        for (File archivosP11 : archivosP1) {
            analizar(archivosP11, this.tablasSimbolosP1);
        }

        for (File archivosP21 : archivosP2) {
            analizar(archivosP21, this.tablasSimbolosP2);
        }
    }

    private void analizar(File file, ArrayList<TablaSimbolos> contenedor) {
        String entrada = "";
        try {
            entrada = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException ex) {

        }

        StringReader sr = new StringReader(entrada);
        TablaSimbolos ts = new TablaSimbolos();
        AnalizadorLexico lexer = new AnalizadorLexico(sr, ts, taErrores);
        parser par = new parser(lexer, ts, taErrores);
        contenedor.add(ts);

        try {
            par.parse();

            if (lexer.isErrores() == true || par.isErrores() == true) {
                this.errores = true;
            } else {
                taErrores.append("Archivo " + file.getName() + " analizado correctamente \n");
            }

        } catch (Exception ex) {
            this.errores = true;
            taErrores.append("Algo grave ocurrio con el analizador sintactico");
        }
    }

}
