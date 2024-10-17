package primeraPractica.ParImparSincro2;

import primeraPractica.HiloParEImpar.Impar;
import primeraPractica.HiloParEImpar.Par;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainParImpar {
    public static void main(String[] args) {

        Object lock = new Object();

        ParS2 hiloPar = new ParS2(lock);
        ImparS2 hiloImpar = new ImparS2(lock);


        hiloImpar.start();
        System.out.print("");
        hiloPar.start();




    }
}
