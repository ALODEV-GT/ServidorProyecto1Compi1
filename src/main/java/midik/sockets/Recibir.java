package midik.sockets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

public class Recibir {

    private final DataInputStream in;
    private final String SEPARADOR = System.getProperty("file.separator");
    private final String pathProyecto1;
    private final String pathProyecto2;

    public Recibir(DataInputStream in) {
        this.pathProyecto1 = getPathEjecucion() + SEPARADOR + "Proyecto1";
        this.pathProyecto2 = getPathEjecucion() + SEPARADOR + "Proyecto2";
        this.in = in;
    }

    public void recibir() throws IOException {
        limpiarDirectorios();
        crearDirectorios();
        int cantidadArchivosP1 = this.in.readInt();
        int cantidadArchivosP2 = this.in.readInt();

        for (int i = 0; i < cantidadArchivosP1; i++) {
            recibirArchivo(pathProyecto1);
        }

        for (int i = 0; i < cantidadArchivosP2; i++) {
            recibirArchivo(pathProyecto2);
        }
    }

    private void recibirArchivo(String nombreDirectorio) throws IOException {
        String nombreArchivo = in.readUTF();
        int tamanioArchivo = in.readInt();
        byte[] contenidoArchivo = new byte[tamanioArchivo];
        for (int j = 0; j < tamanioArchivo; j++) {
            contenidoArchivo[j] = in.readByte();
        }
        String contenido = new String(contenidoArchivo);
        FileWriter fw = new FileWriter(nombreDirectorio + SEPARADOR + nombreArchivo);
        fw.write(contenido);
        fw.close();
    }

    private void crearDirectorios() {
        File directorioP1 = new File(pathProyecto1);
        File directorioP2 = new File(pathProyecto2);
        if (!directorioP1.exists()) {
            directorioP1.mkdir();
        }
        if (!directorioP2.exists()) {
            directorioP2.mkdir();
        }
    }

    public static String getPathEjecucion() {
        File directorio = null;
        try {
            File jar = new File(midik.sockets.Servidor.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            directorio = new File(jar.getParentFile().getPath());
        } catch (URISyntaxException ex) {
            ex.printStackTrace(System.out);
        }
        return directorio.getAbsolutePath();
    }

    private void limpiarDirectorios() {
        File directorioP1 = new File(pathProyecto1);
        File directorioP2 = new File(pathProyecto2);
        File[] archivosP1 = directorioP1.listFiles();
        File[] archivosP2 = directorioP2.listFiles();

        if (archivosP1 != null) {
            for (int i = 0; i < archivosP1.length; i++) {
                archivosP1[i].delete();
            }
        }

        if (archivosP2 != null) {
            for (int i = 0; i < archivosP2.length; i++) {
                archivosP2[i].delete();
            }
        }
    }
}
