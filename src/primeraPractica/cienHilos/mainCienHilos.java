package primeraPractica.cienHilos;

public class mainCienHilos {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Hilo(i+1).start();
        }

    }
}
