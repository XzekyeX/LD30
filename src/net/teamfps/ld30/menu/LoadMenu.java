package net.teamfps.ld30.menu;

import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.level.World;

/**
 * @author Zekye
 *
 */
public class LoadMenu extends Menu {
	private World world;
	private int timer = 0;

	public LoadMenu(Screen screen, World world) {
		super(screen);
		this.world = world;
	}

	@Override
	public void update() {
		timer++;
		if (timer >= 256) {
			timer = 0;
			PlayMenu pm = (PlayMenu) screen.getPlayMenu();
			pm.setWorld(world);
			screen.setMenu(pm);
		}
	}

	@Override
	public void render() {
		String str = "Loading next World!";
		int fsize = 16;
		int x = ((screen.width / 2) - (str.length() * (fsize / 2))) + 32;
		screen.renderString(str, fsize, x, screen.height / 2, 0xffff00, false);
	}

}
