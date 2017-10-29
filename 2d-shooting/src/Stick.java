/**
 * Archer.java
 * Class to create an object Archer, which extends character class
 * June 14 2017
 *
 * @author Bryen & Michelle
 */

import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;

public class Stick extends Char {

    public Stick(int p, int t) {
        super();
        try {
            switch (t) {
                case 0:
                    this.idleR[0] = ImageIO.read(new File("Image/Stick/blackIdleR.png"));
                    this.idleL[0] = ImageIO.read(new File("Image/Stick/blackIdleL.png"));
                    break;
                case 1:
                    this.idleR[0] = ImageIO.read(new File("Image/Stick/purpleIdleR.png"));
                    this.idleL[0] = ImageIO.read(new File("Image/Stick/purpleIdleL.png"));
                    break;
                case 2:
                    this.idleR[0] = ImageIO.read(new File("Image/Stick/greenIdleR.png"));
                    this.idleL[0] = ImageIO.read(new File("Image/Stick/greenIdleL.png"));
                    break;
                case 3:
                    this.idleR[0] = ImageIO.read(new File("Image/Stick/blueIdleR.png"));
                    this.idleL[0] = ImageIO.read(new File("Image/Stick/blueIdleL.png"));
                    break;
                default:
                    System.out.println("Invalid team number");
            }
            this.damagedR[0] = ImageIO.read(new File("Image/Stick/damagedR.png"));
            this.damagedL[0] = ImageIO.read(new File("Image/Stick/damagedL.png"));

            int i;
            for (i = 0; i < 5; i++) {
                //this.jumpL[i] = ImageIO.read(new File(""));
                //this.jumpR[i] = ImageIO.read(new File("")); no image for jump yet
            }

            if (p == 1) {
                this.currentImage = this.idleR;
                this.direction = 'R';
                this.x = 30;
                this.y = 230;
            } else {
                this.currentImage = this.idleL;
                this.direction = 'L';
                this.x = 930;
                this.y = 230;
            }
            //this.setBoundingBox(this.getX() + 100, this.getY() + 40, 100, 300); (x,y) at center or corner?

        } catch (Exception e) {
            System.out.println("error loading stick figure");
        }
    }

    /**
     * draw
     * Method to draw Archer character
     */
    public void draw(Graphics g) {
        g.drawImage(currentImage[this.picIndex], this.x, this.y, null);
    }
}
