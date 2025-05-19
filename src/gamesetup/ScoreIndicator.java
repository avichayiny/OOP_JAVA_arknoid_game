package gamesetup;

import interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;


/**
 * class draw scores on screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter points;

    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int FONT_SIZE = 25;
    static final int BLOCK_HEIGHT = 20;
    static final int BLOCK_SIZE = 340;
    /**
     * constructor.
     * @param points the points in the beginning.
     */
    public ScoreIndicator(Counter points) {
        this.points = points;
    }

    /**
     * draw the sprite to the screen.
     * @param d the drawface to draw on it.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawRectangle(0, 0, WIDTH, HEIGHT);
        String points = Integer.toString(this.points.getValue());
        d.setColor(Color.BLACK);
        d.drawText(BLOCK_SIZE, BLOCK_HEIGHT, "Score: " + points, FONT_SIZE);
    }
    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
