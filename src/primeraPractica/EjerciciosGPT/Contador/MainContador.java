package primeraPractica.EjerciciosGPT.Contador;

public class MainContador {
    public static void main(String[] args) {

        Contador contador = new Contador();

        Runnable incremtoDeContador = () -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        };

        Thread t1 = new Thread(incremtoDeContador);
        Thread t2 = new Thread(incremtoDeContador);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        contador.obtenerValor();

    }
}
