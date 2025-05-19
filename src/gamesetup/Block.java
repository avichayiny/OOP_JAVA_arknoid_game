package gamesetup;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;


/**
 * class to define blocks for game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * constructor.
     * @param rectangle the rectangle of the block.
     * @param color the block color.
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * get color of block.
     * @return the color of block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl listener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * draw the block on the given DrawSurface.
     * @param surface the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();
        surface.fillRectangle(x, y, width, height);
        surface.setColor(Color.WHITE);
        surface.drawRectangle(x, y, width, height);
    }
    /**
     * return the rectangle of block.
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * adding block to game.
     * @param game the game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
        game.addCollidable(this);
    }
    /**
     * the fuunction to block, not now.
     */
    public void timePassed() {
    }

    /**
     * check if ball and current block have the same color.
     * @param ball the ball to check.
     * @return true if it is, false if not.
     */
    public boolean ballColorMatch(Ball ball) {
        if (ball.getColor().equals(this.getColor())) {
            return true;
        }
        return false;
    }

    /**
     * removing block from game.
     * @param game the game to remove current block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * notify yhe listeners that hit event happened.
     * @param hitter  the ball that hitted.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @param hitter the ball that hitted.
     * @return the new velocity after updating.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        // if the collision point is in vertical lines.
        if (collisionPoint.getX() == rectangle.right().getStart().getX() || collisionPoint.getX()
                == rectangle.left().getStart().getX()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            // if the collision point is in horizon lines.
        } else if (collisionPoint.getY() == rectangle.above().getStart().getY()
        || collisionPoint.getY() == rectangle.under().getStart().getY()) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        return currentVelocity;
    }
}
