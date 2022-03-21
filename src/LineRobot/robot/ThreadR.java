package LineRobot.robot;

import java.util.ArrayList;

import LineRobot.Robot;

/**
 * Chaque Thread de robot qui permettra au robot d'exister.
 */
public class ThreadR extends Thread{
    private double posx;
    private double posy;
    public static ThreadR[] rBougePas = new ThreadR[2];

    public ThreadR(double x, double y){
        this.posx = x;
        this.posy = y;
    }

    public double getX(){ return this.posx; }
    public double getY(){ return this.posy; }

    public static double droitePrincipale(ArrayList<ThreadR> listeRobot){
        ArrayList<ThreadR> liste2 = new ArrayList<ThreadR>();
        double distMax = 0;
        for(ThreadR r:listeRobot){
            liste2.add(r);
        }
        for(ThreadR r1:listeRobot){
            for(ThreadR r2:liste2){
                if(distMax < distance(r1, r2)){
                    distMax = distance(r1, r2);
                    rBougePas[0] = r1;
                    rBougePas[1] = r2;
                }
            }
            liste2.remove(r1);
        }
        return distMax;
    }

    public static double distance(ThreadR r1, ThreadR r2){
        double x = Math.abs(r1.getX()-r2.getX());
        double y = Math.abs(r1.getY()-r2.getY());
        double res = Math.pow(x,2)+Math.pow(y,2);
        res = Math.sqrt(res);
        return res;
    }

    public void run(){

    }

    
}
