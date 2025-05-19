package hitlisteners;

import shapes.Ball;
import gamesetup.Block;
import gamesetup.Counter;
import gamesetup.Game;
import interfaces.HitListener;

/**
 * class to remove balls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    static final int BALL_DECREASE = 1;
    /**
     * constructor.
     * @param game the game to remove from.
     * @param remainingBalls the balls number remained in game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Balls that are hit should be removed from the game.
     * @param beingHit the block that being hitted.
     * @param hitter the ball that hitted block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(BALL_DECREASE);
        hitter.removeFromGame(game);
    }
}
