package interfaces;

import biuoop.DrawSurface;
/**
 * interface to sprites of game - ball, paddle and blocks.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d the drawface to draw on it.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}