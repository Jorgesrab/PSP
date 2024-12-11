package Concurrencia.Museo;



import java.util.concurrent.Semaphore;

class Museo {
    Semaphore aforo = new Semaphore(5, true);
    Semaphore puertaDoble = new Semaphore(2, true);
    Semaphore salaEspecial = new Semaphore(1, true);

    public Museo() {

    }


    class Turista implements Runnable {
        int id;
        Museo museo;

        public Turista(int id, Museo museo) {
            this.id = id;
            this.museo = museo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        @Override
        public void run() {
            Thread.currentThread().setName("Turista " + id);
            try {

                // Intentar entrar a través de la puerta doble
                Thread.sleep((long) (Math.random() * 3000));
                museo.puertaDoble.acquire();
                System.out.println(Thread.currentThread().getName() + " ha entrado en la puerta doble (Permisos disponibles en puerta doble: " + museo.puertaDoble.availablePermits() + ")");

                // Adquirir acceso al museo
                museo.aforo.acquire();
                museo.puertaDoble.release();
                System.out.println(Thread.currentThread().getName() + " ha entrado en el museo y ha salido de la puerta doble (Permisos disponibles en aforo: " + museo.aforo.availablePermits() + ", puerta doble: " + museo.puertaDoble.availablePermits() + ")");

                // Intentar entrar a la sala especial
                museo.salaEspecial.acquire();
                System.out.println(Thread.currentThread().getName() + " ha entrado en la sala especial (Permisos disponibles en sala especial: " + museo.salaEspecial.availablePermits() + ")");

                // Simular tiempo en la sala especial
                Thread.sleep((long) (Math.random() * 3000));

                // Salir de la sala especial
                museo.salaEspecial.release();
                System.out.println(Thread.currentThread().getName() + " ha salido de la sala especial (Permisos disponibles en sala especial después de liberar: " + museo.salaEspecial.availablePermits() + ")");

                // Simular tiempo en el museo antes de salir
                Thread.sleep((long) (Math.random() * 30000));

                // Salir del museo
                museo.aforo.release();
                System.out.println(Thread.currentThread().getName() + " ha salido del museo (Permisos disponibles en aforo después de liberar: " + museo.aforo.availablePermits() + ")");

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}




//public class Main {
//    public static void main(String[] args) {
//        Museo museo = new Museo();
//        int numeroTuristas=10;
//        Thread[] turistas = new Thread[numeroTuristas];
//
//        for (int i = 0; i < numeroTuristas; i++) {
//            turistas[i]=new Thread(new Turista(i, museo));
//            turistas[i].start();
//
//        }
//        for (Thread turista: turistas) {
//            try {
//                turista.join();
//            }catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//
//        }
//
//    }
//}
