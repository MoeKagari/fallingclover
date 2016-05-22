package tool;

import java.awt.Dimension;
import java.awt.Toolkit;

public final class ScreenSize {
	private final static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static int getWidth(){
		return size.width;
	}
	
	public static int getHeight(){
		return size.height;
	}
	
}
