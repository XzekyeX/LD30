package net.teamfps.ld30.gfx.level.tile;

import net.teamfps.ld30.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class PortalTile extends Tile {
	private PortalType type;

	public PortalTile(PortalType type, int x, int y) {
		super(type.getSprite(), x, y);
		this.type = type;
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y, w, h, true);
	}

	@Override
	public boolean solid() {
		return false;
	}

	public PortalType getType() {
		return type;
	}

}
