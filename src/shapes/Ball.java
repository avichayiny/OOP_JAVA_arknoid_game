package shapes;

import gamesetup.CollisionInfo;
import gamesetup.Game;
import gamesetup.GameEnvironment;
import interfaces.Sprite;
import biuoop.DrawSurface;
/**
 * class to define and create balls.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;
    static final double BIG = 1000;

    /**
     * constructor.
     * @param center the center point of the ball circle.
     * @param r the circle radius.
     * @param color the ball color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor.
     * @param x the x value of the center point of the ball circle.
     * @param y the y value of the center point of the ball circle.
     * @param r the circle radius.
     * @param color the ball color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * set color to ball.
     * @param color the color to set.
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * move the ball one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * adding ball to sprites list of game.
     * @param game the game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * function to get center of ball.
     * @return center point.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * get the x value of the center of ball point.
     * @return the x value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get the y value of the center of ball point.
     * @return the y value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * get the radius of the ball center.
     * @return the radius.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * get the ball color.
     * @return the ball color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * get the game environment of ball.
     * @return the game.
     */
    public GameEnvironment getGame() {
        return this.game;
    }

    /**
     * setting game to ball.
     * @param game the game environment to set to the ball.
     */
    public void setGame(GameEnvironment game) {
        this.game = game;
    }
    /**
     * draw the ball on the given DrawSurface.
     * @param surface the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * set the ball velocity.
     * @param v the velocity object to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the ball velocity.
     * @param dx the dx of the velocity to set.
     * @param dy the dy of the velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * get the ball velocity.
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * starting a new game environment by creating an object.
     */
    public void startGame() {
        game = new GameEnvironment();
    }

    /**
     * function that return the line that the ball will move without any obstacles.
     * @return the line.
     */
    public Line getTrajectory() {
        Point keep = new Point(center.getX() + velocity.getDx(), this.center.getY() + this.velocity.getDy());
        return new Line(this.center, keep);
    }

    /**
     * function to move small step until the collision.
     * @param collision the collision point.
     */
    public void smallStep(Point collision) {
        // smaller velocity.
        double dx = this.velocity.getDx() / BIG;
        double dy = this.velocity.getDy() / BIG;
        Point current;
        // do small steps until the ball closest to the collision object.
        while (this.center.distance(collision) > this.r) {
            current = new Point(this.center.getX() + dx, this.center.getY() + dy);
            this.center = current;
        }
    }

    /**
     * function that move the ball one step on the screen, by change the ball center according the ball direction.
     */
    public void moveOneStep() {
        Line trajectory = this.getTrajectory();
        // get the collision point and object.
        CollisionInfo collision = this.game.getClosestCollision(trajectory);
        // get in if there is a colission at trajectory, if not move step.
        if (collision != null) {
            Velocity toChange = collision.collisionObject().hit(this, collision.collisionPoint(), this.velocity);
            Point cPoint = collision.collisionPoint();
            // move step smaller and change velocity.
            this.smallStep(cPoint);
            this.setVelocity(toChange);
        } else {
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * removing ball from game.
     * @param game the game to remove current block from.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }

    /**
     * function to check if the ball exit from the screen, and if it is reverse the direction.
     * @param width the width to check if it is exit.
     * @param height the height to check if it is exit.
     * @param startWidth the starting width to check if it is exit.
     * @param startHeight the starting height to check if it is exit.
     */
    public void checkBorders(double width, double height, double startWidth, double startHeight) {
        // if the ball exit from the width and the height.
        if (this.checkWidth(width, startWidth) && this.checkHeight(height, startHeight)) {
            this.setVelocity(-this.velocity.getDx(), -this.velocity.getDy());
            return;
        }

        // if the ball exit from the width.
        if (checkWidth(width, startWidth)) {
            this.setVelocity(-this.velocity.getDx(), this.velocity.getDy());
        }

        // if the ball exit from the height.
        if (checkHeight(height, startHeight)) {
            this.setVelocity(this.velocity.getDx(), -this.velocity.getDy());
        }
    }

    /**
     * check if the ball exit from width - left or right - of screen.
     * @param width the width to check.
     * @param start the width start.
     * @return true if it is, false if not.
     */
    public boolean checkWidth(double width, double start) {
        if (this.center.getX() + this.r > width || this.center.getX() - this.r < start) {
            return true;
        }
        return false;
    }

    /**
     * check if the ball exit from height - bottom or top - of screen.
     * @param height the height to check.
     * @param start the start height to check.
     * @return true if it is, false if not.
     */
    public boolean checkHeight(double height, double start) {
        if (this.center.getY() + this.r > height || this.center.getY() - this.r < start) {
            return true;
        }
        return false;
    }

    /**
     * check if the center and radius cause ball to get out of screen, and move the ball if it is needed.
     * @param width the width of screen.
     * @param height the height of screen.
     * @param startWidth the start width screen.
     * @param startHeight the start height screen.
     */
    public void checkNewBall(double width, double height, double startWidth, double startHeight) {
        // if it happens from right.
        if (this.r + this.center.getX() > width) {
            double dif = this.r + this.center.getX() - width;
            this.center = new Point(this.center.getX() - dif, this.center.getY());
        }

        // if it happens from left.
        if (this.center.getX() - this.r < startWidth) {
            double dif = -(startWidth - (this.r + this.center.getX()));
            this.center = new Point(this.center.getX() + dif, this.center.getY());
        }

        // if it happens from top.
        if (this.r + this.center.getY() > height) {
            double dif = this.r + this.center.getY() - height;
            this.center = new Point(this.center.getX(), this.center.getY() - dif);
        }

        // if it happens from bottom.
        if (this.center.getY() - this.r < startHeight) {
            double dif = -(startHeight - (this.r + this.center.getY()));
            this.center = new Point(this.center.getX(), this.center.getY() + dif);
        }
    }
}
