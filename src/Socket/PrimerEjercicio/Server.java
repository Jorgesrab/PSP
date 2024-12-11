package Socket.PrimerEjercicio;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 12345; // Puerto del servidor
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor iniciado y escuchando en el puerto " + port);

            // Esperar conexión del cliente
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

            // Streams para comunicación
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            // Intercambio de datos
            String clientMessage;
            while ((clientMessage = input.readLine()) != null) {
                System.out.println("Mensaje del cliente: " + clientMessage);
                if ("bye".equalsIgnoreCase(clientMessage)) {
                    output.println("Adiós, cliente!");
                    break;
                }
                output.println("Servidor: " + clientMessage.toUpperCase());
            }

            clientSocket.close();
            System.out.println("Cliente desconectado.");
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
