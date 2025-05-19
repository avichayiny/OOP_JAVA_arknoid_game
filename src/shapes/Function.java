package shapes;

/**
 * class to define function that represent line, and find intersections.
 */
public class Function {
    private double slant;
    private double intersectionY;
    static final double EPSILON = 0.0000001;

    /**
     * constructor.
     * @param point1 the first point.
     * @param point2 the second point.
     */
    public Function(Point point1, Point point2) {
        if (Math.abs(point1.getX() - point2.getX()) < EPSILON) {
            this.intersectionY = 0;
            this.slant = Double.POSITIVE_INFINITY;
            return;
        }
        this.slant = ((point2.getY() - point1.getY()) / (point2.getX() - point1.getX()));
        this.intersectionY = ((point1.getY() - this.slant * point1.getX()));
    }

    /**
     * constructor.
     * @param slant the function slant.
     * @param intersectionY the function intersection with y axes.
     */
    public Function(double slant, double intersectionY) {
        this.slant = slant;
        this.intersectionY = intersectionY;
    }

    /**
     * get linear equation slant.
     * @return the slant.
     */
    public double getSlant() {
        return this.slant;
    }

    /**
     * get the intersection point with y axis.
     * @return the y value in the intersection point.
     */
    public double getIntersectionY() {
        return this.intersectionY;
    }

    /**
     * receive y rate and return the x rate in linear equation.
     * @param yRate the y value.
     * @return the x value.
     */
    public double getXValue(double yRate) {
        return (yRate - this.intersectionY) / slant;
    }

    /**
     * receive x rate and return the y rate in linear equation.
     * @param xRate the x value.
     * @return the y value.
     */
    public double getYValue(double xRate) {
        return (this.slant * xRate) + this.intersectionY;
    }

    /**
     * check an intersection point between 2 linear equation.
     * @param other the linear equation to check.
     * @return the x value of the intersection.
     */
    public double getFuncIntersection(Function other) {
        return (this.intersectionY - other.getIntersectionY()) / (other.getSlant() - this.slant);
    }
    /**
     * function to compare double numbers.
     * @param num1 the first number.
     * @param num2 the second number.
     * @return true if there equals, else if not.
     */
    public boolean compareDouble(double num1, double num2) {
        if (Math.abs(num1 - num2) < EPSILON) {
            return true;
        }
        return false;
    }
    /**
     * check if to 2 linear equation are the same 1.
     * @param toCompare the equation to compare.
     * @return true if there is, false if not.
     */
    public boolean equalFunction(Function toCompare) {
        if (this.compareDouble(this.slant, toCompare.slant)
                && this.compareDouble(this.intersectionY, toCompare.intersectionY)) {
            return true;
        }
        return false;
    }
}
