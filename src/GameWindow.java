import javax.swing.*;
import java.awt.*;
public class GameWindow extends JFrame {
    private GamePanel gamePanel;
    private Timer gameTimer;
    private GameOverPanel gameOverPanel;
    private int score = 0;
    public GameWindow() {
        setTitle("Coin Collector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        gamePanel = new GamePanel(this);
        add(gamePanel);
        gameOverPanel = new GameOverPanel(this);
        gameOverPanel.setVisible(false);
        gamePanel.add(gameOverPanel);
        gameTimer = new Timer(16, e -> { gamePanel.update(); gamePanel.repaint(); });
        pack(); setLocationRelativeTo(null);
        startGame();
    }
    public void startGame() {
        score = 0;
        gamePanel.resetGame();
        gameOverPanel.setVisible(false);
        gameTimer.start();
        gamePanel.requestFocus();
    }
    public void gameOver() {
        gameTimer.stop();
        gameOverPanel.updateScore(score);
        gameOverPanel.setVisible(true);
    }
    public void incrementScore() {
        score++;
    }
    public int getScore() {
        return score; }
}