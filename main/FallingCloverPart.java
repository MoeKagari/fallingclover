package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JWindow;

import tool.Config;
import tool.Picture;
import tool.Random;
import tool.ScreenSize;

public final class FallingCloverPart {
	private JWindow clover;
	private Mover mover;
	public FallingCloverPart() {
		CloverPanel cloverPanel = new CloverPanel();
		
		clover = new JWindow();
		clover.setSize(cloverPanel.getWidth(), cloverPanel.getHeight());
		clover.setLayout(new BorderLayout());
		clover.setBackground(new Color(0, 0, 0, 0));
		clover.setAlwaysOnTop(true);

		clover.add(cloverPanel, BorderLayout.CENTER);
		
		this.mover = new Mover(clover);
	}
	public void display(){
		clover.setVisible(true);
	}
	public boolean canMove(){
		return mover.canMove();
	}
	public void move(){
		mover.move();
	}
	
	final class Mover {
		private int screenWidth,screenHeight,zoneWidth,zoneHeight;
		private int x,y,y_period,y_offset_default,y_offset,x_offset;
		
		private JWindow clover;
		public Mover(final JWindow clover) {
			this.clover = clover;

			screenWidth = ScreenSize.getWidth();
			screenHeight = ScreenSize.getHeight();
			zoneWidth = Random.get().nextInt(30) + 100;
			zoneHeight = screenHeight;
			x = Random.get().nextInt(screenWidth - zoneWidth - clover.getWidth() + 1);
			y = 0;
			y_period = Random.get().nextInt(100) + 350;
			y_offset_default = Random.get().nextInt(y_period);
			
			y_offset = 0 - y_offset_default;
			calculate_x_offset();
			
			move();
		}
		private void calculate_x_offset(){
			x_offset = (int) ((Math.sin(y_offset * 2 * Math.PI / y_period) + 1) / 2 * zoneWidth);
		}
		public boolean canMove(){
			y_offset += Config.y_step;
			calculate_x_offset();
			
			if (y_offset + y_offset_default > zoneHeight) {
				clover.dispose();
				return false;
			}
			
			return true;
		}
		public void move(){
			clover.setLocation(x + x_offset, y + y_offset + y_offset_default);
		}
	}
	@SuppressWarnings("serial")
	final class CloverPanel extends JPanel {
		private BufferedImage image;
		public CloverPanel() {
			try {
				File[] files = Picture.getCloverPicture();
				File file = files[Random.get().nextInt(files.length)];
				image = new BufferedImage(Config.size, Config.size, BufferedImage.TYPE_INT_ARGB);
				image.createGraphics().drawImage(ImageIO.read(file), 0, 0, image.getWidth(), image.getHeight(), null);
				setSize(image.getWidth(),image.getHeight());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void paint(Graphics g){
			g.drawImage(image, 0, 0, this);
		}
	}
}