
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Car extends Thread {
    private Semaphore[] spaces;
    private CarType type;
    private WindowGUI window;
    private int value;
    private Queue<Car> toUnloading;

    public Car(Semaphore[] spaces, WindowGUI window, Queue<Car> unloading) {
        this.spaces = spaces;
        this.window = window;
        this.toUnloading = unloading;
        this.type = CarType.values()[(int)(Math.random()*3)];
        this.value = type.getNumVal()*20;
        this.start();
    }
    public void run(){
        //
        System.out.println("nowy car "+type.name());
        switch (type){
            case Truck:
                unloadProcess(2);
                break;
            case Van:
                if(spaces[1].getQueueLength() <= spaces[2].getQueueLength()){
                    unloadProcess(1);
                }else{
                    unloadProcess(2);
                }
                break;
            case Regular:
                if(spaces[0].getQueueLength() <= spaces[1].getQueueLength() && spaces[0].getQueueLength() <= spaces[2].getQueueLength()){
                    unloadProcess(0);
                } else if(spaces[1].getQueueLength() <= spaces[0].getQueueLength() && spaces[1].getQueueLength() <= spaces[2].getQueueLength()){
                    unloadProcess(1);
                }else{
                    unloadProcess(2);
                }

        }

    }

    private void unloadProcess(int x){
        try {
            spaces[x].acquire();
            //zajmuje miejsce
            toUnloading.add(this);
            while(value>0){

            }
            //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            toUnloading.remove(this);
            spaces[x].release();
        }
    }
}
