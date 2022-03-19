package midik.test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import midik.cup.parser;
import midik.jflex.AnalizadorLexico;
import midik.tablaSimbolos.Termino;

public class Main {

    public static void main(String[] args) {
        File archivo = new File("/home/midik/entradaJava.java");
        String entrada = "";
        try {
            entrada = new String(Files.readAllBytes(archivo.toPath()));
        } catch (IOException ex) {

        }
        StringReader sr = new StringReader(entrada);
        AnalizadorLexico lexer = new AnalizadorLexico(sr);
        parser par = new parser(lexer);

        try {
            par.parse();
            ArrayList<Termino> tablaSimbolos = par.getTablaSimbolos();
            System.out.println("<-------------TABLA SIMBOLOS------------>");
            for (Termino term : tablaSimbolos) {
                System.out.println(term);
            }
            System.out.println("Analisis correcto");
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            System.out.println("Ocurrieron errores");
        }
    }

}
