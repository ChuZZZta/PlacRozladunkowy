
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker extends Thread
{
    private int capacity;
    private Queue<Car> toUnloading;
    
    public Worker(int type, Queue<Car> tu)
    {
        capacity = type * 5;
        this.start();
        toUnloading = tu;
    }
    
    public void run()
    {
        while(true)
        { 
            if(!toUnloading.isEmpty()){toUnloading.peek().value -= capacity;}
            try {Thread.sleep((int)(Math.random()*1000));} catch (InterruptedException ex) {}
        }
    }
}
