// CoinManager.java
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CoinManager {
    private static final int COIN_SIZE = 20;
    private static final int INITIAL_COINS = 5;
    private final ArrayList<Coin> coins;
    private final Random random;
    private final int maxWidth;
    private final int maxHeight;

    public CoinManager(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        coins = new ArrayList<>();
        random = new Random();
        reset();
    }

    public void reset() {
        coins.clear();
        for (int i = 0; i < INITIAL_COINS; i++) {
            generateNewCoin();
        }
    }

    private void generateNewCoin() {
        coins.add(new Coin(
                random.nextInt(maxWidth - COIN_SIZE),
                random.nextInt(maxHeight - COIN_SIZE)
        ));
    }

    public boolean checkCollisions(Player player) {
        Rectangle playerBounds = player.getBounds();
        for (int i = coins.size() - 1; i >= 0; i--) {
            Coin coin = coins.get(i);
            if (coin.getBounds().intersects(playerBounds)) {
                coins.remove(i);
                generateNewCoin();
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        for (Coin coin : coins) {
            g.fillOval(coin.getX(), coin.getY(), COIN_SIZE, COIN_SIZE);
        }
    }

    // Wewnętrzna klasa reprezentująca monetę
    private static class Coin {
        private final int x;
        private final int y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, COIN_SIZE, COIN_SIZE);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}