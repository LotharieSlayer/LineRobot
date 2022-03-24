package LineRobot.gui.panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import LineRobot.MainActivity;

public class GraphPanel extends JPanel{

    private JLabel label;


    public GraphPanel(MainActivity instance){
        label = new JLabel("caca");
        add(label);


    }

    public void paint(Graphics g){
    
        g.drawLine(10, 10, 1160, 840);
        g.setColor(new Color(255,105,180));
        g.fillOval(10, 10, 10, 10);
        g.fillOval(50, 60, 10, 10);
        g.fillOval(200, 100, 10, 10);
        g.fillOval(300, 400, 10, 10);
        g.fillOval(1170, 850, 10, 10);
        
    }

}
