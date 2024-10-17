package primeraPractica.cienHilos;

public class Hilo extends Thread{
    private int numero;


    public Hilo(int i) {
        this.numero = i;
    }

    @Override
    public void run() {
        System.out.println("Soy el hilo "+ this.numero);

    }
}
