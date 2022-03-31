package LineRobot.robot;

import java.util.ArrayList;
import java.util.HashMap;

public class ThreadServeur extends Thread{
    
    private ServerR serveur;
    private Robot robot;
    private int nombreRecu;
    private HashMap<Integer, double[]> coords;
    private ArrayList<double[]> alcoords;

    public ThreadServeur(Robot robot, ServerR serverR) {
        this.robot = robot;
        this.serveur = serverR;
        this.nombreRecu = 0;
        this.coords = new HashMap<>();
        this.alcoords = new ArrayList<>();
    }

    public void run() {
        while (true) {
            String message = serveur.getMessage();
            String[] args = message.split(";");

            System.out.println();
            System.out.println("-----------------(" + robot.getIdRobot() + ")----------------------------");
            System.out.println(message);

			if(args[1].contains("coordonnee")){
				robot.sendMessage("to:"+ args[0].split(":")[1] + ";Coord=X:"+robot.getX()+"|Y:"+robot.getY());
			}

            if(args[1].contains("existe")) {
                if(robot.getIdRobot() == 1 && robot.getMillieuDroite() != null)
                robot.sendMessage("to:"+ args[0].split(":")[1] +";droite;X1:" + robot.getPointDroite1().getX() + ";Y1:" + robot.getPointDroite1().getX() + ";X2:" + robot.getPointDroite2().getX() + ";Y2:" + robot.getPointDroite2().getY());
            }


            if(robot.getIdRobot() != Integer.parseInt(args[0].split(":")[1])) {

                int idSender = Integer.parseInt(args[0].split(":")[1]);

                if(args[1].contains("nouveau") ) {
                    robot.sendMessage("to:"+ args[0].split(":")[1] +";num="+(robot.getTotalRobotCo()+1));
                }

                if(args[1].contains("maxR")) {
                    robot.setNbRobotMax(Integer.parseInt(args[1].split(":")[1].trim()));
                    try { Thread.sleep(1500); } catch (InterruptedException e) {}
                }


                if(args[1].startsWith("to:")){
                    if(robot.getIdRobot() == Integer.parseInt(args[1].split(":")[1])){
                        int idrecepteur = Integer.parseInt(args[1].split(":")[1]);
                        if(idSender != idrecepteur) {
                            if(args[2].startsWith("num=")){
                                if(robot.getIdRobot() < 0){
                                    robot.setNumRobot( Integer.parseInt( args[2].substring( "num=".length() ).trim() ) );
                                    robot.sendMessage("maxR:" + robot.getIdRobot());
                                }
                            }
                            if(args[2].startsWith("droite")){
                                System.out.println("aa");
                                double x1 = Double.parseDouble(args[3].split(":")[1]);
                                double y1 = Double.parseDouble(args[4].split(":")[1]);
                                double x2 = Double.parseDouble(args[5].split(":")[1]);
                                double y2 = Double.parseDouble(args[6].split(":")[1]);
                                robot.getPointDroite(x1, y1, x2, y2);
                            }
                        }
                    }
                }
            }
        }
    }
}
