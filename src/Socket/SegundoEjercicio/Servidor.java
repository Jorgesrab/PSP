package Socket.SegundoEjercicio;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 12345; // Puerto del servidor
    private static final List<ClientHandler> jugadores = Collections.synchronizedList(new ArrayList<>()); // Lista de jugadores
    private static int numeroSecreto; // Número secreto generado por el servidor
    private static int jugadorActual = 0; // Índice del jugador que tiene el turno

    public static void main(String[] args) {
        System.out.println("** Servidor de la Batalla de Números **");
        System.out.println("Esperando jugadores...");

        // Generar un número secreto aleatorio entre 1 y 100
        numeroSecreto = new Random().nextInt(100) + 1;
        System.out.println("Número secreto generado: " + numeroSecreto); // Solo para pruebas/mostrar al desarrollador

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                // Aceptar una conexión de algún cliente
                Socket socketCliente = serverSocket.accept();
                System.out.println("Nuevo jugador conectado: " + socketCliente.getInetAddress().getHostAddress());

                // Crear un cliente manejador y asignarle un hilo
                ClientHandler nuevoJugador = new ClientHandler(socketCliente, jugadores.size() + 1);
                jugadores.add(nuevoJugador);
                new Thread(nuevoJugador).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase manejadora de cliente
    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private final int jugadorId; // Identificador único del jugador
        private BufferedReader entrada;
        private PrintWriter salida;

        public ClientHandler(Socket socket, int jugadorId) {
            this.socket = socket;
            this.jugadorId = jugadorId;
        }

        @Override
        public void run() {
            try {
                entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                salida = new PrintWriter(socket.getOutputStream(), true);

                // Enviar bienvenida al cliente
                salida.println("Bienvenido, Jugador " + jugadorId + "! Esperando tu turno...");

                // Comunicar sistema de turnos
                while (true) {
                    synchronized (jugadores) {
                        // Turno del jugador actual
                        if (jugadores.indexOf(this) == jugadorActual) {
                            salida.println("¡Es tu turno, Jugador " + jugadorId + "! Adivina el número (entre 1 y 100):");

                            // Leer la entrada del cliente
                            String input = entrada.readLine();
                            if (input == null || input.isEmpty()) continue; // Manejar entradas vacías

                            try {
                                int intento = Integer.parseInt(input);

                                if (intento == numeroSecreto) {
                                    // Si el jugador acierta, notificar a todos y finalizar el juego
                                    broadcast("¡El Jugador " + jugadorId + " ha acertado el número y ha ganado el juego!");
                                    salida.println("¡Felicidades! Has ganado.");
                                    cerrarConexiones();
                                    break;
                                } else if (intento < numeroSecreto) {
                                    salida.println("El número secreto es mayor. ¡Espera tu próximo turno!");
                                } else {
                                    salida.println("El número secreto es menor. ¡Espera tu próximo turno!");
                                }

                                // Cambiar al siguiente jugador
                                jugadorActual = (jugadorActual + 1) % jugadores.size();
                                broadcast("Turno cambiado: Jugador " + (jugadorActual + 1));

                            } catch (NumberFormatException e) {
                                salida.println("Por favor introduce un número válido.");
                            }
                        } else {
                            // Notificar si no es el turno del cliente
                            Thread.sleep(100); // Evitar alta frecuencia de bucle
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Método para enviar mensajes a todos los jugadores conectados
        private void broadcast(String mensaje) {
            synchronized (jugadores) {
                for (ClientHandler jugador : jugadores) {
                    jugador.salida.println(mensaje);
                }
            }
        }

        // Cerrar conexiones del servidor
        private void cerrarConexiones() {
            synchronized (jugadores) {
                for (ClientHandler jugador : jugadores) {
                    try {
                        jugador.salida.println("¡El juego ha terminado! Cerrando conexión...");
                        jugador.socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                jugadores.clear(); // Limpiar la lista de jugadores
            }
        }
    }
}