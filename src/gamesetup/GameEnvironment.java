package gamesetup;

import shapes.Line;
import shapes.Point;
import interfaces.Collidable;

import java.util.LinkedList;
/**
 * class to define and save the game collidies.
 */
public class GameEnvironment {
    private LinkedList<Collidable> collides = new LinkedList<>();
    static final int FIRST = 1;

    /**
     * add the given collidable to the environment.
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.collides.add(c);
    }

    /**
     * get the collidable collection.
     * @return list of collidables.
     */
    public LinkedList<Collidable> getCollides() {
        return this.collides;
    }
    /**
     * return the information about the closest collision that is going to occur with trajectory line, if there is.
     * @param trajectory the trajectory line.
     * @return the information - point and collision, null if there isn't.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closest = trajectory.closestIntersectionToStartOfLine(collides.getFirst().getCollisionRectangle());
        Collidable close = collides.getFirst();
        // check all collides and find if there is colission point with them. find the closest.
        for (int i = FIRST; i < collides.size(); i++) {
            Point current = trajectory.closestIntersectionToStartOfLine(collides.get(i).getCollisionRectangle());
            if (closest == null) {
                closest = current;
                close = collides.get(i);
                continue;
            }
            if (current == null) {
                continue;
            }
            // check if the current  closer than the closest.
            if (current.distance(trajectory.getStart()) < closest.distance((trajectory.getStart()))) {
                closest = current;
                close = collides.get(i);
            }
        }
        if (closest == null) {
            return null;
        }
        return new CollisionInfo(closest, close);
    }

}
