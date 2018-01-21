
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Car extends Thread {
    private Semaphore[] spaces;
    private CarType type;
    private WindowGUI window;
    public int value, id;
    private Queue<Car> toUnloading;
    public Semaphore stop = new Semaphore(0);

    public Car(int id, Semaphore[] spaces, WindowGUI window, Queue<Car> unloading, int t) {
        this.spaces = spaces;
        this.window = window;
        this.toUnloading = unloading;
        this.type = CarType.values()[t];
        this.value = type.getNumVal()*20;
        this.id = id;
        this.start();
    }
    public void run(){
        System.out.println("id: "+id+"||typ: "+type.name()+"||value: "+value+"|| Podjezdzam na plac");
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
            System.out.println("id: "+id+"||typ: "+type.name()+"||value: "+value+"|| Ustawiam się w kolejce na stanowisku "+x);
            spaces[x].acquire();
            toUnloading.add(this);
            System.out.println("id: "+id+"||typ: "+type.name()+"||value: "+value+"|| Czekam na rozładunek na "+x);
            stop.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
                System.out.println("id: "+id+"||typ: "+type.name()+"||value: "+value+"|| Jestem pusty, odjeżdżam z "+x);
            spaces[x].release();
        }
    }
}
