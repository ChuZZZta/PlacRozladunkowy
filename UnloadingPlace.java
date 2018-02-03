

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class UnloadingPlace extends JPanel {
        private Car currentCar = null;
        private JProgressBar unloadingProcces;
        private JLabel carIco;
        private JPanel workerPlace;
        private JPanel workerStats;
        private int carMaxCount;
        private Queue<UnloadingPlace> toUnloading;
        private JLabel normalWorkerCount, specialWorketCount;

        public UnloadingPlace(Queue<UnloadingPlace> toUnloading){
            this.toUnloading = toUnloading;
            this.setSize(new Dimension(100, 200));
            super.setLayout(new BorderLayout());
            workerPlace = new JPanel(new BorderLayout());
            workerPlace.setSize(new Dimension(100, 80));
            JLabel workerTitle = new JLabel("Status rozładunku");
            workerTitle.setFont(new Font("Serif", Font.BOLD, 16));
            workerPlace.add(workerTitle, BorderLayout.NORTH);
            workerStats = new JPanel(new GridLayout(2,2));
            workerStats.add(new JLabel("Normal"));
            workerStats.add(new JLabel("Wózek"));
            normalWorkerCount = new JLabel("0");
            specialWorketCount = new JLabel("0");
            workerStats.add(normalWorkerCount);
            workerStats.add(specialWorketCount);
            workerPlace.add(workerStats);
            this.add(workerPlace, BorderLayout.NORTH);
            carIco = new JLabel(IconPlaceManager.empty);
            this.add(carIco);
            unloadingProcces = new JProgressBar();
            setProgressBar(0);
            this.add(unloadingProcces, BorderLayout.SOUTH);
        }


        public void setProgressBar(int i){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    unloadingProcces.setValue(i);
                }
            });
        }

        public void clearCarIco(){
            this.toUnloading.remove(this);
            this.currentCar = null;
            this.setProgressBar(0);
            this.specialWorketCount.setText("0");
            this.normalWorkerCount.setText("0");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    carIco.setIcon(IconPlaceManager.empty);
                }
            });
        }

        public void setCarIco(CarType type){
            switch (type){
                case Truck:
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            carIco.setIcon(IconPlaceManager.tirIco);
                        }
                    });
                    break;
                case Van:
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            carIco.setIcon(IconPlaceManager.busIco);
                        }
                    });
                    break;
                case Regular:
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            carIco.setIcon(IconPlaceManager.normalCarIco);
                        }
                    });
                    break;
            }
        }

        public void increaseVisitedWorker(WorkerType type){
            if(type == WorkerType.Human){
                currentCar.decreaseCurrentValueBy(1);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        normalWorkerCount.setText(String.valueOf(Integer.parseInt(normalWorkerCount.getText())+1));
                    }
                });
            }else{
                currentCar.decreaseCurrentValueBy(3);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        specialWorketCount.setText(String.valueOf(Integer.parseInt(specialWorketCount.getText())+1));
                    }
                });
            }
            setProgressBar((int)((currentCar.getCurrentValue()/(double)carMaxCount)*100));
            if(currentCar.getCurrentValue()<1){
                currentCar.allowLeave();
            }
        }

    public UnloadingPlace initByNewCar(Car car) {
            currentCar = car;
            toUnloading.add(this);
            setCarIco(car.getCarType());
            setProgressBar(100);
            carMaxCount = car.getCarType().getNumVal()*20;
            return this;
    }

    public boolean isEmpty(){
            return this.currentCar == null;
    }
}
