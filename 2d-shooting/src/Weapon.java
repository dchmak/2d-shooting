/**
 * Weapon.java
 * Abstract class to hold variables for different weapons used
 * June 14 2017
 *
 * @author Daniel
 */

import java.awt.*;

abstract public class Weapon {
    private int x, y;
    private int speed = 50;
    private Rectangle boundingBox;
    private int atk; // base on type of weapon
    private char direction;

    //Constructor
    Weapon() {
    }

    public void draw(Graphics g) {
    }
}
