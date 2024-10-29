package primeraPractica.HiloParEImpar;

public class Par extends Thread {
    public Par() {
    }

    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            if (i%2 == 0 ){
                System.out.println("Hilo par: "+ i);
            }
        }
    }
}
