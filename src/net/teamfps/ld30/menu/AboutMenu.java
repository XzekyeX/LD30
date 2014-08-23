package net.teamfps.ld30.menu;

import net.teamfps.ld30.gfx.Button;
import net.teamfps.ld30.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class AboutMenu extends Menu {
	private Button mm;

	/**
	 * @param screen
	 */
	public AboutMenu(Screen screen) {
		super(screen);
		mm = new Button("Main Menu", 4, 26, 22, 0xffffff);
	}

	private int delay = 20;

	@Override
	public void update() {
		if (delay > 0) delay--;
		if (mm.isPressedLeft() && delay == 0) {
			delay = 20;
			screen.setMenu(screen.getMainMenu());
		}
	}

	@Override
	public void render() {
		int cw = (screen.width / 2) - 64;
		mm.render(screen, cw, 64, 128, 32);
		screen.fillRect(64, 160, screen.width - 128, screen.height - 230, 3, 0x00c6ff, 0xffffff, false);
		String[] about = { "This game is made for ludum dare contest,", "in about 15 hours.", "Controls: ASDW And Space", "By Zekye." };
		for (int i = 0; i < about.length; i++) {
			screen.renderString(about[i], 20, 98, 200 + i * 20, 0xffffff, false);
		}
	}

}
