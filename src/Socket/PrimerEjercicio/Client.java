package Socket.PrimerEjercicio;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "192.168.137.204"; // Dirección del servidor
        int port = 12345; // Puerto del servidor

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conectado al servidor en " + host + ":" + port);

            // Streams para comunicación
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Entrada desde teclado
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            System.out.println("Escribe mensajes para el servidor (escribe 'bye' para salir):");
            while ((userInput = keyboard.readLine()) != null) {
                output.println(userInput); // Enviar mensaje al servidor
                String serverResponse = input.readLine(); // Leer respuesta del servidor
                System.out.println("Respuesta del servidor: " + serverResponse);
                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
