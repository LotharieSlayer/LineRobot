package LineRobot;

import java.util.ArrayList;
import LineRobot.robot.ThreadR;


public class Robot {
    private ThreadR thread;

    public Robot (ThreadR Thre){
        this.thread = Thre;
    }

    public static void main (String[] args){
        ThreadR t1, t2, t3, t4;
        t1 = new ThreadR();
        t2 = new ThreadR();
        t3 = new ThreadR();
        t4 = new ThreadR();
        ArrayList<ThreadR> alRobots = new ArrayList<ThreadR>();
        alRobots.add(t1);
        alRobots.add(t2);
        alRobots.add(t3);
        alRobots.add(t4);

        System.out.println(ThreadR.droitePrincipale(alRobots) + " distance entre " + ThreadR.rBougePas[0].getX() + " et " + ThreadR.rBougePas[1].getX());
        System.out.println(730%360);
    }
}

// Robot lui même qui inclus le serveur, socket et son thread.