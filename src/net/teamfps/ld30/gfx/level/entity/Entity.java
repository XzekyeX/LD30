package net.teamfps.ld30.gfx.level.entity;

import java.util.Random;

import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.level.Level;
import net.teamfps.ld30.gfx.level.tile.SpikeTile;
import net.teamfps.ld30.gfx.level.tile.Tile;

/**
 * @author Zekye
 *
 */
public class Entity {
	public int x, y, w, h;
	public double hp;
	public String name;
	protected Level level;
	protected Random rand = new Random();
	private boolean remove = false;

	public void update() {

	}

	public void render(Screen screen) {

	}

	public boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + 64) / 64;
			int yt = ((y + ya + 32) + c / 2 * h + 32) / 64;
			if (level != null) {
				Tile t = level.getTile(xt - 1, yt - 1);
				if (t != null) {
					if (t.solid()) {
						solid = true;
					}
					if (t instanceof SpikeTile) {
						hp -= 0.02D;
						System.out.println("hp: " + hp);
					}
				}
			}
		}
		return solid;
	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	public void remove() {
		this.remove = true;
	}

	public boolean isRemoved() {
		return remove;
	}

	public void init(Level level) {
		this.level = level;
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
}
