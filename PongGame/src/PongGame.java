import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class PongGame extends JComponent implements ActionListener, MouseMotionListener{
	private int ballX = 400;
	private int ballY = 150;
	private int paddleX = 0;
	private int speed = 10;
	private static int counter = 0;
	private int ballXSpeed = speed;
	private int ballYSpeed = speed;

	public static void main(String[] args){
		
		JFrame window = new JFrame("Pong Game");
		PongGame game = new PongGame(); 
		JLabel score = new JLabel("Score: " + counter);
		score.setHorizontalTextPosition(JLabel.CENTER);
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.add(score);
		window.setVisible(true);
		
		Timer t = new Timer(100, game);
		t.start();
		
		window.addMouseMotionListener(game);

	}
	
	public Dimension getPreferredSize(){
		return new Dimension(800,600);
	}

	@Override
	protected void paintComponent(Graphics g){
		// Draws the sky
		g.setColor(new Color(178, 223, 224));
		g.fillRect(0, 0, 800, 600);
		
		// Draws the paddle
		g.setColor(new Color(110, 65, 13));
		g.fillRect(paddleX, 510, 150, 15);
		
		// Draws the ball
		g.setColor(new Color(155, 93, 169));
		g.fillOval(ballX, ballY, 30, 30);
	}
	
	public void actionPerformed(ActionEvent e){
		ballX = ballX + ballXSpeed;
		ballY = ballY + ballYSpeed;
		
		if(ballY == 490 && ballX >= paddleX && ballX <= (paddleX + 150))
		{
			ballYSpeed = speed*-1;
			counter++;
		}
		if(ballX == 770)
			ballXSpeed = speed*-1;
		if(ballX == 0)
			ballXSpeed = speed;
		if(ballY == 0)
			ballYSpeed = speed;
		repaint();
		
		if(counter%10 == 0)
			speed = speed + 5*(counter/10);
	}
	
	public void mouseDragged(MouseEvent e){}
	
	public void mouseMoved(MouseEvent e){
		paddleX = e.getX() - 75;
		repaint();
	}
}
