package primeraPractica.EjerciciosGPT.Banco;

public class Main {
    public static void main(String[] args) {
        Object lock = new Object();


        Banco banco = new Banco(1000,lock);

        Runnable incrementar = () ->{
            for (int i = 0; i <10; i++) {
                      banco.depositar(100);
            }
        };

        Runnable retirar  = () ->{
            for (int i = 0; i <10; i++) {
                banco.retirar(100);
            }
        };

        Thread incrementarThread = new Thread(incrementar);
        Thread retirarThread = new Thread(retirar);
        incrementarThread.start();
        retirarThread.start();

        try {
            incrementarThread.join();
            retirarThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
