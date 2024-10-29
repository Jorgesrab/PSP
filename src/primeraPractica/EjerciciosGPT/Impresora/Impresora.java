package primeraPractica.EjerciciosGPT.Impresora;

public class Impresora {
    Object lock = new Object();
    public Impresora(Object lock) {
        this.lock = lock;
    }

    public void imprimir(String Documento){
        synchronized (lock) {

            System.out.println(Documento);

        }

    }



}
