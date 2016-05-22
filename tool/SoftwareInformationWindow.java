package tool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JWindow;

@SuppressWarnings("serial")
public final class SoftwareInformationWindow extends JPanel {
	private JWindow window;
	private BufferedImage image;
	private SoftwareInformationWindow() {
		initImage();
		initWindow();
	}
	private void initImage() {
		try {
			final BufferedImage chacracter = ImageIO.read(Picture.getCharacterPicture());
			final BufferedImage logo = ImageIO.read(Picture.getLogoPicture());
			final BufferedImage background = ImageIO.read(Picture.getBackgroundPicture());

			int x, y;
			int height = 500;
			int width = 265 + chacracter.getWidth();
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.createGraphics();
			g.setColor(Config.color);
			g.setFont(Config.font);

			x = 0;
			y = 0;
			g.drawImage(background, x, y, background.getWidth(), background.getHeight(), null);

			x = 0;
			y = 0;
			g.drawImage(logo, x, y, logo.getWidth(), logo.getHeight(), null);

			x = image.getWidth() - chacracter.getWidth();
			y = 0;
			g.drawImage(chacracter, x, y, chacracter.getWidth(), chacracter.getHeight(), null);

			FontMetrics fm = g.getFontMetrics();
			int charWidth;
			int charHeight = fm.getHeight();
			x = 0;
			y = (image.getHeight() - Config.information.length * charHeight) + fm.getAscent();
			for (int i = 0; i < Config.information.length; i++) {
				String str = Config.information[i];
				for (int index = 0; index < str.length(); index++) {
					char ch = str.charAt(index);
					charWidth = fm.charWidth(ch);
					g.drawString("" + ch, x, y);
					x += charWidth;
				}
				x = 0;
				y += charHeight;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	private final static SoftwareInformationWindow instance = new SoftwareInformationWindow();
	public static SoftwareInformationWindow getInstance() {
		return instance;
	}
}
