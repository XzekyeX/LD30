package net.teamfps.ld30.menu;

import net.teamfps.ld30.gfx.Button;
import net.teamfps.ld30.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class FailMenu extends Menu {
	private Button mm;
	private Button exit;

	public FailMenu(Screen screen) {
		super(screen);
		mm = new Button("Main Menu", 4, 26, 22, 0xffffff);
		exit = new Button("Exit", 26, 0xffffff);
	}

	private int delay = 20;

	@Override
	public void update() {
		if (delay > 0) delay--;
		if (mm.isPressedLeft() && delay == 0) {
			delay = 20;
			screen.setMenu(screen.getMainMenu());
		}
		if (exit.isPressedLeft()) {
			System.exit(1);
		}
	}

	@Override
	public void render() {
		String str = "You Fail!";
		int cw = (screen.width / 2) - 64;
		mm.render(screen, cw, 64, 128, 32);
		exit.render(screen, cw, 64 + 48, 128, 32);
		int fsize = 64;
		screen.renderString(str, fsize, cw - 64, screen.height / 2, 0xff0000, false);
	}

}
