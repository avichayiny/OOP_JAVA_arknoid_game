package shapes;

import java.util.ArrayList;
/**
 * class to define rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper-left point of rectangle.
     * @param width the width of rectangle.
     * @param height the height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * return the upper side of the rectangle.
     * @return line that is the upper.
     */
    public Line above() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        return new Line(this.upperLeft, upperRight);
    }

    /**
     * return the under side of the rectangle.
     * @return line that is the under.
     */
    public Line under() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downRight = new Point(upperRight.getX(), downLeft.getY());
        return new Line(downLeft, downRight);
    }

    /**
     * return the right side of the rectangle.
     * @return line that is the right.
     */
    public Line right() {
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(downRight, upperRight);
    }

    /**
     * return the left side of the rectangle.
     * @return line that is the left.
     */
    public Line left() {
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(downLeft, this.upperLeft);
    }


    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line the line to check.
     * @return list of points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line above = this.above();
        Line under = this.under();
        Line left = this.left();
        Line right = this.right();
        java.util.List<Point> points = new ArrayList<>();
        // if hits the left.
        if (line.interVertical(left) != null) {
            points.add(line.interVertical(left));
        }
        // if hits the right.
        if (line.interVertical(right) != null) {
            points.add(line.interVertical(right));
        }
        // if hits above.
        if (line.interHorizon(above) != null) {
            points.add(line.interHorizon(above));
        }
        // if hits the bottom.
        if (line.interHorizon(under) != null) {
            points.add(line.interHorizon(under));
        }
        return points;
    }

    /**
     * Return the width of the rectangle.
     * @return the width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     * @return the height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * @return the upper-left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}