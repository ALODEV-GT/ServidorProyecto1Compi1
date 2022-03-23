package midik.comparacion;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import midik.cup.parser;
import midik.jflex.AnalizadorLexico;
import midik.tablaSimbolos.TablaSimbolos;
import midik.tablaSimbolos.Termino;

public class Comparar {

    private File directorioP1 = new File("Proyecto1");
    private File directorioP2 = new File("Proyecto2");
    private ArrayList<TablaSimbolos> tablasSimbolosP1 = new ArrayList<>();
    private ArrayList<TablaSimbolos> tablasSimbolosP2 = new ArrayList<>();
    private javax.swing.JTextArea taErrores;

    public Comparar(javax.swing.JTextArea taErrores) {
        this.taErrores = taErrores;
    }

    public void comparar() {
        File[] archivosP1 = directorioP1.listFiles();
        File[] archivosP2 = directorioP2.listFiles();

        for (int i = 0; i < archivosP1.length; i++) {
            obtenerTablasSimbolos(archivosP1[i], this.tablasSimbolosP1);
        }

        for (int i = 0; i < archivosP2.length; i++) {
            obtenerTablasSimbolos(archivosP2[i], this.tablasSimbolosP2);
        }
    }

    public void obtenerTablasSimbolos(File file, ArrayList<TablaSimbolos> contenedor) {
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
            taErrores.append("Archivo " + file.getName() + " analizado correctamente \n");
        } catch (Exception ex) {
            taErrores.append("Algo grave ocurrio con el analizador sintactico");
        }
    }
    
    
}
