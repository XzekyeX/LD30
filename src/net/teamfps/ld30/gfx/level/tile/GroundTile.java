package net.teamfps.ld30.gfx.level.tile;

import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public class GroundTile extends Tile {

	public GroundTile(int x, int y) {
		super(Sprite.tile_ground, x, y);
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y, w, h, true);
	}

	@Override
	public boolean solid() {
		return true;
	}
}
