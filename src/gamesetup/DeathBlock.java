package gamesetup;

import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;

/**
 * class to death block - when killing ball.
 */
public class DeathBlock extends Block {
    static final int WIDTH = 800;
    static final double SIDES_HEIGHT = 600;
    static final double BLOCK_HEIGHT = 20;

    /**
     * constructor.
     */
    public DeathBlock() {
        super(new Rectangle(new Point(0, SIDES_HEIGHT), WIDTH, BLOCK_HEIGHT), Color.WHITE);
    }
}
