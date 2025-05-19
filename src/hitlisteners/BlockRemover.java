package hitlisteners;

import shapes.Ball;
import gamesetup.Block;
import gamesetup.Counter;
import gamesetup.Game;
import interfaces.HitListener;

/**
 * class is in charge of removing blocks from the game,
 * as well as keeping count the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;
    static final int DECREASE_BLOCKS = 1;

    /**
     * constructor.
     * @param game the game to remove from.
     * @param remainingBlocks the blocks number remained in game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed from the game.
     * @param beingHit the block that being hitted.
     * @param hitter the ball that hitted block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getColor());
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(DECREASE_BLOCKS);
        beingHit.removeFromGame(game);
    }
}