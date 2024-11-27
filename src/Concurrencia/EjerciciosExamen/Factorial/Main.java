package Concurrencia.EjerciciosExamen.Factorial;


class Factorial {
    private int valor = 1;

    public synchronized void multiplicar(int numero) {
        valor *= numero;
    }

    public void calcularFactorial(int inicio, int fin, int resultadoParcial) {
        try {
            for (int i = inicio; i <= fin; i++) {
                Thread.sleep(i * 10L);
                resultadoParcial *= i;
            }
            System.out.println("El " + Thread.currentThread().getName() + " desde " + inicio + " hasta " + fin + ": " + resultadoParcial);
            multiplicar(resultadoParcial);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getValor() {
        return valor;
    }
}

class Operacion implements Runnable {

    private Factorial factorial;
    private int inicio;
    private int fin;


    public Operacion(Factorial factorial, int inicio, int fin) {
        this.factorial = factorial;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        factorial.calcularFactorial(inicio, fin, 1);
    }
}

public class Main {
    public static void main(String[] args) {
        final int TOTAL_HILOS =4;
        final int ITERACIONES_FACTORIAL = 15;

        final int tamanoChunk = ITERACIONES_FACTORIAL / TOTAL_HILOS;

        Thread[] hilos = new Thread[TOTAL_HILOS];
        Factorial factorial = new Factorial();

        for (int i = 0; i < hilos.length; i++) {
            int inicio = i * tamanoChunk + 1;
            int fin = (i == hilos.length - 1) ? ITERACIONES_FACTORIAL : (i + 1) * tamanoChunk;
            hilos[i] = new Thread(new Operacion(factorial, inicio, fin) , "Hilo " + (i +1));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Valor del factorial: " + factorial.getValor());

    }
}