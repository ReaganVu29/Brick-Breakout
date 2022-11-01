import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class Frame {
	
	static JFrame ball = new JFrame();
	
	static GameInteraction gameInteraction = new GameInteraction();
	
	
	
	public static void main(String[] args) {
		
	
		
		//makes the ball bounce off the walls
		ball.setBounds(10, 10, 700, 600);
		ball.setVisible(true);
		ball.add(gameInteraction);
	
		
		
		
		
		
	}

	

}