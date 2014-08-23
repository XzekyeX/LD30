package net.teamfps.ld30.gfx.level.tile;

import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public class SpikeTile extends Tile {

	public SpikeTile(int x, int y) {
		super(Sprite.tile_spike, x, y);
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y, w, h, true);
	}

	@Override
	public boolean solid() {
		return false;
	}
}
