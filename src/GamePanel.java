import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GamePanel extends JPanel {
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 600;
    private final GameWindow gameWindow;
    private final Player player;
    private final Monster monster;
    private final CoinManager coinManager;
    private final InputManager inputManager;
    public GamePanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.WHITE); setFocusable(true);
        player = new Player(PANEL_WIDTH / 2, PANEL_HEIGHT / 2);
        monster = new Monster(50, 50);
        coinManager = new CoinManager(PANEL_WIDTH, PANEL_HEIGHT);
        inputManager = new InputManager();
        addKeyListener(inputManager);
    }
    public void resetGame() {
        player.reset(PANEL_WIDTH / 2, PANEL_HEIGHT / 2);
        monster.reset(50, 50);
        coinManager.reset();
    }
    public void update() {
        player.move(inputManager.getKeys(), PANEL_WIDTH, PANEL_HEIGHT);
        monster.updateSpeed(gameWindow.getScore()); // Aktualizacja prędkości monstera
        monster.moveTowards(player.getX(), player.getY());

        if (coinManager.checkCollisions(player)) {
            gameWindow.incrementScore();
        }

        if (monster.checkCollision(player)) {
            gameWindow.gameOver();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
        monster.draw(g2d);
        coinManager.draw(g2d);
        // Rysowanie licznika monet
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Monety: " + gameWindow.getScore(), 10, 30);
    }
}
