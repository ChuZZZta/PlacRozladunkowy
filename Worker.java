
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
        toUnloading = tu;
        this.start();
    }
    
    public void run()
    {
        while(true)
        { 
            if(!toUnloading.isEmpty())
            {
                if(toUnloading.peek().value<=0)
                {
                    toUnloading.peek().stop.release();
                    toUnloading.poll();
                }
                else
                {
                    toUnloading.peek().value -= capacity;
                    System.out.println("Rozładowuje "+toUnloading.peek().id+"||Zostało: "+toUnloading.peek().value);
                }
            }
            try {Thread.sleep((int)(Math.random()*1000));} catch (InterruptedException ex) {}
        }
    }
}
