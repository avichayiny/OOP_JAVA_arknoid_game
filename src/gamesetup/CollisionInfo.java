package gamesetup;

import interfaces.Collidable;
import shapes.Point;

/**
 * class to get information about the coliision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable colllisionObject;

    /**
     * constructor.
     * @param collisionPoint the point collision.
     * @param colllisionObject the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable colllisionObject) {
        this.collisionPoint = collisionPoint;
        this.colllisionObject = colllisionObject;
    }
    /**
     * return the point at which the collision occurs.
     * @return the point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * return the collidable object involved in the collision.
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.colllisionObject;
    }
}