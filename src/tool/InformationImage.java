package tool;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class InformationImage {
	
	public static BufferedImage getImage(){
		try {
			final BufferedImage chacracter = ImageIO.read(Picture.getCharacterPicture());
			final BufferedImage logo = ImageIO.read(Picture.getLogoPicture());
			final BufferedImage background = ImageIO.read(Picture.getBackgroundPicture());

			int x, y;
			int height = Config.INFORMATION_IMAGE_HEIGHT;
			int width = Config.INFORMATION_IMAGE_WIDTH + chacracter.getWidth();
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.createGraphics();
			g.setColor(Config.COLOR);
			g.setFont(Config.FONT);

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
			final String[] information = Config.INFORMATION;
			x = 0;
			y = (image.getHeight() - information.length * charHeight) + fm.getAscent();
			for (int i = 0; i < information.length; i++) {
				String str = information[i];
				for (int index = 0; index < str.length(); index++) {
					char ch = str.charAt(index);
					charWidth = fm.charWidth(ch);
					g.drawString("" + ch, x, y);
					x += charWidth;
				}
				x = 0;
				y += charHeight;
			}
			return image;
		} catch (IOException e) {
			return null;
		}
	}
	
}
