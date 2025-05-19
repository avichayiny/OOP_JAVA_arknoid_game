package gamesetup;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import interfaces.Collidable;
import interfaces.Sprite;
import shapes.Ball;
import shapes.Point;
import shapes.Rectangle;
import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * class for initialize and run game.
 */
public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Counter blockCounter = new Counter();
    private Counter ballsCounter = new Counter();
    private Counter points = new Counter();
    private GUI gui;
    private  biuoop.Sleeper sleeper;
    static final int ONE = 1;
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int BALL_NUM = 3;
    static final int TWICE = 2;
    static final double BLOCK_WIDTH = 40;
    static final double BLOCK_HEIGHT = 20;
    static final double START_X = 160;
    static final double START_Y = 40;
    static final double RANDOM_NUM = 9;
    static final int MIN = 0;
    static final int MAX = 12;
    static final int BALL_R = 5;
    static final double VELOCITY = 3;
    static final double START_BALL = 400;
    static final double SIDES_HEIGHT = 580;
    static final double PADDLE_WIDTH = 150;
    static final double RIGHT_START = 780;
    static final int FRAMES = 60;
    static final int MILLISECONDS = 1000;
    static final int NEXT_LEVEL = 100;
    /**
     * adding collidable to the list.
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * adding sprite to list.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removing collidable from game.
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollides().remove(c);
    }

    /**
     * removing sprite from game.
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball and add them to the game.
     */
    public void initialize() {
        gui = new GUI("Game", WIDTH, HEIGHT);
        sleeper = new biuoop.Sleeper();
        BlockRemover remover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        ScoreIndicator gamePoints = new ScoreIndicator(this.points);
        ScoreTrackingListener updatePoints = new ScoreTrackingListener(this.points);
        // create paddle.
        Paddle paddle = new Paddle(this.gui, new Rectangle(new Point(BLOCK_HEIGHT,
                SIDES_HEIGHT - BLOCK_HEIGHT), PADDLE_WIDTH, BLOCK_HEIGHT), java.awt.Color.YELLOW);
        paddle.addToGame(this);
        // create sides blocks.
        // Top block.
        Block block1 = new Block(new Rectangle(new Point(0, BLOCK_HEIGHT), WIDTH, BLOCK_HEIGHT), Color.GRAY);
        block1.addToGame(this);
        // Left block
        Block block2 =
                new Block(new Rectangle(new Point(0, BLOCK_HEIGHT), BLOCK_HEIGHT, HEIGHT), java.awt.Color.BLACK);
        block2.addToGame(this);
        // Right block
        Block block3 =
                new Block(new Rectangle(
                        new Point(RIGHT_START, BLOCK_HEIGHT), BLOCK_HEIGHT, HEIGHT), java.awt.Color.BLACK);
        block3.addToGame(this);
        // Bottom block
        //Block block4 =
                //new Block(new Rectangle(new Point(0, SIDES_HEIGHT), WIDTH, BLOCK_HEIGHT), java.awt.Color.BLACK);
        //block4.addToGame(this);

        DeathBlock deathBlock = new DeathBlock();
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);

        int indexMin = MIN;
        int indexMax = MAX;

        /**
        Block block = new Block(new Rectangle(new Point(150, 40), BLOCK_WIDTH*4, BLOCK_HEIGHT), java.awt.Color.PINK);
        block.addToGame(this);
        blockCounter.increase(ONE);
        block.addHitListener(remover);
        block.addHitListener(updatePoints);
*/
        // lines of block in different colors.
        for (int i = indexMin; i < indexMax; i++) {
            Point start = new Point(START_X + i * BLOCK_WIDTH, START_Y);
            Block block = new Block(new Rectangle(start, BLOCK_WIDTH, BLOCK_HEIGHT), java.awt.Color.BLUE);
            block.addToGame(this);
            blockCounter.increase(ONE);
            block.addHitListener(remover);
            block.addHitListener(updatePoints);
        }
        indexMin++;
        indexMax--;
        for (int i = indexMin; i < indexMax; i++) {
            Point start = new Point(START_X + i * BLOCK_WIDTH, START_Y + BLOCK_HEIGHT);
            Block block = new Block(new Rectangle(start, BLOCK_WIDTH, BLOCK_HEIGHT), java.awt.Color.PINK);
            block.addToGame(this);
            blockCounter.increase(ONE);
            block.addHitListener(remover);
            block.addHitListener(updatePoints);
        }
        indexMin++;
        indexMax--;
        double times = TWICE;
        for (int i = indexMin; i < indexMax; i++) {
            Point start = new Point(START_X + i * BLOCK_WIDTH, START_Y + BLOCK_HEIGHT * times);
            Block block = new Block(new Rectangle(start, BLOCK_WIDTH, BLOCK_HEIGHT), java.awt.Color.RED);
            block.addToGame(this);
            blockCounter.increase(ONE);
            block.addHitListener(remover);
            block.addHitListener(updatePoints);
        }
        indexMin++;
        indexMax--;
        times++;
        for (int i = indexMin; i < indexMax; i++) {
            Point start = new Point(START_X + i * BLOCK_WIDTH, START_Y + BLOCK_HEIGHT * times);
            Block block = new Block(new Rectangle(start, BLOCK_WIDTH, BLOCK_HEIGHT), java.awt.Color.GREEN);
            block.addToGame(this);
            blockCounter.increase(ONE);
            block.addHitListener(remover);
            block.addHitListener(updatePoints);
        }
        indexMin++;
        indexMax--;
        times++;
        for (int i = indexMin; i < indexMax; i++) {
            Point start = new Point(START_X + i * BLOCK_WIDTH, START_Y + BLOCK_HEIGHT * times);
            Block block = new Block(new Rectangle(start, BLOCK_WIDTH, BLOCK_HEIGHT), java.awt.Color.MAGENTA);
            block.addToGame(this);
            blockCounter.increase(ONE);
            block.addHitListener(remover);
            block.addHitListener(updatePoints);
        }
        indexMin++;
        indexMax--;
        times++;
        for (int i = indexMin; i < indexMax; i++) {
            Point start = new Point(START_X + i * BLOCK_WIDTH, START_Y + BLOCK_HEIGHT * times);
            Block block = new Block(new Rectangle(start, BLOCK_WIDTH, BLOCK_HEIGHT), java.awt.Color.CYAN);
            block.addToGame(this);
            blockCounter.increase(ONE);
            block.addHitListener(remover);
            block.addHitListener(updatePoints);
        }


        // the balls.
        for (int i = 0; i < BALL_NUM; i++) {
            Ball ball = new Ball(START_BALL + i * RANDOM_NUM,
                    START_BALL - i * RANDOM_NUM, BALL_R, java.awt.Color.BLUE);
            ball.setVelocity(VELOCITY, -VELOCITY);
            ball.startGame();
            ball.addToGame(this);
            ball.setGame(this.environment);
            this.ballsCounter.increase(ONE);
        }
        this.sprites.addSprite(gamePoints);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int framesPerSecond = FRAMES;
        int millisecondsPerFrame = MILLISECONDS / framesPerSecond;
        while (this.blockCounter.getValue() != 0 && this.ballsCounter.getValue() != 0) {
            long startTime = System.currentTimeMillis(); // timing

            // the process- draw and move for all sprites.
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        this.points.increase(NEXT_LEVEL);
        this.gui.close();
    }
}