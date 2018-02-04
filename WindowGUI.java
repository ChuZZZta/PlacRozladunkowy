import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WindowGUI extends JFrame{
    public LinkedList<UnloadingPlace> placesList;
    private JLabel carIco;
    private JLabel normalCount, busCount, tirCount;
    public LinkedList<UnloadingPlace> toUnloading;

    public WindowGUI(String name){
        super(name);
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toUnloading = new LinkedList<UnloadingPlace>();
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1,5));
        top.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        placesList = new LinkedList<UnloadingPlace>();
        for(int i=0; i<5;i++){
            UnloadingPlace x = new UnloadingPlace(toUnloading);
            x.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            top.add(x);
            placesList.add(x);
        }
        add(top, BorderLayout.NORTH);
        //kolejka
        JPanel quePanel = new JPanel(new BorderLayout());
        JPanel queLabel = new JPanel();
        queLabel.setLayout(new BoxLayout(queLabel, BoxLayout.X_AXIS ));
        JLabel labelText = new JLabel("Kolejka");
        labelText.setFont(new Font("Serif", Font.BOLD, 25));
        queLabel.add(Box.createHorizontalGlue());
        queLabel.add(labelText);
        queLabel.add(Box.createHorizontalGlue());
        JPanel quePosition = new JPanel( new GridLayout(1, 3));
        JPanel tmp = new JPanel(new BorderLayout());
        tmp.add(new JLabel(IconPlaceManager.normalCarIco), BorderLayout.CENTER);
        JPanel tmpCountFrame = new JPanel();
        tmpCountFrame.setLayout(new BoxLayout(tmpCountFrame, BoxLayout.X_AXIS ));
        normalCount = new JLabel("0");
        normalCount.setFont(new Font("Serif", Font.BOLD, 20));
        tmpCountFrame.add(Box.createHorizontalGlue());
        tmpCountFrame.add(normalCount);
        tmpCountFrame.add(Box.createHorizontalGlue());
        tmp.add(tmpCountFrame, BorderLayout.SOUTH);
        quePosition.add(tmp);
        tmp = new JPanel(new BorderLayout());
        tmp.add(new JLabel(IconPlaceManager.busIco), BorderLayout.CENTER);
        tmpCountFrame = new JPanel();
        tmpCountFrame.setLayout(new BoxLayout(tmpCountFrame, BoxLayout.X_AXIS ));
        busCount = new JLabel("0");
        busCount.setFont(new Font("Serif", Font.BOLD, 20));
        tmpCountFrame.add(Box.createHorizontalGlue());
        tmpCountFrame.add(busCount);
        tmpCountFrame.add(Box.createHorizontalGlue());
        tmp.add(tmpCountFrame, BorderLayout.SOUTH);
        quePosition.add(tmp);
        tmp = new JPanel(new BorderLayout());
        tmp.add(new JLabel(IconPlaceManager.tirIco), BorderLayout.CENTER);
        tmpCountFrame = new JPanel();
        tmpCountFrame.setLayout(new BoxLayout(tmpCountFrame, BoxLayout.X_AXIS ));
        tirCount = new JLabel("0");
        tirCount.setFont(new Font("Serif", Font.BOLD, 20));
        tmpCountFrame.add(Box.createHorizontalGlue());
        tmpCountFrame.add(tirCount);
        tmpCountFrame.add(Box.createHorizontalGlue());
        tmp.add(tmpCountFrame, BorderLayout.SOUTH);
        quePosition.add(tmp);
        quePanel.add(queLabel, BorderLayout.NORTH);
        quePanel.add(quePosition, BorderLayout.SOUTH);
        add(quePanel, BorderLayout.CENTER);

        //wjazd
        JPanel newCarEntracePanel = new JPanel(new BorderLayout());
        JPanel newCarEntraceLabel = new JPanel();
        newCarEntraceLabel.setLayout(new BoxLayout(newCarEntraceLabel, BoxLayout.X_AXIS ));
        JLabel label = new JLabel("Ostatnio wjechał");
        label.setFont(new Font("Serif", Font.BOLD, 25));
        newCarEntraceLabel.add(Box.createHorizontalGlue());
        newCarEntraceLabel.add(label);
        newCarEntraceLabel.add(Box.createHorizontalGlue());
        newCarEntracePanel.add(newCarEntraceLabel, BorderLayout.NORTH);
        carIco = new JLabel(IconPlaceManager.empty);
        newCarEntracePanel.add(carIco, BorderLayout.SOUTH);
        add(newCarEntracePanel, BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);
    }

    public void setNewCarIco(CarType type){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println(type);
                switch (type){
                        case Van:{
                            carIco.setIcon(IconPlaceManager.busIco);
                            return;
                        }
                        case Truck:{
                            carIco.setIcon(IconPlaceManager.tirIco);
                            return;
                        }
                        case Regular:{
                            carIco.setIcon(IconPlaceManager.normalCarIco);
                            return;
                        }
                        default:{
                            carIco.setIcon(IconPlaceManager.empty);
                        }
                }
            }
        });
    }

    public void increaseQueeCount(CarType type){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println(type);
                switch (type){
                    case Van:{
                        busCount.setText(String.valueOf(Integer.parseInt(busCount.getText())+1));
                        return;
                    }
                    case Truck:{
                        tirCount.setText(String.valueOf(Integer.parseInt(tirCount.getText())+1));
                        return;
                    }
                    case Regular:{
                        normalCount.setText(String.valueOf(Integer.parseInt(normalCount.getText())+1));
                        return;
                    }
                }
            }
        });
    }

    public void decreaseQueeCount(CarType type){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println(type);
                switch (type){
                    case Van:{
                        busCount.setText(String.valueOf(Integer.parseInt(busCount.getText())-1));
                        return;
                    }
                    case Truck:{
                        tirCount.setText(String.valueOf(Integer.parseInt(tirCount.getText())-1));
                        return;
                    }
                    case Regular:{
                        normalCount.setText(String.valueOf(Integer.parseInt(normalCount.getText())-1));
                        return;
                    }
                }
            }
        });
    }
}
