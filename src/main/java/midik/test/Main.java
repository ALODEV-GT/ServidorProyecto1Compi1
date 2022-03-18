package midik.test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import midik.cup.parser;
import midik.jflex.AnalizadorLexico;

public class Main {
    public static void main(String[] args) {
        File archivo = new File("/home/midik/entradaJava.txt");
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
            System.out.println("Analisis correcto");
        } catch (Exception ex) {
            System.out.println("Ocurrieron errores");
        }
    }
    
    public int metodo(){
        if (true) {
            return 0;
        }
        boolean existe = true;
        while(existe){
            return 0;
        }

        int valor = 0;
        switch(valor){
            case 1: 
                return 3;
        }
        
        for (int i = 0; i < 10; i++) {
            return 0;
        }
        
        return 0;
    }
}
