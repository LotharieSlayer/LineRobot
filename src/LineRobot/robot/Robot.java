package LineRobot.robot;

import java.util.HashMap;
import java.util.Map.Entry;

public class Robot {

    private int idRobot;
    private double[] coordPoint1;
    private double[] coordPoint2;

    private double posx;
    private double posy;
    private double rot;

    private int nbRobotMax;

    private double pointFinalx;
    private double pointFinaly;

    private SocketR client;
    private ServerR serveur;
    private ThreadServeur thS;

    private boolean eloigner;
    private boolean bloquer;

    public Robot() {
        eloigner = false;
        bloquer = false;
        this.client  = new SocketR();
        this.serveur = new ServerR();
        thS = new ThreadServeur(this, serveur);
        thS.start();
        nbRobotMax = 0;
        this.idRobot = -1;
        this.posx    = Math.random()*500;
        this.posy    = Math.random()*500;
        this.rot     = Math.random()*360;

       sendMessage("nouveau");

       try { Thread.sleep(100); } catch (InterruptedException e) {}

       sendMessage("coordonnee");

       try { Thread.sleep(1000); } catch (InterruptedException e) {}
       
       while (nbRobotMax < 2) {}

       sendMessage("droite");

       try { Thread.sleep(100); } catch (InterruptedException e) {}

       double[] resultat = new Calcul(coordPoint1[0], coordPoint2[0], coordPoint1[1], coordPoint2[1]).calculPoints();
       pointFinalx = resultat[0];
       pointFinaly = resultat[1];
    }

    public void sendMessage(String message) {
        client.sendMessage("id:"+idRobot+";"+message);
    }

    public double getX      () { return this.posx   ; }
    public double getY      () { return this.posy   ; }
    public double getRot    () { return this.rot    ; }
    public int    getIdRobot() { return this.idRobot; }

    public boolean isEloigner() { return this.eloigner; }

    public double[] getCoordPoint1() { return coordPoint1; }
    public double[] getCoordPoint2() { return coordPoint2; }


    public int getTotalRobotCo() {
        return nbRobotMax;
    }

    public void setNumRobot(int numRobot) {
        if(numRobot > idRobot){
            idRobot = numRobot;
            nbRobotMax = numRobot;
        }
    }

    public double calculDroite(HashMap<Integer, double[]> coords) {
        HashMap<Integer, double[]> coord2 = (HashMap<Integer, double[]>) coords.clone();
        double distMax = 0;
        for ( Entry<Integer, double[]> entry1 : coords.entrySet()) {
            for (Entry<Integer, double[]> entry2 : coord2.entrySet()) {
                double distance = distance(entry1.getValue()[0], entry1.getValue()[1], entry2.getValue()[0], entry2.getValue()[1]);
                if(distMax < distance){
                    distMax = distance;
                    if(entry1.getKey() == idRobot || entry2.getKey() == idRobot) {
                        eloigner = true;
                        bloquer = true;
                    }
                }
            }
            coord2.remove(entry1.getKey());
        }
        return distMax;
    }

    public static double distance(double x1, double y1, double x2, double y2){
        double x = Math.abs(x1-x2);
        double y = Math.abs(y1-y2);
        double res = Math.pow(x,2)+Math.pow(y,2);
        res = Math.sqrt(res);
        return res;
    }

    public void setCoordDroite(double x1, double y1, double x2, double y2) {
        coordPoint1 = new double[]{x1,y1};
        coordPoint2 = new double[]{x2,y2};
    }
}
