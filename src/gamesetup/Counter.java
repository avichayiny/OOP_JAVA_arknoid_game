package gamesetup;

/**
 * class to count things.
 */
public class Counter {
    private int value = 0;
    /**
     * add number to current count.
     * @param number number to increase to value.
     */
    public void increase(int number) {
        this.value += number;
    }
    /**
     * subtract number from current count.
     * @param number number to decrease from value.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * get current count.
     * @return value;
     */
    public int getValue() {
        return this.value;
    }
}