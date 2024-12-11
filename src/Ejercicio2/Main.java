package Ejercicio2;


//Declaración del banco
class Banco {
    final Object lock = new Object();




    //Metodo para usar el cajero sincronizado por un ReentrantLock
    public void usarcajero() throws InterruptedException {
        Thread.sleep(Thread.currentThread().threadId() *50);

        System.out.println("estoy en el cajero "+Thread.currentThread().getName());

        cajero();


    }

    public synchronized void cajero() throws InterruptedException {

        System.out.println("El cliente " + Thread.currentThread().getName() + " esta usando el cajero");

        Thread.sleep( 2000);

        System.out.println("El cliente " + Thread.currentThread().getName() + " ha dejado de usar el cajero cajero");

    }

}
class Cliente implements Runnable {
    Banco banco;
    int id;


    public Cliente(Banco banco, int id) {
        this.banco = banco;
        this.id = id;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(String.valueOf(id));
        try {
            banco.usarcajero();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        final int NUMERO_CLIENTES = 10;

        Thread[] clientes = new Thread[NUMERO_CLIENTES];

        for (int i = 0; i < NUMERO_CLIENTES; i++) {
            int id = i+1;
            clientes[i] = new Thread(new Cliente(banco,id));
            clientes[i].start();
        }

        for (Thread cliente : clientes){
            try {
                cliente.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
