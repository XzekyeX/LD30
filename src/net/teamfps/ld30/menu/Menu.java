package net.teamfps.ld30.menu;

import net.teamfps.ld30.gfx.Screen;

/**
 * @author Zekye
 *
 */
public abstract class Menu {
	public Screen screen;

	public Menu(Screen screen) {
		this.screen = screen;
	}

	public abstract void update();

	public abstract void render();
}
