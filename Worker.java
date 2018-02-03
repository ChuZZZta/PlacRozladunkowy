
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker extends Thread
{
    private WorkerType type;
    private WindowGUI window;
    
    public Worker(WorkerType type, WindowGUI window)
    {
        this.type = type;
        this.window = window;
        this.start();
    }
    
    public void run()
    {
        while(true)
        {
            try{
                UnloadingPlace currentPlace = window.toUnloading.peek();
                currentPlace.increaseVisitedWorker(type);
            }catch (Exception e){}
            try {Thread.sleep(((int)(Math.random()*3000+1000))*2);} catch (InterruptedException ex) {} //czeka losowy czas
        }
    }
}
