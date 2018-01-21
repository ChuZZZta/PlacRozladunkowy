import java.util.Queue;
import java.util.concurrent.Semaphore;

public class CarGenerator extends Thread {
    private Semaphore[] spaces;
    private WindowGUI window;
    private Queue<Car> toUnloading;
    private int id = 0,typ,random;

    public CarGenerator(Semaphore[] spaces, WindowGUI window, Queue<Car> unloading) {
        this.spaces = spaces;
        this.window = window;
        this.toUnloading = unloading;
        this.setDaemon(true);
        this.start();
    }

    public void run(){
        while(true){
            try {
                
                random = (int)(Math.random()*101);
                if (random<45) {typ = 2;}             //losowanie jaki pojazd 45% szans regular, 35% van, 20% truck
                else if (random>80) {typ = 0;}
                else typ = 1;
                Thread.sleep((int)(Math.random()*1000));
                new Car(id, spaces, window, toUnloading,typ);
                id++;
            }catch (Exception e){

            }
        }

    }
}
