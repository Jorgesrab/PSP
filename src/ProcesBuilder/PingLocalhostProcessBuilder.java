package ProcesBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingLocalhostProcessBuilder {
    public static void main(String[] args) {
        try {
            // Crear un ProcessBuilder para ejecutar "ping localhost"
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "ping localhost");

            // Iniciar el proceso
            Process process = builder.start();

            // Capturar la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            System.out.println("Salida del ping a localhost (ProcessBuilder):\n");

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("\nProceso finalizado con código: " + exitCode);
        } catch (Exception e) {
            System.err.println("Error al ejecutar ping con ProcessBuilder.");
            e.printStackTrace();
        }
    }
}
