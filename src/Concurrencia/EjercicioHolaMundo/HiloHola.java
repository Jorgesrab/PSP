package Concurrencia.EjercicioHolaMundo;

public class HiloHola implements Runnable {
    private int num;
    private String mensaje;

    public HiloHola(int num) {
        this.mensaje = "Hola Mundo";
        this.num = num;

    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(num*1000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        Thread.currentThread().setName("Hilo " +num);
        System.out.println(mensaje+" de "+(Thread.currentThread().getName()));
    }
}
