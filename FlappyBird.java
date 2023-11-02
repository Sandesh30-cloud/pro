import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FlappyBird extends JPanel implements ActionListener {
    private int birdY = 150;
    private int birdVelocity = 0;
    private int birdGravity = 2;
    private int pipeX = 300;
    private int pipeY = 0;
    private int pipeWidth = 50;
    private int pipeHeight = 300;
    private int pipeGap = 150;
    private boolean isGameOver = false;

    public FlappyBird() {
        Timer timer = new Timer(20, this);
        timer.start();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver) {
                    birdVelocity = -20;
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER && isGameOver) {
                    restartGame();
                }
            }
        });
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isGameOver) {
            birdVelocity += birdGravity;
            birdY += birdVelocity;
            pipeX -= 5;
            if (pipeX + pipeWidth < 0) {
                pipeX = getWidth();
                pipeY = (int) (Math.random() * (getHeight() - pipeGap));
            }
            checkCollision();
        }
        repaint();
    }

    private void checkCollision() {
        int birdRight = 50;
        int birdBottom = birdY + 50;
        int pipeRight = pipeX + pipeWidth;
        int pipeBottom = pipeY + pipeHeight;

        if (birdRight > pipeX && birdY < pipeY && birdBottom > pipeY) {
            isGameOver = true;
        }
        if (birdRight > pipeX && birdBottom > pipeBottom && birdY < pipeBottom) {
            isGameOver = true;
        }
        if (birdY > getHeight() || birdY < 0) {
            isGameOver = true;
        }
    }

    private void restartGame() {
        birdY = 150;
        birdVelocity = 0;
        pipeX = 300;
        pipeY = 0;
        isGameOver = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.GREEN);
        g.fillRect(pipeX, 0, pipeWidth, pipeY);
        g.fillRect(pipeX, pipeY + pipeGap, pipeWidth, getHeight() - pipeY - pipeGap);
        g.setColor(Color.RED);
        g.fillRect(50, birdY, 50, 50);
        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.drawString("Game Over. Press Enter to Restart", getWidth() / 2 - 100, getHeight() / 2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}