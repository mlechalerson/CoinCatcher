import java.awt.*;

public class Monster {
    private static final int SIZE = 30;
    private static final double BASE_SPEED = 3.0;
    private static final double SPEED_INCREASE_PER_COIN = 0.2; // Zwiększenie prędkości o 0.2 za każdą monetę
    private static final double MAX_SPEED = 8.0; // Maksymalna prędkość monstera
    private int x, y;
    private double currentSpeed;

    public Monster(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentSpeed = BASE_SPEED;
    }

    public void reset(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentSpeed = BASE_SPEED;
    }

    public void updateSpeed(int coinCount) {
        currentSpeed = Math.min(
                BASE_SPEED + (SPEED_INCREASE_PER_COIN * coinCount),
                MAX_SPEED
        );
    }

    public void moveTowards(int targetX, int targetY) {
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > 0) {
            x += (dx / distance) * currentSpeed;
            y += (dy / distance) * currentSpeed;
        }
    }

    public boolean checkCollision(Player player) {
        return getBounds().intersects(player.getBounds());
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, SIZE, SIZE);
    }

    private Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
}