import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WindowGUI extends JFrame{
    private ConcurrentLinkedQueue<UnloadingPlace> placesList;

    public WindowGUI(String name){
        super(name);
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1,5));
        placesList = new ConcurrentLinkedQueue<UnloadingPlace>();
        for(int i=0; i<5;i++){
            UnloadingPlace x = new UnloadingPlace();
            top.add(x);
            placesList.add(x);
        }
        add(top, BorderLayout.NORTH);
    }
}
