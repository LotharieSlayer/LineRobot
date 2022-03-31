package LineRobot.robot;

public class ThreadRobot extends Thread {

    private Robot robot;

    public ThreadRobot(Robot robot) {
        this.robot = robot;
    }
    
    public void run() {
        while (robot.isDemarer()) {
            if (robot.getX() > robot.getMillieuDroite().getX()) {
                robot.moveX(-0.01);
            }
            if(robot.getX() < robot.getMillieuDroite().getX()) {
                robot.moveX(0.01);
            }

            if (robot.getY() > robot.getMillieuDroite().getY()) {
                robot.moveY(-0.01);
            }
            if(robot.getY() < robot.getMillieuDroite().getY()) {
                robot.moveY(0.01);
            }


            if(robot.getX() == robot.getMillieuDroite().getX() && robot.getY() == robot.getMillieuDroite().getY()) {
                robot.stop();   
            }
            System.out.println("----------------------------------------");
            System.out.println("robot " + robot.getIdRobot() + " = { x:" + robot.getX() + "; y:" + robot.getY());
            System.out.println("Droite point1 = { x:" + robot.getPointDroite1().getX() + "; y:" + robot.getPointDroite1().getY() );
            System.out.println("Droite point2 = { x:" + robot.getPointDroite2().getX() + "; y:" + robot.getPointDroite2().getY() );
            System.out.println("droite = { x:" + robot.getMillieuDroite().getX() + "; y:" + robot.getMillieuDroite().getY());
            System.out.println("----------------------------------------");
            try { Thread.sleep(20); } catch (InterruptedException e) {}
        }
    }
}
