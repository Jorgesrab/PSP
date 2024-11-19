package Concurrencia.EjercicioHolaMundo;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {

            HiloHola hiloHola = new HiloHola(i+1);
            Thread hilo = new Thread(hiloHola);

            hilo.start();
        }

    }
}
