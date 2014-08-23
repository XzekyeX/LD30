package net.teamfps.ld30.menu;

import net.teamfps.ld30.gfx.Button;
import net.teamfps.ld30.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class MainMenu extends Menu {
	private Button play;
	private Button about;
	private Button exit;
	private Button custom;

	public MainMenu(Screen screen) {
		super(screen);
		play = new Button("Play", 26, 0xffffff);
		about = new Button("About", 26, 0xffffff);
		custom = new Button("Worlds", 26, 0xffffff);
		exit = new Button("Exit", 26, 0xffffff);
	}

	private int delay = 20;

	private void load() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				long lastTime = System.currentTimeMillis();
				PlayMenu pm = ((PlayMenu) screen.getPlayMenu()).init().loadLevel();
				screen.setMenu(pm);
				long now = System.currentTimeMillis();
				long math = now - lastTime;
				System.out.println("Loaded In: " + math + "ms");
			}
		};
		thread.start();
	}

	@Override
	public void update() {
		if (delay > 0) delay--;
		if (play.isPressedLeft() && delay == 0) {
			delay = 20;
			load();
		}
		if (about.isPressedLeft() && delay == 0) {
			delay = 20;
			screen.setMenu(screen.getAboutMenu());
		}
		if (custom.isPressedLeft() && delay == 0) {
			delay = 20;
			screen.setMenu(screen.getWorldMenu());
		}
		if (exit.isPressedLeft()) {
			System.exit(1);
		}
	}

	@Override
	public void render() {
		int cw = screen.width / 2 - 64;
		play.render(screen, cw, 64, 128, 32);
		custom.render(screen, cw, 64 + (48 * 1), 128, 32);
		about.render(screen, cw, 64 + (48 * 2), 128, 32);
		exit.render(screen, cw, 64 + (48 * 3), 128, 32);
	}

}
