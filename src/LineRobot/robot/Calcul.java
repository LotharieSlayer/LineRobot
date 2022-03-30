package LineRobot.robot;

public class Calcul {
	
	private final int NB_POINTS = 4;
	private double[] pointsDroite;

	private double xPoint;
	private double yPoint;

	// (y2-y1)/(x2-x1) perpendiculaire de la droite entre les deux extremitÃ©s
	public Calcul(double x1, double x2, double y1, double y2) {
		pointsDroite = new double[NB_POINTS];

		pointsDroite[0] = x1;
		pointsDroite[1] = x2;
		pointsDroite[2] = y1;
		pointsDroite[3] = y2;
	}

	public double[] calculPoints() {
		
		if (pointsDroite[0] > pointsDroite[1]) {
			xPoint = (pointsDroite[0] + pointsDroite[1])/2;
		} else { 
			if (pointsDroite[0] < pointsDroite[1]) {
				xPoint = (pointsDroite[1] + pointsDroite[0])/2;
			}
		}
		
		if (pointsDroite[2] > pointsDroite[3]) {
			yPoint = (pointsDroite[2] + pointsDroite[3])/2;
		} else {
			if (pointsDroite[2] < pointsDroite[3] ) {
				yPoint = (pointsDroite[3] + pointsDroite[2])/2;
			}
		}
		
		return new double[]{xPoint,yPoint};
	}
}