package tool;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class ScreenSize {
	private final static Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int getWidth(){
		return SIZE.width;
	}
	
	public static int getHeight(){
		return SIZE.height;
	}
	
}
