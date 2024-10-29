package primeraPractica.ParImparSincro1;

public class ImparS extends Thread {

    public ImparS() {
    }

    @Override
    public synchronized void run(){

        for (int i = 0; i <= 100; i++) {
            if (i%2!=0){
                System.out.println("Hilo impar: "+ i);
            }
        }

    }
}
