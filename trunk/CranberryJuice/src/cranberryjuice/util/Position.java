package cranberryjuice.util;

/**
 * A simple class that holds the position of an object.
 * 
 * @author David
 *
 */
public class Position {
	
	private int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position() {}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
