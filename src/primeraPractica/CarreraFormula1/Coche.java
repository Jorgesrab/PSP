package primeraPractica.CarreraFormula1;

import java.awt.*;
import java.util.List;

public class Coche extends Thread{
    private String piloto;
    private List<String> posicionesPilotos;


    public Coche( String piloto, List<String>PosicionesPilotos ) {
        this.piloto = piloto;
        this.posicionesPilotos = PosicionesPilotos;

    }


    @Override
    public void run() {


        for (int vuelta = 0; vuelta <= 78; vuelta++) {
            System.out.println(piloto +" ha completado la vuelta "+vuelta);
        }

        System.out.println(piloto+ "ha terminado la carreara");
        posicionesPilotos.add(piloto);
    }

}


