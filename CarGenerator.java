import java.util.Queue;
import java.util.concurrent.Semaphore;

public class CarGenerator extends Thread {
    private Semaphore[] spaces;
    private WindowGUI window;
    private Queue<Car> toUnloading;
    private int id = 0;

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
                Thread.sleep((int)(Math.random()*2500));
                new Car(id, spaces, window, toUnloading);
                id++;
            }catch (Exception e){

            }
        }

    }
}
