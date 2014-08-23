package net.teamfps.ld30.gfx.level.tile;

import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;

/**
 * @author Zekye
 *
 */
public class Tile {
	public int x, y, w, h;
	public Sprite sprite;
	private int color = 0x606060;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.h = 64;
		this.w = 64;
	}

	public Tile(int color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.h = 64;
		this.w = 64;
	}

	public Tile(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.h = 64;
		this.w = 64;
	}

	public void render(Screen screen) {
		screen.fillRect(x, y, w, h, color, true);
	}

	@Override
	public String toString() {
		return "Pos[" + x + "][" + y + "], Sprite[" + sprite + "]";
	}

	public boolean solid() {
		return false;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the w
	 */
	public int getW() {
		return w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @return the sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}
}
