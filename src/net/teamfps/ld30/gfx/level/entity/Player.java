package net.teamfps.ld30.gfx.level.entity;

import java.awt.event.KeyEvent;

import net.teamfps.ld30.Input;
import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;
import net.teamfps.ld30.gfx.level.World;
import net.teamfps.ld30.gfx.level.tile.PortalTile;
import net.teamfps.ld30.gfx.level.tile.PortalType;
import net.teamfps.ld30.gfx.level.tile.Tile;
import net.teamfps.ld30.menu.LoadMenu;
import net.teamfps.ld30.menu.PlayMenu;

/**
 * @author Zekye
 *
 */
public class Player extends Entity {
	private int speed = 2;

	public Player(String name, int x, int y) {
		this.x = x;
		this.y = y;
		this.w = 32;
		this.h = 32;
		this.name = name;
		this.hp = 20;
	}

	private boolean air = false;
	private boolean jumping = false;
	private int timer = 0;

	private Tile under = null;

	@Override
	public void update() {
		int xa = 0;
		int ya = 0;
		if (level != null) {
			under = level.getUnderTile(this);
			if (under instanceof PortalTile) {
				PortalTile pt = (PortalTile) under;
				if (pt.getType() == PortalType.END) {
					remove();
					Screen screen = level.screen;
					if (screen != null) {
						PlayMenu pm = (PlayMenu) screen.getPlayMenu();
						if (pm != null) {
							World w = pm.getWorld();
							if (w != null) {
								screen.setMenu(new LoadMenu(screen, w));
							}
						}
					}
				}
			}
			if (hp <= 0) {
				Screen screen = level.screen;
				if (screen != null) {
					screen.setMenu(screen.getFailMenu());
				}
			}
		}
		if (hp <= 0) {
			remove();
		}
		if (Input.equalsKey(KeyEvent.VK_A)) {
			xa -= speed;
		}
		if (Input.equalsKey(KeyEvent.VK_D)) {
			xa += speed;
		}
		if (Input.equalsKey(KeyEvent.VK_SPACE) && !air && !jumping) {
			jumping = true;
		}
		if (jumping) {
			ya -= 8;
			timer++;
			if (timer >= 22) {
				timer = 0;
				jumping = false;
			}
		} else {
			timer = 0;
		}
		if (under == null) {
			air = true;
		} else {
			air = false;
		}
		ya += 2;
		move(xa, ya);
	}

	@Override
	public void render(Screen screen) {
		screen.renderSprite(Sprite.player, x, y, w, h, true);
		String s = String.format("%.1f", hp);
		screen.renderString("" + s, 16, x, y - 4, 0xffff00, true);
	}
}
