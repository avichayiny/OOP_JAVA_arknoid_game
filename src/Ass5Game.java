// 206626947

import gamesetup.Game;

/**
 * class to create a game object, initializes and runs it.
 */
public class Ass5Game {
    /**
     * the main method.
     * @param args from cmd.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}