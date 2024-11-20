package Concurrencia.ProductoConsumidor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProductorConsumidorConBlockingQueue {

    public static void main(String[] args) {
        // Crear una BlockingQueue con capacidad de 5
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // Crear productor y consumidor
        Thread productor = new Thread(new Productor(queue));
        Thread consumidor = new Thread(new Consumidor(queue));

        // Iniciar los hilos
        productor.start();
        consumidor.start();
    }
}

// Clase Productor
class Productor implements Runnable {
    private BlockingQueue<Integer> queue;

    public Productor(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int item = 0; // Inicializamos el item que será producido
        try {
            while (true) {
                System.out.println("Producido: " + item);
                queue.put(item++); // Inserta el elemento, bloqueando si la cola está llena
                Thread.sleep(500); // Simula el tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Clase Consumidor
class Consumidor implements Runnable {
    private BlockingQueue<Integer> queue;

    public Consumidor(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = queue.take(); // Toma un elemento, bloqueando si la cola está vacía
                System.out.println("Consumido: " + item);
                Thread.sleep(1000); // Simula el tiempo de consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
