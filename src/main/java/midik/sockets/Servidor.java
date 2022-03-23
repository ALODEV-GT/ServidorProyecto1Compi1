package midik.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import midik.comparacion.Comparar;
import midik.frontend.VtnErrores;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;
        final int PUERTO = 5000;

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado.");
            VtnErrores vtnErrores = new VtnErrores();
            vtnErrores.setVisible(true);

            while (true) {
                sc = servidor.accept(); //A la espera de un cliente
                System.out.println("Cliente conectado.");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                Recibir recibir = new Recibir(in);
                recibir.recibir();

                Comparar comparar = new Comparar(vtnErrores.getTaErrores(), out);
                comparar.comparar();
                

                sc.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
