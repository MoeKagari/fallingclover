package tool;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public final class Picture {

	public static File getTrayIconPicture() {
		return new File("resource\\trayicon.png");
	}

	public static File getBackgroundPicture() {
		return new File("resource\\background.jpg");
	}

	public static File[] getCloverPicture() {
		return new File[] {
				new File("resource\\clover1.png"),
				new File("resource\\clover2.png"),
				new File("resource\\clover3.png"),
				new File("resource\\clover4.png")
		};
	}

	public static File getCharacterPicture() {
		return new File("resource\\hanako.png");
	}

	public static File getLogoPicture() {
		return new File("resource\\logo.png");
	}

	public void deleteBlock(File file, File newFile) {
		try {
			BufferedImage image = ImageIO.read(file);
			int width = image.getWidth();
			int height = image.getHeight();

			ArrayList<int[]> res = new ArrayList<>();
			for (int i = 0; i < width; i++) {
				int[] temp = new int[height];
				boolean del = true;
				for (int j = 0; j < height; j++) {
					int rgb = image.getRGB(i, j);
					if ((rgb >> 24) != 0) {
						del = false;
					}
					temp[j] = rgb;
				}
				if (!del)
					res.add(temp);
			}

			ArrayList<int[]> output = new ArrayList<>();
			for (int j = 0; j < height; j++) {
				int[] temp = new int[res.size()];
				boolean del = true;
				for (int i = 0; i < res.size(); i++) {
					int rgb = res.get(i)[j];
					if ((rgb >> 24) != 0) {
						del = false;
					}
					temp[i] = rgb;
				}
				if (!del)
					output.add(temp);
			}

			int[] rgb = new int[res.size() * output.size()];
			for (int i = 0; i < output.size(); i++) {
				for (int j = 0; j < res.size(); j++) {
					rgb[j + i * res.size()] = output.get(i)[j];
				}
			}

			BufferedImage bf = new BufferedImage(res.size(), output.size(), BufferedImage.TYPE_INT_ARGB);
			bf.setRGB(0, 0, res.size(), output.size(), rgb, 0, res.size());
			ImageIO.write(bf, "png", newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
