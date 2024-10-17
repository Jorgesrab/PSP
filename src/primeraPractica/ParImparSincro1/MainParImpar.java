package primeraPractica.ParImparSincro1;

import primeraPractica.HiloParEImpar.Impar;
import primeraPractica.HiloParEImpar.Par;

public class MainParImpar {
    public static void main(String[] args) {
        primeraPractica.HiloParEImpar.Par hiloPar = new Par();
        primeraPractica.HiloParEImpar.Impar hiloImpar = new Impar();
        hiloPar.start();
        hiloImpar.start();


    }
}
