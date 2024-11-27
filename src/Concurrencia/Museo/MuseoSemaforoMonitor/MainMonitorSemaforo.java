package Concurrencia.Museo.MuseoSemaforoMonitor;




import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class Museo {
    protected Semaphore aforo = new Semaphore(5, true);
    protected Semaphore salaEspecial = new Semaphore(1, true);
    protected final ReentrantLock puertaDoble1 = new ReentrantLock();
    protected final ReentrantLock puertaDoble2 = new ReentrantLock();
    protected final BlockingQueue<Thread> cola = new LinkedBlockingQueue<>();



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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }


    public void entrarCola(){

        try{

            museo.cola.put(Thread.currentThread());

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

    }
    public void entraMuseo(){

        boolean entro = false;
        try {


            while (!entro) {
                if (museo.puertaDoble1.tryLock()) {
                    try {
                        // Usar puerta 1
                        System.out.println(Thread.currentThread().getName() + " pasa por la Puerta 1.");
                        Thread.sleep(500); // Simular tiempo dentro
                        entro = true;
                    } finally {
                        museo.puertaDoble1.unlock(); // Liberar puerta 1

                    }
                } else if (museo.puertaDoble2.tryLock()) {
                    try {
                        // Usar puerta 2
                        System.out.println(Thread.currentThread().getName() + " pasa por la Puerta 2.");
                        Thread.sleep(500); // Simular tiempo dentro
                        entro = true;
                    } finally {
                        museo.puertaDoble2.unlock(); // Liberar puerta 2
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    @Override
    public void run() {

    }
}




public class  MainMonitorSemaforo {
    public static void main(String[] args) {

    }
}
