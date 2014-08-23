package net.teamfps.ld30.menu;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.teamfps.ld30.gfx.Button;
import net.teamfps.ld30.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class WorldMenu extends Menu {
	private Button mm;
	private Button open;

	/**
	 * @param screen
	 */
	public WorldMenu(Screen screen) {
		super(screen);
		mm = new Button("Main Menu", 4, 26, 22, 0xffffff);
		open = new Button("Open Worlds Folder", 12, 26, 22, 0xffffff);
	}

	private int delay = 20;

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
	}

	@Override
	public void render() {
		int cx = screen.width / 2 - 128;
		mm.render(screen, cx + 64, 64, 128, 32);
		open.render(screen, cx, 64 + 48, 256, 32);
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

}
