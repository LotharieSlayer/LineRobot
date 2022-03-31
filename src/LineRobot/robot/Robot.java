package LineRobot.robot;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Robot {

    private int idRobot;


    private double[] coordPoint1;
    private double[] coordPoint2;

    private Point pointDroit1;
    private Point pointDroit2;
    private Point millieuDroite;

    private double posx;
    private double posy;
    private double rot;

    private int nbRobotMax;

    private SocketR client;
    private ServerR serveur;
    private ThreadServeur thS;
    private ThreadRobot thR;

    private boolean isDemarer;

    public Robot() {
        isDemarer = true;
        this.client  = new SocketR();
        this.serveur = new ServerR();
        thS = new ThreadServeur(this, serveur);
        thS.start();
        nbRobotMax = 0;
        this.idRobot = (int) (Math.random()*50000) * -1;
        
        this.posx    = new BigDecimal((Math.random()*500)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.posy    = new BigDecimal((Math.random()*500)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.rot     = Math.random()*360;

        do {
            sendMessage("nouveau");	
		 	try { Thread.sleep(1000); } catch (InterruptedException e) {}
		} while (idRobot < 0);

        if(idRobot == 1) {
            pointDroit1 = new Point(new BigDecimal((Math.random()*500)).setScale(2, RoundingMode.HALF_UP).doubleValue(), new BigDecimal((Math.random()*500)).setScale(2, RoundingMode.HALF_UP).doubleValue());
            pointDroit2 = new Point(new BigDecimal((Math.random()*500)).setScale(2, RoundingMode.HALF_UP).doubleValue(), new BigDecimal((Math.random()*500)).setScale(2, RoundingMode.HALF_UP).doubleValue());
            double[] mid = new Calcul(pointDroit1.getX(), pointDroit2.getX(), pointDroit1.getY(), pointDroit2.getY()).calculPoints();
            millieuDroite = new Point(mid[0], mid[1]);
        }

        if(idRobot != 1){
            while (millieuDroite == null) {
                sendMessage("existe");
                try { Thread.sleep(1500); } catch (InterruptedException e) {}
            }
        }
        thR = new ThreadRobot(this);
        thR.run();
    }

    public void sendMessage(String message) {
        client.sendMessage("id:"+idRobot+";"+message);
    }


    public Point getMillieuDroite() {
        return millieuDroite;
    }

    public double[] getCoordPoint1() { return coordPoint1; }
    public double[] getCoordPoint2() { return coordPoint2; }
    public double getX      () { return this.posx   ; }
    public double getY      () { return this.posy   ; }
    public double getRot    () { return this.rot    ; }
    public int    getIdRobot() { return this.idRobot; }

    public int getTotalRobotCo() {
        return nbRobotMax;
    }

    public Boolean isDemarer(){
        return isDemarer;
    }

    public void setNumRobot(int numRobot) {
        if(numRobot > idRobot){
            idRobot = numRobot;
            nbRobotMax = numRobot;
        }
    }

    public void setNbRobotMax(int nbRobotMax) {
        this.nbRobotMax = nbRobotMax;
    }

    public void moveX(double d) {
        posx += d;
        posx = new BigDecimal(posx).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void moveY(double d) {
        posy += d;
        posy = new BigDecimal(posy).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public void stop() {
        isDemarer = false;
    }

    public void setCoordsmidDroite(double x, double y) {
        millieuDroite = new Point(x, y);
    }

    public void getPointDroite(double x1, double y1, double x2, double y2) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        this.pointDroit1 = p1;
        this.pointDroit2 = p2;
        int x = (int)((this.posy + this.posx * ((p2.getX() - p1.getX())/(p2.getY() - p1.getY())) - p1.getY() + p1.getX() * ((p2.getY() - p1.getY())/(p2.getX() - p1.getX()))) / ((p2.getY()-p1.getY())/(p2.getX()-p1.getX()) + (p2.getX()-p1.getX())/(p2.getY()-p1.getY())));
		this.millieuDroite = new Point(
			x,
			(int)(x * ((p2.getY()-p1.getY())/(p2.getX()-p1.getX())) + p1.getY() - p1.getX() * ((p2.getY()-p1.getY())/(p2.getX()-p1.getX()))) 
		);
    }

    public Point getPointDroite1() {
        return pointDroit1;
    }

    public Point getPointDroite2() {
        return pointDroit2;
    }
}
