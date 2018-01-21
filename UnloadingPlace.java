

import javax.swing.*;
import java.awt.*;

public class UnloadingPlace extends JPanel {
        private JProgressBar unloadingProcces;
        private JLabel carIco;

        public UnloadingPlace(){
            this.setSize(new Dimension(100, 200));
            super.setLayout(new GridLayout(3,1));
            this.add(new JTextArea("Pracownicy"));
            carIco = new JLabel(IconPlaceManager.normalCarIco);
            this.add(carIco);
            unloadingProcces = new JProgressBar();
            setProgressBar((int)(Math.random()*100));
            this.add(unloadingProcces);
            clearCarIco();
        }


        public void setProgressBar(int i){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    unloadingProcces.setValue(i);
                    unloadingProcces.repaint();
                }
            });
        }

        public void clearCarIco(){

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    carIco.setIcon(IconPlaceManager.empty);
                    carIco.repaint();
                }
            });

        }
}
