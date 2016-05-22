package main;

import javax.swing.JWindow;

import tool.Config;
import tool.Random;
import tool.ScreenSize;

public final class Mover {
	private int screenWidth, screenHeight, zoneWidth, zoneHeight;
	private int x, y, y_period, y_offset_default, y_offset, x_offset;

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
	private void calculate_x_offset() {
		x_offset = (int) ((Math.sin(y_offset * 2 * Math.PI / y_period) + 1) / 2 * zoneWidth);
	}
	public boolean canMove() {
		y_offset += Config.Y_STEP;
		calculate_x_offset();

		if (y_offset + y_offset_default > zoneHeight) {
			clover.dispose();
			return false;
		}

		return true;
	}
	public void move() {
		clover.setLocation(x + x_offset, y + y_offset + y_offset_default);
	}
}