package net.teamfps.ld30.menu;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.teamfps.ld30.Input;
import net.teamfps.ld30.gfx.Button;
import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public class WorldMenu extends Menu {
	private Button mm;
	private Button open;
	private Slot[] slots;

	/**
	 * @param screen
	 */
	public WorldMenu(Screen screen) {
		super(screen);
		mm = new Button("Main Menu", 4, 26, 22, 0xffffff);
		open = new Button("Open Worlds Folder", 12, 26, 22, 0xffffff);
	}

	private int delay = 20;

	public WorldMenu init() {
		List<Sprite> sprites = Sprite.loadFolder("/worlds/");
		if (sprites != null) {
			int size = sprites.size();
			slots = new Slot[size];
			for (int i = 0; i < slots.length; i++) {
				slots[i] = new Slot(sprites.get(i));
			}
			System.out.println("size: " + size);
		}
		return this;
	}

	private int scrollspeed = 10;
	private int sx = 32;
	private int sw = 64 * 7;

	@Override
	public void update() {
		if (delay > 0) delay--;
		if (mm.isPressedLeft() && delay == 0) {
			delay = 20;
			screen.setMenu(screen.getMainMenu());
		}
		if (open.isPressedLeft() && delay == 0) {
			delay = 20;
			openFolder();
		}

		if (Input.SCROLL == 1) {
			Input.SCROLL = 0;
			sx -= scrollspeed;
		}
		if (Input.SCROLL == -1) {
			Input.SCROLL = 0;
			sx += scrollspeed;
		}
		int size = slots.length;
		int max = size * 74 + 32;
		if (-sx > max - sw) {
			sx = -max + sw;
		}
		if (sx > 32) {
			sx = 32;
		}
		if (slots != null) {
			for (int i = 0; i < slots.length; i++) {
				if (slots[i].isPressedLeft()) {
					int x = sx + i * 74;
					if (!(x >= sw || x <= 28)) {

					}
				}
			}
		}
	}

	@Override
	public void render() {
		int cx = screen.width / 2 - 128;
		int bot = screen.height - 64;
		mm.render(screen, cx + 64, 64, 128, 32);
		open.render(screen, cx, bot, 256, 32);
		if (slots != null) {
			for (int i = 0; i < slots.length; i++) {
				int x = sx + i * 74;
				if (!(x >= sw || x <= 28)) {
					slots[i].render(32 + x, 160);
				}
			}
		}
	}

	public void openFolder() {
		String appdata = System.getenv("APPDATA");
		String path = appdata + "/.teamfps/worlds/";
		File f = new File(path);
		if (!f.mkdirs()) {
			try {
				File ft = new File(f, "README.txt");
				if (!ft.exists()) {
					BufferedWriter bw = new BufferedWriter(new FileWriter(ft));
					String[] s = { "How To Make Custom Worlds!", "You Will Need: Photoshop or something you can be drawn", "One Pixel = One Tile", "--Tile Colour Codes--", "GroundTile(HEX: 0xff606060, DEC:  4284506208, OCT: 37730060140)", "PortalTile.START(HEX: 0xff0000ff, DEC: 4278190335, OCT: 37700000377)", "PortalTile.END(HEX: 0xffff0000, DEC: 4294901760, OCT: 37777600000)", "SpikeTile(HEX: 0xffffff00, DEC: 4294967040, OCT: 37777777400)" };
					for (int i = 0; i < s.length; i++) {
						System.out.println("Write: " + s[i]);
						bw.write(s[i]);
						bw.newLine();
					}
					bw.close();
				}
				Desktop.getDesktop().open(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			openFolder();
		}
	}

	class Slot {
		private int x, y, w, h;
		private Sprite sprite;
		private int bgcolor = 0x00ccff;
		private int rcolor = 0xffffff;

		public Slot(Sprite sprite) {
			this.sprite = sprite;
			this.w = 64;
			this.h = 64;
		}

		public void render(int x, int y) {
			this.x = x;
			this.y = y;
			screen.fillRect(x, y, w, h, 3, bgcolor, rcolor, false);
			if (getSprite() != null) {
				screen.renderSprite(getSprite(), x + 3, y + 3, w - 3, h - 3, false);
			}
		}

		public boolean isPressedLeft() {
			boolean pressed = false;
			if (isMouseOver()) {
				if (Input.ML) {
					pressed = true;
				}
			} else {

			}
			return pressed;
		}

		public boolean isMouseOver() {
			boolean over = false;
			if (Input.MX >= x && Input.MX <= x + w && Input.MY >= y && Input.MY <= y + h) {
				rcolor = 0xff0000;
				over = true;
			} else {
				rcolor = 0xffffff;
			}
			return over;
		}

		/**
		 * @return the sprite
		 */
		public Sprite getSprite() {
			return sprite;
		}
	}

}
