package net.teamfps.ld30.gfx.level.tile;

import net.teamfps.ld30.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public enum PortalType {
	START(Sprite.tile_spawn), END(Sprite.tile_portal);
	private Sprite sprite;

	private PortalType(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
}
