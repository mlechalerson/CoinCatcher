import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {
    private static final int SIZE = 30;
    private static final int SPEED = 5;
    private int x, y;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public int speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    int mango;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = SPEED;
        direction = "down";
        getPlayerImage();

        // Inicjalizacja obszaru kolizji
        solidArea = new Rectangle(8, 16, 32, 32);
    }

    private void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-up.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-up-2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-down.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-down-2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-left.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-left-2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-right.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/Bomba-right-2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        spriteCounter = 0;
        spriteNum = 1;
    }

    public void move(boolean[] keys, int maxWidth, int maxHeight) {
        boolean isMoving = false;

        if (keys[KeyEvent.VK_W]) {
            y = Math.max(0, y - speed);
            direction = "up";
            isMoving = true;
        }
        if (keys[KeyEvent.VK_S]) {
            y = Math.min(maxHeight - SIZE, y + speed);
            direction = "down";
            isMoving = true;
        }
        if (keys[KeyEvent.VK_A]) {
            x = Math.max(0, x - speed);
            direction = "left";
            isMoving = true;
        }
        if (keys[KeyEvent.VK_D]) {
            x = Math.min(maxWidth - SIZE, x + speed);
            direction = "right";
            isMoving = true;
        }

        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
        }

        if (image != null) {
            g2.drawImage(image, x, y, SIZE, SIZE, null);
        } else {
            // Fallback jeśli obrazek nie został załadowany
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, SIZE, SIZE);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}