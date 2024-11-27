package Runntime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EjecutarDirConRuntime {
    public static void main(String[] args) {
        try {
            // Ejecutar el comando dir usando Runtime
            Process process = Runtime.getRuntime().exec("cmd.exe /c dir");

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
