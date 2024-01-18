import greenfoot.*;

/* The entire class allows meteors to move by a decimal amount each time.
 * This allows for smoother and slower movement
 */

public abstract class SmoothMover extends Actor{
    private double exactX;
    private double exactY;

    public void setLocation(double x, double y) {
        exactX = x;
        exactY = y; 
        super.setLocation((int) (x + 0.5), (int) (y + 0.5));
    }

    public void setLocation(int x, int y) {
        exactX = x;
        exactY = y;
        super.setLocation(x, y);
    }

    public double getExactX() {
        return exactX;
    }

    public double getExactY() {
        return exactY;
    }
}
