package LineRobot.robot;

import java.text.DecimalFormat;

/**
 * Point
 */
public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        // this.x= x;
        // this.y = y;
        this.x = Math.round(x*100)/100;
        this.y = Math.round(y*100)/100;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}