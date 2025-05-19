package interfaces;

import shapes.Ball;
import gamesetup.Block;

/**
 * interface for listeners.
 */
public interface HitListener {
    /**
     * method is called whenever the beingHit object is hit.
     * @param beingHit the object that hitted.
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
