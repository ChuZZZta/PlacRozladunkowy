
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
            if(!toUnloading.isEmpty()) //jesli jest cos w kolejce do rozladunku to worker zacznie pracowac
            {
                if(toUnloading.peek().value<=0)//jesli sie zdarzylo tak ze pierwszy samochod jest juz rozladowany to go puszcza
                {
                    toUnloading.peek().stop.release();
                    toUnloading.poll();
                }
                else // jesli jednak cos dalej jest zaladowane
                {
                    if(toUnloading.peek().value<capacity) // jesli udalo sie zabrac wszytsko to go puszcza
                    {
                        toUnloading.peek().value=0;
                        toUnloading.peek().stop.release();
                        toUnloading.poll();
                    }
                    else toUnloading.peek().value -= capacity; // jesli dalej cos zostalo to wezmie co moze i samochod daej czeka
                    System.out.println("Rozładowuje "+toUnloading.peek().id+"||Zostało: "+toUnloading.peek().value);
                }
            }
            try {Thread.sleep((int)(Math.random()*1500));} catch (InterruptedException ex) {} //czeka losowy czas
        }
    }
}
