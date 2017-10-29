import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;

abstract class Char implements Command {

    private final int idleImageNo = 1;
    private final int atkImageNo = 1;
    private final int jumpImageNo = 1;
    private final int dmgImageNo = 1;

    protected BufferedImage icon;
    protected BufferedImage[] currentImage;
    protected BufferedImage[] idleL = new BufferedImage[idleImageNo];
    protected BufferedImage[] idleR = new BufferedImage[idleImageNo];
    protected BufferedImage[] atkL = new BufferedImage[atkImageNo];
    protected BufferedImage[] atkR = new BufferedImage[atkImageNo];
    protected BufferedImage[] jumpL = new BufferedImage[jumpImageNo];
    protected BufferedImage[] jumpR = new BufferedImage[jumpImageNo];
    protected BufferedImage[] damagedL = new BufferedImage[dmgImageNo];
    protected BufferedImage[] damagedR = new BufferedImage[dmgImageNo];

    protected int hp;
    protected int type;
    protected boolean attacking;
    protected boolean alive;
    protected boolean idle;
    protected boolean jumping;
    protected char direction;
    protected int x, y, width, height;
    protected int picIndex;
    protected boolean showArrow;

    private ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
    private Weapon weapon; //only for special

    private Rectangle boundingBox;

    /**
     * Constructor
     * All variables are the same at beginning of a game
     */
    Char() {
        this.hp = 100;
        this.alive = true;
        this.attacking = false;
        this.idle = true;
        this.width = 100; //subject to change
        this.height = 300; //subject to change
        this.picIndex = 0;
        this.jumping = false;
        //this.setWeapon(null);
    }

    /**
     * atk
     * Method implemented from Command interface
     * Adjusts character images and parameters for a high atk
     */
    public void atk(char c) {
        /*
        Weapon tempWeapon = null;
        //1-Archer 2-Assassin
        if (this.getType() == 1) {
            tempWeapon = new Arrow();
            tempWeapon.setAtk(1);
            if (c == 'L') {
                this.setCurrentImage(atkL);
                tempWeapon.setX(this.getX());
                tempWeapon.setY(this.getY() + 130);
                tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(), tempWeapon.getY(), 133, 11));
                tempWeapon.setDirection('L');
            } else {
                this.setCurrentImage(atkR);
                tempWeapon.setX(this.getX() + 110);
                tempWeapon.setY(this.getY() + 130);
                tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(), tempWeapon.getY(), 133, 11));
                tempWeapon.setDirection('R');
            }
        } else {
            tempWeapon = new Dagger();
            tempWeapon.setAtk(1);
            if (c == 'R') {
                tempWeapon.setX(this.getX() + 205);
                tempWeapon.setY(this.getY() + 140);
                tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(), tempWeapon.getY(), 100, 15));
                this.setCurrentImage(atkR);
            } else {
                tempWeapon.setX(this.getX() + 15);
                tempWeapon.setY(this.getY() + 140);
                tempWeapon.setBoundingBox(new Rectangle(tempWeapon.getX(), tempWeapon.getY(), 100, 15));
                this.setCurrentImage(atkL);
            }
        }
        this.weaponList.add(tempWeapon);
        this.setShowArrow(true);
        this.setAttacking(true);
        this.setIdle(false);*/
    }

    public void jump(char c, double elapsedTime) {
        if (c == 'L') {
            this.currentImage = jumpL;
        } else {
            this.currentImage = jumpR;
        }
        this.idle = false;
        this.attacking = true;
        this.jumping = true;
    }

    /**
     * move
     * Method implemented from Command interface
     * Adjusts character images and parameters to move left/right
     */
    public void move(int x) {
        this.x += x;
        this.idle = true;
        this.boundingBox = new Rectangle(this.x, this.y, this.width, this.height);
        //Changes image every time player moves
        if (this.picIndex == idleImageNo - 1) {
            this.picIndex = 0;
        } else {
            this.picIndex++;
        }
        if (x > 0) {
            this.direction = 'R';
            this.currentImage = idleR;
        } else {
            this.direction = 'L';
            this.currentImage = idleL;
        }
    }

    /**
     * update
     * Method that updates a character's images to create animation
     * Also updates the character's weapon's images
     */
    public void update(double elapsedTime) {
        /*//Make the jump frames move up and down accordingly
        if (this.isJumping()) {
            if (this.getPicIndex() < 2) {
                this.setY(this.getY() - 50);
                this.setBoundingBox(this.getX() + 90, this.getY() + 60, 100, 300);
            } else if (this.getPicIndex() > 2) {
                this.setY(this.getY() + 50);
                this.setBoundingBox(this.getX() + 90, this.getY() + 60, 100, 300);
            }
        }
        //Shuffles through entire array for each group of images
        if (this.getAttacking()) {
            if (this.getPicIndex() == currentImage.length - 1) {
                this.setPicIndex(0);
                if (this.isJumping()) {
                    this.setY(230);
                    this.setBoundingBox(this.getX() + 90, this.getY() + 60, 100, 300);
                }
                //removes bounding box for assassin high attack after attack finishes
                if (this.type == 2) {
                    for (int i = 0; i < this.getWeaponList().size(); i++) {
                        if (this.getWeaponList().get(i).getAtk() == 1) {
                            this.getWeaponList().remove(i);
                        }
                    }
                }
                if (this.getDirection() == 'L') {
                    this.setCurrentImage(idleL);
                    this.setIdle(true);
                    this.setAttacking(false);
                    this.setJumping(false);
                } else {
                    this.setCurrentImage(idleR);
                    this.setAttacking(false);
                    this.setIdle(true);
                    this.setJumping(false);
                }
            } else {
                this.setPicIndex(this.getPicIndex() + 1);

            }
        }

        //Updates weaponlist
        ArrayList<Weapon> tempList = this.getWeaponList();
        Weapon tempWeapon;
        for (Weapon w : tempList) {
            tempWeapon = w;
            *//*
            if (this.getType() == 1) {
                ((Arrow) tempWeapon).update(elapsedTime);
            } else {
                ((Dagger) tempWeapon).update(elapsedTime);
            }*//*
        }*/
    }

    public boolean damage(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            alive = false;
        }
        return alive;
    }

    public void draw(Graphics g) {
    }

}


