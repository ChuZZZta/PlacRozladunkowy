import java.util.Queue;
import java.util.concurrent.Semaphore;

public class CarGenerator extends Thread {
    private Semaphore[] spaces;
    private WindowGUI window;
    private Queue<Car> toUnloading;

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
                Thread.sleep((int)(Math.random()*5000));
                new Car(spaces, window, toUnloading);
            }catch (Exception e){

            }
        }

    }
}
