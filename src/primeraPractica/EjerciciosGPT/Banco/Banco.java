package primeraPractica.EjerciciosGPT.Banco;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private int cantidad;
    private final Object lock;


    public Banco(int cantidad, Object lock) {
        this.cantidad = cantidad;

        this.lock = lock;
    }


    public void depositar(int cantidad){
        synchronized (lock) {

            this.cantidad += cantidad;
            System.out.println("Tienes " + this.cantidad + " euros");

        }
    }

    public void retirar(int cantidad){

        synchronized (lock) {

            if (this.cantidad >= cantidad) {
                this.cantidad -= cantidad;
                System.out.println("Tienes " + this.cantidad + " euros");
            }else {
                System.out.println("No tienes " + this.cantidad + " euros");
            }
        }
    }
}
