package interfaces;

import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;

/**
 * interface to things collides with ball.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return rectangle.
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @param hitter the ball that hitted.
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
