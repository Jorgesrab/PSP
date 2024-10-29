package primeraPractica.EjerciciosGPT.Contador;

public class Contador {
    private int contador;

    public Contador() {
        this.contador = 0;
    }

    public synchronized void incrementar(){
        this.contador++;

    }

    public void obtenerValor(){
        System.out.println("El valor del contador es: " + this.contador);
    }
}
