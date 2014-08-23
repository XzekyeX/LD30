package net.teamfps.ld30.gfx.level;

import java.util.List;

import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;
import net.teamfps.ld30.gfx.level.entity.Player;
import net.teamfps.ld30.gfx.level.tile.PortalType;
import net.teamfps.ld30.gfx.level.tile.SpikeTile;
import net.teamfps.ld30.gfx.level.tile.GroundTile;
import net.teamfps.ld30.gfx.level.tile.PortalTile;
import net.teamfps.ld30.gfx.level.tile.Tile;

/**
 * @author Zekye
 *
 */
public class World extends Level {
	/**
	 * @param screen
	 * @param world
	 */
	public World(Screen screen, Sprite world) {
		super(screen, world);
	}

	@Override
	protected void Load() {
		if (world != null) {
			int[] pixels = world.getPixels();
			width = world.getWidth();
			height = world.getHeight();
			tiles = new Tile[width][height];
			if (pixels != null) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						int col = pixels[x + y * width];
						// if (!(col == 0xffffffff)) tiles[x][y] = new Tile(col, x * 64, y * 64);
						if (col == 0xff606060) {
							tiles[x][y] = new GroundTile(x * 64, y * 64);
						}
						if (col == 0xffffff00) {
							tiles[x][y] = new SpikeTile(x * 64, y * 64);
						}
						if (col == 0xff0000ff) {
							tiles[x][y] = new PortalTile(PortalType.START, x * 64, y * 64);
						}
						if (col == 0xffff0000) {
							tiles[x][y] = new PortalTile(PortalType.END, x * 64, y * 64);
						}
					}
				}
			}
		}
		PortalTile start = getStartPortal();
		int x = 32;
		int y = 32;
		if (start != null) {
			x = start.getX();
			y = start.getY();
		}
		add(new Player("Player" + rand.nextInt(1000) + 100, x, y));
	}

	@Override
	protected void Generate() {

	}

}
