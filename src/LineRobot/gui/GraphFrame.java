package LineRobot.gui;

import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Dimension;

import LineRobot.MainActivity;
import LineRobot.gui.panels.GraphPanel;
import LineRobot.gui.panels.TracePanel;

import javax.swing.JFrame;

/**
 * Frame du graphique avec les robots.
 */
public class GraphFrame extends JFrame {
	
    public GraphFrame(MainActivity instance){

        // IMPORTS
        GraphPanel pnlGraph = new GraphPanel(instance) ;
        TracePanel pnlTrace = new TracePanel(instance) ;

        // INIT
        int widthFrame = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 1.2);
        int heightFrame = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.2);

        System.out.println(widthFrame);
        System.out.println(heightFrame);
        setLocation(widthFrame / 10, heightFrame / 10);
		setSize(widthFrame,heightFrame);
		setResizable(false);
		setTitle ("LineRobot");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setVisible(true);

        // EDIT PNL
        pnlGraph.setPreferredSize(new Dimension(widthFrame*3/4, heightFrame)); // pnl gauche
        pnlTrace.setPreferredSize(new Dimension(widthFrame*1/4, heightFrame)); // pnl droit

        System.out.println(widthFrame*3/4);
        System.out.println(widthFrame*1/4);

        // ADD
        add(pnlGraph, BorderLayout.CENTER);
        add(pnlTrace, BorderLayout.EAST);

    }
}
// Implémenter GraphPanel.java
