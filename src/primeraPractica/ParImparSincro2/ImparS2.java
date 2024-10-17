package primeraPractica.ParImparSincro2;
import java.util.concurrent.locks.Lock;


public class ImparS2 extends Thread {

    private final Object lock;

    public ImparS2(Object lock) {
        this.lock = lock;
    }


    @Override
    public void run(){

        for (int i = 1; i <= 100; i++) {
            if (i%2!=0) {
                synchronized (lock) {

                    System.out.println("Hilo impar: " + i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (lock) {
                    lock.notify();
                }
            }
        }

    }
}
