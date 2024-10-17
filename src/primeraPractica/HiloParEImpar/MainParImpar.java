package primeraPractica.HiloParEImpar;

public class MainParImpar {
    public static void main(String[] args) {
        Par hiloPar = new Par();
        Impar hiloImpar = new Impar();
        hiloPar.start();
        hiloImpar.start();


    }
}
