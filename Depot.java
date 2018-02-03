import javax.swing.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Depot {
    private static Semaphore[] spaces;
    private static WindowGUI window;
    //private static Queue<Car> toUnloading;
    private static Worker[] workers;

    public static void main(String[] args){



        spaces = new Semaphore[3];
        spaces[0] = new Semaphore(1,true);
        spaces[1] = new Semaphore(3,true);
        spaces[2] = new Semaphore(1,true);
        //workers = new Worker[3];
        //toUnloading = new ConcurrentLinkedQueue<Car>();
        /*for(Worker w: workers)
        {
            w = new Worker(WorkerType.Human,toUnloading);
        }*/
        window = new WindowGUI("Plac");
        new CarGenerator(spaces, window);

        while(true)
        {
            //window.placesList.peek().increaseVisitedWorker(WorkerType.Human);

            try {Thread.sleep(1000);} catch (InterruptedException ex) {}
        }

    }

}
