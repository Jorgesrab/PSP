package primeraPractica.ParImparSincro1;

public class Par extends Thread {
    public Par() {
    }

    @Override
    public synchronized void run(){
        for (int i = 0; i < 100; i++) {
            if (i%2 == 0 ){
                System.out.println("Hilo par: "+


                        i);
            }
        }
    }
}
