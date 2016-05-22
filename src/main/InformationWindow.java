package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JWindow;

import tool.InformationImage;

@SuppressWarnings("serial")
public final class InformationWindow extends JPanel {
	private JWindow window;
	private BufferedImage image;
	private InformationWindow() {
		initImage();
		initWindow();
	}
	private void initImage() {
		image = InformationImage.getImage();
	}
	private void initWindow(){
		window = new JWindow();
		window.setSize(image.getWidth(), image.getHeight());
		window.setLocationRelativeTo(null);
		window.setLayout(new BorderLayout());
		window.setBackground(new Color(0, 0, 0, 0f));
		window.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					close();
			}
		});

		window.getContentPane().add(this, BorderLayout.CENTER);
	}
	public void display() {
		window.setVisible(true);
	}
	public void close() {
		window.setVisible(false);
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * -------------------µ¥ÀýÄ£Ê½--------------------------------
	 */
	private final static InformationWindow instance = new InformationWindow();
	public static InformationWindow getInstance() {
		return instance;
	}
}
