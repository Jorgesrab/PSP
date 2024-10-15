package primeraPractica.HiloParEImpar;

public class MainPrImpar {
    public static void main(String[] args) {
        Par hiloPar = new Par();
        Impar hiloImpar = new Impar();
        hiloPar.start();
        hiloImpar.start();


    }
}
