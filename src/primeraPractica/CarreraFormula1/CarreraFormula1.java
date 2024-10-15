package primeraPractica.CarreraFormula1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CarreraFormula1 {
    public static void main(String[] args) {

        String[] nombresPilotos = {
                "Hamilton", "Vettel", "Raikkonen", "Alonso", "Sainz Jr", "Bottas", "Vandoorne"
        };


        List<String> posicionesPilotos = new ArrayList<>();
        for (String nombre : nombresPilotos) {
            new Coche(nombre, posicionesPilotos).start();

        }


       try {
           Thread.sleep(3000);
       }catch (InterruptedException e){
           e.printStackTrace();
       }

        System.out.println("\nPosiciones finales de la carrera:");
        for (int i = 0; i < posicionesPilotos.size(); i++) {
            System.out.println((i + 1) + ". " + posicionesPilotos.get(i));
        }


    }
}
