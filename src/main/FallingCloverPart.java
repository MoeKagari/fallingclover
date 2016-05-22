package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JWindow;

import tool.Config;
import tool.Picture;
import tool.Sleep;

@SuppressWarnings("serial")
public final class FallingCloverPart extends JPanel implements Runnable{
	private JWindow window;
	private BufferedImage image;
	private Mover mover;
	public FallingCloverPart() {
		initImage();
		initWindow();
		initMover();
	}
	private void initImage() {
		try {
			int size = Config.CLOVER_SIZE;
			BufferedImage bi = ImageIO.read(Picture.getCloverPicture());
			
			if(size <= 0)
				image = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
			else
				image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
			
			image.createGraphics().drawImage(bi, 0, 0, image.getWidth(), image.getHeight(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void initWindow(){
		window = new JWindow();
		window.setSize(image.getWidth(), image.getHeight());
		window.setLayout(new BorderLayout());
		window.setBackground(new Color(0, 0, 0, 0));
		window.setAlwaysOnTop(true);

		window.add(this, BorderLayout.CENTER);
	}
	private void initMover(){
		mover = new Mover(window);
	}
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
	public void run() {
		window.setVisible(true);
		while (true) {
			if (!mover.canMove())
				break;
			mover.move();
			Sleep.sleep(Config.MOVE_SLEEP_TIME);
		}
	}
}