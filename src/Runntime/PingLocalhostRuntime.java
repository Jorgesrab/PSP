package Runntime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingLocalhostRuntime {
    public static void main(String[] args) {
        try {
            // Ejecutar el comando "ping localhost"
            Process process = Runtime.getRuntime().exec("ping localhost");

            // Capturar la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            System.out.println("Salida del ping a localhost (Runtime):\n");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("\nProceso finalizado con código: " + exitCode);
        } catch (Exception e) {
            System.err.println("Error al ejecutar ping con Runtime.");
            e.printStackTrace();
        }
    }
}
