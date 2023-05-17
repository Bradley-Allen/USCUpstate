
public class Rectangle implements Comparable<Rectangle>{
	private int width;
	private int height;
	
	public Rectangle(int w, int h) {
		width = w;
		height = h;
	}
	
	public int compareTo(Rectangle other) {
		return (width * height) - (other.width * other.height);
	}

}
