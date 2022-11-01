import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bricks {
	public int bricks[][];
	public int bWidth;
	public int bHeight;
	
	
	//creates the cubes
	public Bricks(int row, int col) {
		bricks = new int[row][col];		
		for(int a = 0; a < bricks.length; a++)
		{
			for(int b = 0; b < bricks[0].length; b++)
			{
				bricks[a][b] = 1;
			}			
		}
		//assigns the dimensions of the cubes
		bWidth = 500/col;
		bHeight = 170/row;
		
	}	
	//draws the cubes
	public void draw(Graphics2D g) {
		for(int a = 0; a < bricks.length; a++)
		{
			for(int b = 0; b < bricks[0].length; b++)
			{
				if(bricks[a][b] > 0) {
					g.setColor(Color.white);
					g.fillRect(b * bWidth + 80, a * bHeight + 50, bWidth, bHeight);
					//creates an outline on each cube so that it does not look like one big cube
					//code can run without these next 3 lines
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(b * bWidth + 80, a * bHeight + 50, bWidth, bHeight);	
						
				}
				
			}			
		}
		
	}
	
	public void setValueOfB(int value, int row, int col) {
		bricks[row][col] = value; 
		
	}
}
