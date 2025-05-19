package shapes;

/**
 * class to define points.
 */
public class Point {
    private double x;
    private double y;
    static final double EPSILON = 0.0000001;
    /**
     * constructor.
     * @param x the x rate.
     * @param y the y rate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     * @param other the other point.
     * @return the distance.
     */
    public double distance(Point other) {
        // the points differences.
        double xDef = this.x - other.getX();
        double yDef = this.y - other.getY();
        // the differences squared.
        xDef = xDef * xDef;
        yDef = yDef * yDef;
        // The root of the difference connection.
        return Math.sqrt(xDef + yDef);
    }

    /**
     * return true is the points are equal, false otherwise.
     * @param other the point to equal.
     * @return true if equal, false if not.
     */
    public boolean equals(Point other) {
        if (Math.abs(this.x - other.getX()) < EPSILON && Math.abs(this.y - other.getY()) < EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * return the x value of the point.
     * @return x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * return the y value of the point.
     * @return y value.
     */
    public double getY() {
        return this.y;
    }
}