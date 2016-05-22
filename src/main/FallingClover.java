package main;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tool.Config;
import tool.Picture;
import tool.Sleep;

public final class FallingClover {
	private PopupMenu menu;
	private FallingClover() {
		initPopupMenu();
		createTaskIcon();
	}
	private void createTaskIcon() {
		try {
			BufferedImage image = ImageIO.read(Picture.getTrayIconPicture());
			TrayIcon ti = new TrayIcon(image, "飘落的四叶草");
			ti.setImageAutoSize(true);
			ti.setPopupMenu(menu);

			SystemTray tray = SystemTray.getSystemTray();
			tray.add(ti);
		} catch (IOException | AWTException e) {
			e.printStackTrace();
		}
	}
	private void initPopupMenu() {
		menu = new PopupMenu();

		MenuItem alwaysOnTop = new MenuItem("总在最上");
		alwaysOnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		menu.add(alwaysOnTop);

		MenuItem about = new MenuItem("关于");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformationWindow.getInstance().display();
			}
		});
		menu.add(about);
		
		MenuItem exit = new MenuItem("关闭");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(exit);
	}
	public void startMove() {
		while(true){
			new Thread(new FallingCloverPart()).start();
			Sleep.sleep(Config.NEW_CLOVER_WAIT_TIME);
		}
	}
	/*
	 * 
	 * 
	 * 
	 * 
	 * -------------------单例模式--------------------------------
	 */
	private final static FallingClover instance = new FallingClover();
	public final static FallingClover getInstance() {
		return instance;
	}
}
