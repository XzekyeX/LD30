package net.teamfps.ld30.gfx;

import java.awt.Graphics;
import java.awt.Graphics2D;
import net.teamfps.ld30.Camera;
import net.teamfps.ld30.menu.AboutMenu;
import net.teamfps.ld30.menu.FailMenu;
import net.teamfps.ld30.menu.MainMenu;
import net.teamfps.ld30.menu.Menu;
import net.teamfps.ld30.menu.PlayMenu;

/**
 * @author Zekye
 *
 */
public class Screen extends Bitmap {
	private Menu menu;
	private Menu playmenu;
	private Menu mainmenu;
	private Menu failmenu;
	private Menu aboutmenu;

	/**
	 * @param width
	 * @param height
	 */
	public Screen(Camera camera, int width, int height) {
		super(camera, width, height);
		mainmenu = new MainMenu(this);
		playmenu = new PlayMenu(this);
		failmenu = new FailMenu(this);
		aboutmenu = new AboutMenu(this);
		menu = mainmenu;
	}

	public void update() {
		if (menu != null) {
			menu.update();
		}
	}

	public void render(Graphics g) {
		initGFX((Graphics2D) g);
		renderSprite(Sprite.bg, 0, 0, width, height, false);
		if (menu != null) {
			menu.render();
		}
	}

	/**
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @return the playmenu
	 */
	public Menu getPlayMenu() {
		return playmenu;
	}

	/**
	 * @return the mainmenu
	 */
	public Menu getMainMenu() {
		return mainmenu;
	}

	/**
	 * @return the failmenu
	 */
	public Menu getFailMenu() {
		return failmenu;
	}

	/**
	 * @return the aboutmenu
	 */
	public Menu getAboutMenu() {
		return aboutmenu;
	}
}
