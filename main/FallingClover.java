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
import tool.ScreenSize;
import tool.SoftwareInformationWindow;

public final class FallingClover {
	private PopupMenu menu;
	private FallingCloverPart[] clovers;

	private FallingClover() {
		initPopupMenu();
		createTaskIcon();
		initClovers();
	}
	private void initClovers(){
		clovers = new FallingCloverPart[Config.number];
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

		menu.addSeparator();
		MenuItem about = new MenuItem("关于");
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoftwareInformationWindow.getInstance().display();
			}
		});
		menu.add(about);
		
		menu.addSeparator();
		MenuItem exit = new MenuItem("关闭");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(exit);
	}
	private void move(int index){
		if(clovers[index].canMove()){
			clovers[index].move();
		}else{
			clovers[index] = new FallingCloverPart();
			clovers[index].display();
		}
	}
	private void sleep(long m){
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void startMove() {
		final int count = ScreenSize.getHeight() / Config.number / Config.y_step;
		for (int i = 0; i < clovers.length; i++) {
			clovers[i] = new FallingCloverPart();
			clovers[i].display();
			
			for (int c = 0; c < count; c++) {
				long start = System.currentTimeMillis();
				for (int j = 0; j < i + 1; j++) {
					move(j);
				}
				long end = System.currentTimeMillis();
				sleep((end - start) * Config.number / (i + 1));
			}
			
			sleep(Config.sleep);
		}
		
		while (true) {
			
			for (int i = 0; i < clovers.length; i++) {
				move(i);
			}

			sleep(Config.sleep);
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
