package gamesetup;

import interfaces.Collidable;
import interfaces.Sprite;
import shapes.Rectangle;
import shapes.Point;
import shapes.Line;
import shapes.Velocity;
import shapes.Ball;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * class to implements paddle for the game.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private java.awt.Color color;
    static final double PARTS = 5;
    static final double WIDTH = 800;
    static final double STEP = 5;
    static final double START = 0;
    static final int ONE = 1;
    static final double LEFT = 330;
    static final double VERY_LEFT = 300;
    static final double RIGHT = 30;
    static final double VERY_RIGHT = 60;

    /**
     * moving the paddle left.
     */
    public void moveLeft() {
        Point newUpper;
        // check if the paddle out of screen.
        if (rectangle.getUpperLeft().getX() + rectangle.getWidth() - STEP < START) {
            newUpper = new Point(WIDTH, rectangle.getUpperLeft().getY());
        } else {
            newUpper = new Point(rectangle.getUpperLeft().getX() - STEP, rectangle.getUpperLeft().getY());
        }
        rectangle = new Rectangle(newUpper, rectangle.getWidth(), rectangle.getHeight());
    }
    /**
     * moving the paddle right.
     */
    public void moveRight() {
        Point newUpper;
        // check if the paddle out of screen.
        if (rectangle.getUpperLeft().getX() + STEP > WIDTH) {
            newUpper = new Point(START, rectangle.getUpperLeft().getY());
        } else {
            newUpper = new Point(rectangle.getUpperLeft().getX() + STEP, rectangle.getUpperLeft().getY());
        }
        rectangle = new Rectangle(newUpper, rectangle.getWidth(), rectangle.getHeight());
    }
    /**
     * constructor.
     * @param gui the gui to draw.
     * @param rectangle the paddle rectangle.
     * @param color paddle color.
     */
    public Paddle(GUI gui, Rectangle rectangle, java.awt.Color color) {
        this.keyboard = gui.getKeyboardSensor();
        this.rectangle = rectangle;
        this.color = color;
    }
    /**
     * check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * drawing paddle.
     * @param d the surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * return the rectangle of the paddle.
     * @return rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * check if point is on a vertical line.
     * @param point the point to check.
     * @param line the line on it.
     * @return true if yes, false if not.
     */
    public boolean onLineVertical(Point point, Line line) {
        if (point.getY() > line.getEnd().getY() && point.getY() < line.getStart().getY()) {
            return true;
        }
        return false;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param hitter the ball that hitter object.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return the new velocity after updating according to fun paddle rules.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // if hits the sides of paddle.
        if (this.onLineVertical(collisionPoint, rectangle.left())
                || this.onLineVertical(collisionPoint, rectangle.right())) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        // find a range of parts in fun paddle.
        double range = this.rectangle.getWidth() / PARTS;
        double start = this.rectangle.getUpperLeft().getX();
        double xValue = collisionPoint.getX();
        // the point i the width range.
        double spot = xValue - start;
        // find in which part the point is.
        int part = (int) (spot / range) + ONE;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt(dx * dx + dy * dy);
        // fit the right velocity to the part in fun paddle.
        switch (part) {
            case 1:
                return Velocity.fromAngleAndSpeed(VERY_LEFT, speed);
            case 2:
                return Velocity.fromAngleAndSpeed(LEFT, speed);
            case 3:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 4:
                return Velocity.fromAngleAndSpeed(RIGHT, speed);
            case 5:
                return Velocity.fromAngleAndSpeed(VERY_RIGHT, speed);
            default:
                return currentVelocity;
        }
    }
    /**
     * Add this paddle to the game.
     * @param g the game.
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}