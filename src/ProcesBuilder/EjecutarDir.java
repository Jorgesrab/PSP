package ProcesBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EjecutarDir {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar el comando dir
            ProcessBuilder builder = new ProcessBuilder();
            builder.command("cmd.exe", "/c", "dir -c");

            // Iniciar el proceso
            Process process = builder.start();

            // Capturar la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            System.out.println("Contenido del directorio actual:\n");

            // Leer y mostrar cada línea de la salida del comando
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("\nProceso finalizado con código: " + exitCode);

        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Ocurrió un error al ejecutar el comando dir.");
            e.printStackTrace();
        }
    }
}
