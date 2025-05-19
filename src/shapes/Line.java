package shapes;

import java.util.List;
/**
 * class to define line by 2 points - start and end.
 */
public class Line {
    private Point start;
    private Point end;
    static final int ONE = 1;

    /**
     * constructor.
     * @param start the first point
     * @param end the end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     * @param x1 x rate of first point.
     * @param y1 y rate of first point.
     * @param x2 x rate of end point.
     * @param y2 y rate of end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
    }

    /**
     * get the start point of line.
     * @return the start point object.
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * get the end point of line.
     * @return the end point object.
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Return the length of the line.
     * @return the distance between the points.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * find the middle point of thr line.
     * @return the middle point of the line.
     */
    public Point middle() {
        double xMiddle = (this.end.getX() + this.start.getX()) / 2;
        double yMiddle = (this.end.getY() + this.start.getY()) / 2;
        Point middle = new Point(xMiddle, yMiddle);
        return middle;
    }

    /**
     * Returns the start point of the line.
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     * @return the end point
     */
    public Point end() {
        return this.end;
    }
    /**
     * function to find collision point with vertical line and line.
     * @param other the vertical line.
     * @return the point if exist, null if not.
     */
    public Point interVertical(Line other) {
        double xVal = other.start.getX();
        double min = Math.min(this.start.getX(), this.end.getX());
        double max = Math.max(this.start.getX(), this.end.getX());
        // if x of vertical in line range.
        if (xVal >= min && xVal <= max) {
            Function func = new Function(this.start, this.end);
            double yVal = func.getYValue(xVal);
            // if there is y colission.
            if (yVal <= other.getStart().getY() && yVal >= other.getEnd().getY()) {
                return new Point(xVal, yVal);
            }
        }
        return null;
    }

    /**
     * function to find collision point with horizon line and line.
     * @param other the horizon line.
     * @return the point if exist, null if not.
     */
    public Point interHorizon(Line other) {
        double min = Math.min(this.start.getY(), this.end.getY());
        double max = Math.max(this.start.getY(), this.end.getY());
        // if y of horizon in line range.
        if (other.getStart().getY() >= min && other.getStart().getY() <= max) {
            Function func = new Function(this.start, this.end);
            double xVal = func.getXValue(other.getStart().getY());
            // if there is x colission.
            if (xVal >= other.start.getX() && xVal <= other.end.getX()) {
                return new Point(xVal, other.start.getY());
            }
        }
        return null;
    }

    /**
     * check if 2 lines equals.
     * @param other the line to check with current line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        Function linear1 = new Function(this.start, this.end);
        Function linear2 = new Function(other.start, other.end);
        if (!linear1.equalFunction(linear2)) {
            return false;
        }
        if (this.end.getX() < other.start.getX() || this.start.getX() > other.end.getX()) {
            return false;
        }
        return true;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect the rectangle.
     * @return the closest point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        // check if there isn't colission points.
        if (points.isEmpty()) {
            return null;
        }
        // find the closest.
        Point closest = points.get(0);
        for (int i = ONE; i < points.size(); i++) {
            if (this.start.distance(closest) > this.start.distance(points.get(i))) {
                closest = points.get(i);
            }
        }
        return closest;
    }

    /**
     * check if there is intersect between lines.
     * @param other the line to check with.
     * @return true if there is, false if not.
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other) || this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * check with 2 lines if there is intersect.
     * @param other1 the first line to check.
     * @param other2 the second.
     * @return true if there is, false if not.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        if (intersectionWith(other1) != null && intersectionWith(other2) != null) {
            return true;
        }
        return false;
    }

    /**
     * if the lines intersect return the intersection point.
     * @param other the other line to check with.
     * @return the point if there is, null otherwise.
     */
    public Point intersectionWith(Line other) {
        Function funcToline = new Function(this.start, this.end);
        Function funcToOther = new Function(other.start, other.end);
        if (funcToline.equalFunction(funcToOther)) {
            return null;
        }
        double rateXIntersection = funcToline.getFuncIntersection(funcToOther);
        if (rateXIntersection < this.start.getX() || rateXIntersection > this.end.getX()
                || rateXIntersection < other.start.getX() || rateXIntersection > other.end.getX()) {
            return null;
        }
        return new Point(rateXIntersection, funcToline.getYValue(rateXIntersection));
    }
}
