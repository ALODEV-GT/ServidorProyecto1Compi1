package midik.sockets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Recibir {

    private final DataInputStream in;

    public Recibir(DataInputStream in) {
        this.in = in;
    }

    public void recibir() throws IOException {
        limpiarDirectorios();
        crearDirectorios();
        int cantidadArchivosP1 = this.in.readInt();
        int cantidadArchivosP2 = this.in.readInt();

        for (int i = 0; i < cantidadArchivosP1; i++) {
            recibirArchivo("Proyecto1");
        }

        for (int i = 0; i < cantidadArchivosP2; i++) {
            recibirArchivo("Proyecto2");
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
        FileWriter fw = new FileWriter(nombreDirectorio + "/" + nombreArchivo);
        fw.write(contenido);
        fw.close();
    }

    private void crearDirectorios() {
        File directorioP1 = new File("Proyecto1");
        File directorioP2 = new File("Proyecto2");
        if (!directorioP1.exists()) {
            directorioP1.mkdir();
        }
        if (!directorioP2.exists()) {
            directorioP2.mkdir();
        }
    }

    private void limpiarDirectorios() {
        File directorioP1 = new File("Proyecto1");
        File directorioP2 = new File("Proyecto2");

        File[] archivosP1 = directorioP1.listFiles();
        File[] archivosP2 = directorioP2.listFiles();

        for (int i = 0; i < archivosP1.length; i++) {
            archivosP1[i].delete();
        }

        for (int i = 0; i < archivosP2.length; i++) {
            archivosP2[i].delete();
        }
    }
}
