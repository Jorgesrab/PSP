package Runntime;

import java.io.IOException;

public class ProcessCheck {
    public static void main(String[] args) {
        try {
            // Lanza un proceso (por ejemplo, "ping" con 5 paquetes)
            ProcessBuilder processBuilder = new ProcessBuilder("ping", "-c", "5", "google.com");
            Process process = processBuilder.start();

            System.out.println("Proceso iniciado. Comprobando su estado...");

            // Variable de control para el ciclo
            boolean procesoActivo = true;

            // Comprueba cada 3 segundos si el proceso sigue vivo
            while (procesoActivo) {
                if (process.isAlive()) {
                    System.out.println("El proceso sigue en ejecución...");
                } else {
                    System.out.println("El proceso ha terminado.");
                    procesoActivo = false;
                }

                // Espera 3 segundos antes de la siguiente comprobación
                Thread.sleep(3000);
            }

        } catch (IOException e) {
            System.err.println("Error al iniciar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }
}
