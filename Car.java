
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Car extends Thread {
    private Semaphore[] spaces;
    private CarType type;
    private WindowGUI window;
    private UnloadingPlace currentPlace = null;
    public int value, id;

    public Car(int id, Semaphore[] spaces, WindowGUI window, Queue<Car> unloading, int t) {
        this.spaces = spaces;
        this.window = window;
        this.type = CarType.values()[t];
        this.value = type.getNumVal()*20;
        this.id = id;
        this.start();
    }
    public void run(){
        //System.out.println("id: "+id+"||typ: "+type.name()+"||value: "+value+"|| Podjezdzam na plac");
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
            window.increaseQueeCount(type);
            spaces[x].acquire();
            window.decreaseQueeCount(type);
            //wybór odpowiedniego stanowiska
            switch(x){
                case 0:
                    this.currentPlace = window.placesList.get(0).initByNewCar(this);
                    break;
                case 1:
                    for(int i=1;i<4;i++){
                        if(window.placesList.get(i).isEmpty()){
                            this.currentPlace = window.placesList.get(i).initByNewCar(this);
                            break;
                        }
                    }
                    break;
                case 2:
                    this.currentPlace = window.placesList.get(4).initByNewCar(this);
                    break;
            }

            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.currentPlace.clearCarIco();
            spaces[x].release();
        }
    }

    public CarType getCarType(){
        return this.type;
    }

    public void setCurrentValue(int i){
        this.value = i;
    }
}
