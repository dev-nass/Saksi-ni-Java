package main;
	
import javax.swing.JFrame;
	
public class Main {
	
	public static void main (String[] args) {
			JFrame frame = new JFrame("Pokemon");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			
			GamePanel gamePanel = new GamePanel();
			frame.add(gamePanel);
			
			frame.pack(); // ensures that this JPanel (frame) will adjust its size based on the preferredSize set within the GamePanel
			
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			gamePanel.setupGame();
			gamePanel.StartGameThread(); // DELETION OF THIS FOCKING SHEET MADE THE RECTANGLE WONT MOVE
		}
	}
