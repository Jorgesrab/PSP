package Concurrencia.Museo;

import java.util.concurrent.Semaphore;

class Museo {
    Semaphore  aforo = new Semaphore(5, true);
    Semaphore puertaDoble = new Semaphore(2, true);
    Semaphore salaEspecial = new Semaphore(1, true);

    public Museo() {

    }


}

class Turista implements Runnable {
    int id;
    Museo museo;

    public Turista(int id, Museo museo) {
        this.id = id;
        this.museo = museo;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Turista " + id);
        try {
            entrarPuertaDoble();
            entrarMuseo();
            entrarSalaEspecial();
            salirSalaEspecial();
            salirMuseo();
        } catch (InterruptedException e) {
            System.err.println(Thread.currentThread().getName() + " fue interrumpido: " + e.getMessage());
        }
    }

    private void entrarPuertaDoble() throws InterruptedException {
        museo.puertaDoble.acquire();
        System.out.println(Thread.currentThread().getName() + " ha entrado en la puerta doble (Permisos disponibles en puerta doble: " + museo.puertaDoble.availablePermits() + ")");
    }

    private void entrarMuseo() throws InterruptedException {
        museo.aforo.acquire();
        museo.puertaDoble.release();
        System.out.println(Thread.currentThread().getName() + " ha entrado en el museo y ha salido de la puerta doble (Permisos disponibles en aforo: " + museo.aforo.availablePermits() + ", puerta doble: " + museo.puertaDoble.availablePermits() + ")");
    }

    private void entrarSalaEspecial() throws InterruptedException {
        museo.salaEspecial.acquire();
        System.out.println(Thread.currentThread().getName() + " ha entrado en la sala especial (Permisos disponibles en sala especial: " + museo.salaEspecial.availablePermits() + ")");
        Thread.sleep((long) (Math.random() * 1000)); // Simular tiempo en la sala especial
    }

    private void salirSalaEspecial() throws InterruptedException {
        museo.salaEspecial.release();
        System.out.println(Thread.currentThread().getName() + " ha salido de la sala especial (Permisos disponibles en sala especial después de liberar: " + museo.salaEspecial.availablePermits() + ")");
    }

    private void salirMuseo() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 500)); // Simular tiempo en el museo antes de salir
        museo.aforo.release();
        System.out.println(Thread.currentThread().getName() + " ha salido del museo (Permisos disponibles en aforo después de liberar: " + museo.aforo.availablePermits() + ")");
    }
}




public class Main {
    public static void main(String[] args) {
        Museo museo = new Museo();

        for (int i = 0; i < 10; i++) {
            Turista turista = new Turista(i, museo);
            new Thread(turista).start();

        }
    }
}
