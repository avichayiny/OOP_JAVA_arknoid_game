package shapes;

/**
 * class Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * constructor.
     * @param dx the change in x axes.
     * @param dy the change in y axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * get the dx velocity.
     * @return the dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * get the dy velocity.
     * @return the dy value.
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p the current point to add its value the direction.
     * @return the new point with updated values.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * function that create velocity by speed and angle.
     * @param angle the angle of the direction.
     * @param speed the speed to advance.
     * @return the new velcity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRadians = Math.toRadians(angle);
        double dx = speed * Math.sin(angleInRadians);
        double dy = -speed * Math.cos(angleInRadians);
        return new Velocity(dx, dy);
    }

}
