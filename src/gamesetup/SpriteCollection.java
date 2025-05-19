package gamesetup;

import interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.LinkedList;

/**
 * class that have a collection of sprites.
 */
public class SpriteCollection {
    private LinkedList<Sprite> sprites = new LinkedList<>();

    /**
     * adding a sprite to the list.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * get the sprites collection.
     * @return sprites list.
     */
    public LinkedList<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d the drawface to draw on it.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).drawOn(d);
        }
    }
}