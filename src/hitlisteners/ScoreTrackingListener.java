package hitlisteners;

import shapes.Ball;
import gamesetup.Block;
import gamesetup.Counter;
import interfaces.HitListener;

/**
 * class to update scores in game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    static final int POINTS = 5;

    /**
     * constructor.
     * @param scoreCounter the points in the beginnings.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * update scores in the game when block removed.
     * @param beingHit the object that hitted.
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       beingHit.removeHitListener(this);
       this.currentScore.increase(POINTS);
    }
}