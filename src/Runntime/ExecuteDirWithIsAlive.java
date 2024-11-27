package Runntime;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteDirWithIsAlive {
    public static void main(String[] args) {
        try {
            // Crear el proceso para ejecutar el comando "dir"
            Process process = Runtime.getRuntime().exec("ping -n 5 google.com");

            // Hilo para leer la salida mientras el proceso está vivo
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // Comprobar periódicamente si el proceso sigue vivo
            while (process.isAlive()) {
                System.out.println("El proceso sigue en ejecución...");
                Thread.sleep(2000); // Esperar medio segundo antes de volver a comprobar
            }

            System.out.println("El proceso ha terminado.");
            int exitCode = process.waitFor(); // Obtener el código de salida final
            System.out.println("Código de salida: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
