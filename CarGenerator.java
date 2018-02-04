import java.util.concurrent.Semaphore;

public class CarGenerator extends Thread {
    private Semaphore[] spaces;
    private WindowGUI window;
    private int typ,random;

    public CarGenerator(Semaphore[] spaces, WindowGUI window) {
        this.spaces = spaces;
        this.window = window;
        this.setDaemon(true);
        this.start();
    }

    public void run(){
        while(true){
            try {
                random = (int)(Math.random()*101);
                if (random<30) {typ = 2;}
                else if (random>85) {typ = 0;}
                else typ = 1;
                window.setNewCarIco(CarType.values()[typ]);
                new Car(spaces, window, typ);
                Thread.sleep((int)(Math.random()*4500+1000));
            }catch (Exception e){

            }
        }

    }
}
