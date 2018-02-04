
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
                synchronized (window.toUnloading){
                    UnloadingPlace currentPlace = window.toUnloading.get((int)(Math.random()*window.toUnloading.size()));
                    currentPlace.increaseVisitedWorker(type);
                }
            }catch (Exception e){}
            try {Thread.sleep(((int)(Math.random()*3000+1000))*2);} catch (InterruptedException ex) {} //czeka losowy czas
        }
    }
}
