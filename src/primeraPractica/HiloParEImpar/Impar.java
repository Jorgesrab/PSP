package primeraPractica.HiloParEImpar;

public class Impar extends Thread {

    public Impar() {
    }

    @Override
    public void run(){

        for (int i = 0; i <= 100; i++) {
            if (i%2!=0){
                System.out.println("Hilo impar: "+ i);
            }
        }

    }
}
