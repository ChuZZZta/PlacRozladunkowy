import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Depot {
    private static Semaphore[] spaces;
    private static WindowGUI window;
    private static Queue<Car> toUnloading;
    private static Worker[] workers;

    public static void main(String[] args){
        spaces = new Semaphore[3];
        spaces[0] = new Semaphore(5, true);
        spaces[1] = new Semaphore(3, true);
        spaces[2] = new Semaphore(2, true);
        workers = new Worker[5];
        toUnloading = new ConcurrentLinkedQueue<Car>();
        for(Worker w: workers)
        {
            w = new Worker((int)(Math.random()*2+1),toUnloading);
        }
        new CarGenerator(spaces, window,toUnloading);

        while(true){}

    }

}
