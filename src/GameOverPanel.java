import javax.swing.*;
import java.awt.*;
public class GameOverPanel extends JPanel {
    private final JLabel scoreLabel;
    private final GameWindow gameWindow;
    public GameOverPanel(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel = new JLabel("Zebrane monety: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton restartButton = new JButton("Restart");
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(e -> gameWindow.startGame());
        add(gameOverLabel); add(Box.createRigidArea(new Dimension(0, 10)));
        add(scoreLabel); add(Box.createRigidArea(new Dimension(0, 10))); add(restartButton);
    }
    public void updateScore(int score) {
        scoreLabel.setText("Zebrane monety: " + score); }
}
