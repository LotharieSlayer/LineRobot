package LineRobot.robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
            System.out.println("-----------------" + robot.getIdRobot() + "----------------------------");
            System.out.println(message);
            if(robot.getIdRobot() == -1 || robot.getIdRobot() != Integer.parseInt(args[0].split(":")[1])) {
                int idSender = Integer.parseInt(args[0].split(":")[1]);
                if(args[1].contains("nouveau") ) {
                    if(robot.getIdRobot() != -1) {
                        robot.sendMessage("to:"+ args[0].split(":")[1] +";num="+(robot.getTotalRobotCo()+1));
                    }
                }
                if(args[1].contains("coordonnee")){
                    robot.sendMessage("to:"+ args[0].split(":")[1] + ";Coord=X:"+robot.getX()+"|Y:"+robot.getY());
                    if(robot.getTotalRobotCo() >= 2) {
                        robot.sendMessage("droite");
                    }
                }
                if(args[1].contains("droite")) {
                    if(robot.isEloigner()){
                        robot.sendMessage("to:"+ args[0].split(":")[1] + ";eloigner=X:"+robot.getX()+"|Y:"+robot.getY());
                    }
                }
                if(args[1].startsWith("to:")){
                    if(robot.getIdRobot() == Integer.parseInt(args[1].split(":")[1])){
                        int idrecepteur = Integer.parseInt(args[1].split(":")[1]);
                        if(idSender != idrecepteur) {
                            if(args[2].startsWith("num=")){
                                if(robot.getIdRobot() == -1){
                                    robot.setNumRobot( Integer.parseInt( args[2].substring( "num=".length() ).trim() ) );
                                }
                            }
                            if(args[2].startsWith("Coord=")){
                                String[] coord = args[2].substring("Coord=".length()).split("\\|");
                                double x = Double.parseDouble(coord[0].split(":")[1]);
                                double y = Double.parseDouble(coord[1].split(":")[1]);
                                coords.put(Integer.parseInt(args[0].split(":")[1].trim()),new double[]{x,y});
                                nombreRecu++;
                                if(nombreRecu == robot.getTotalRobotCo()) {
                                    robot.calculDroite(coords);
                                    coords = new HashMap<>();
                                    nombreRecu = 0;
                                }
                            }
                            if(args[2].startsWith("eloigner=")){
                                String[] coord = args[2].substring("eloigner=".length()).split("\\|");
                                double x = Double.parseDouble(coord[0].split(":")[1]);
                                double y = Double.parseDouble(coord[1].split(":")[1]);
                                alcoords.add(new double[]{x,y});
                                nombreRecu++;
                                if(nombreRecu == 2) {
                                    robot.setCoordDroite(alcoords.get(0)[0], alcoords.get(0)[1], alcoords.get(1)[0], alcoords.get(1)[1]);
                                    nombreRecu = 0;
                                }
                            } 
                        }
                    }
                }
            }
        }
    }
}
