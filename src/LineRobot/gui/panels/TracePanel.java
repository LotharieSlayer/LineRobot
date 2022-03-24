package LineRobot.gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import LineRobot.MainActivity;

public class TracePanel extends JPanel{

    private JLabel label;

    public TracePanel(MainActivity instance){
        // GridLayout grid = new GridLayout(1,2);
        label = new JLabel("pipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipipi");
        add(label);
    }
}
