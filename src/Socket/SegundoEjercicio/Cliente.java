package Socket.SegundoEjercicio;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        final String HOST = "127.0.0.1"; // Dirección IP del servidor
        final int PUERTO = 12345;       // Puerto del servidor

        try (Socket socket = new Socket(HOST, PUERTO)) {
            System.out.println("Conectado al servidor de la Batalla de Números.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Hilo para escuchar mensajes del servidor
            Thread listener = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = entrada.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Desconectado del servidor.");
                }
            });

            listener.start();

            // Leer mensajes desde el teclado
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String mensaje = teclado.readLine();
                salida.println(mensaje);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}