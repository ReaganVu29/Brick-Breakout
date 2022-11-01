import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GameInteraction extends JPanel implements KeyListener, ActionListener {
	private boolean commence = false; 

	// How many bricks you have to break
	private int amountOfBricks = 20; 
	
	private Timer clock; 
	
	private int delay = 8; 
	
	// start position of paddle
	private int xPosPlayer = 310;
	
	// start position of ball
	private int xPosBall = 310; 
	private int yPosBall = 470; 
	
	// speed of ball
	private int xDirBall = -1;
	private int yDirBall = -1;
	
	private Bricks bricks;
	
	
	public GameInteraction() {
		
		bricks = new Bricks(1,4);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		clock = new Timer(delay, this);
		clock.start();
	}
			
	public void paint(Graphics g) {
		// background of game
		g.setColor(Color.black);
		g.fillRect(1, 1, 700, 600);
		
		
		//Draws the Bricks of the games
		bricks.draw((Graphics2D)g);
		

		
		// Draws the paddle 
		g.setColor(Color.white);
		g.fillRect(xPosPlayer, 490, 100, 8);
		
		// Draws the ball
		g.setColor(Color.white);
		g.fillOval(xPosBall, yPosBall, 20, 20);
		
		g.dispose();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//stops game after ball falls under y value of 600
		if(yPosBall > 600) {
			clock.stop();
			
			return;
		}
		
		
		clock.start();
		if(commence) {
			if(new Rectangle(xPosBall, yPosBall, 20, 20).intersects(new Rectangle(xPosPlayer, 490, 100, 8))) {
				yDirBall = -yDirBall;
			}
			Z: for(int a = 0; a <bricks.bricks.length; a++) {
				for(int b = 0; b <bricks.bricks[0].length; b++) {
					if(bricks.bricks[a][b] > 0) {
						int HorizontalOfBrick = b * bricks.bWidth + 20;
						int VerticalOfBrick = a * bricks.bHeight + 30;
						int bWidth = bricks.bWidth;
						int bHeight = bricks.bHeight;
						
						Rectangle block = new Rectangle(HorizontalOfBrick, VerticalOfBrick, bWidth, bHeight);
						Rectangle ballBlock = new Rectangle(xPosBall, yPosBall, 20, 20);
						Rectangle blockBrick = block;
						
						// Checks collision if ball hits either side of brick and makes it disappear
						if(ballBlock.intersects(blockBrick)) {
							bricks.setValueOfB(0, a, b);
							amountOfBricks --;
						
							
							
							if(xPosBall + 20 <= blockBrick.x || xPosBall + 1 >= blockBrick.x + blockBrick.width)	
							{
								xDirBall = -xDirBall;
							}
							// 
							else
							{
								yDirBall = -yDirBall;				
							}
							
							break Z;
						}
						
					}
				}
			}
			
			// mechanics of the ball
			xPosBall += xDirBall;
			yPosBall += yDirBall;
			if(xPosBall < 0) {
				xDirBall = -xDirBall;
			}
			if(yPosBall < 0) {
				yDirBall = -yDirBall;
			}
			if(xPosBall > 500) {
				xDirBall = -xDirBall;
			}
		}
		repaint();
	}
	
	//assigns the arrow keys to a certain movement
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){        
			if(xPosPlayer >= 600)
			{
				xPosPlayer = 600;
			}
			else
			{
				moveDirRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT){          
			if(xPosPlayer < 10)
			{
				xPosPlayer = 10;
			}
			else
			{
				moveDirLeft();
			}
        }	
	}
	
	//calls the arrow keys and assigns a speed to them
	public void moveDirRight() {
		commence = true;
		xPosPlayer += 2;
	}
	
	public void moveDirLeft() {
		commence = true;
		xPosPlayer -= 2;
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
