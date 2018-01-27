

import javax.swing.*;
import java.awt.*;

public class UnloadingPlace extends JPanel {
        private JProgressBar unloadingProcces;
        private JLabel carIco;
        private JPanel workerPlace;
        private JPanel workerStats;
        private JLabel normalWorkerCount, specialWorketCount;

        public UnloadingPlace(){
            this.setSize(new Dimension(100, 200));
            super.setLayout(new BorderLayout());
            workerPlace = new JPanel(new BorderLayout());
            workerPlace.setSize(new Dimension(100, 80));
            JLabel workerTitle = new JLabel("Pracownicy");
            workerTitle.setFont(new Font("Serif", Font.BOLD, 16));
            workerPlace.add(workerTitle, BorderLayout.NORTH);
            workerStats = new JPanel(new GridLayout(2,2));
            workerStats.add(new JLabel("Normal"));
            workerStats.add(new JLabel("Wózek"));
            normalWorkerCount = new JLabel("2");
            specialWorketCount = new JLabel("3");
            workerStats.add(normalWorkerCount);
            workerStats.add(specialWorketCount);
            workerPlace.add(workerStats);
            this.add(workerPlace, BorderLayout.NORTH);
            carIco = new JLabel(IconPlaceManager.empty);
            this.add(carIco);
            unloadingProcces = new JProgressBar();
            setProgressBar((int)(Math.random()*100));
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
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    carIco.setIcon(IconPlaceManager.empty);
                }
            });
        }

        public void increaseVisitedWorker(WorkerType type){
            if(type == WorkerType.Human){
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        normalWorkerCount.setText(String.valueOf(Integer.parseInt(normalWorkerCount.getText())+1));
                    }
                });
            }else{
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        specialWorketCount.setText(String.valueOf(Integer.parseInt(specialWorketCount.getText())+1));
                    }
                });
            }

        }
}
