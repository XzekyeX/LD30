package net.teamfps.ld30.gfx.level;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.teamfps.ld30.Input;
import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;
import net.teamfps.ld30.gfx.level.entity.Entity;
import net.teamfps.ld30.gfx.level.entity.Player;
import net.teamfps.ld30.gfx.level.tile.PortalTile;
import net.teamfps.ld30.gfx.level.tile.PortalType;
import net.teamfps.ld30.gfx.level.tile.SpikeTile;
import net.teamfps.ld30.gfx.level.tile.Tile;

/**
 * @author Zekye
 *
 */
public class Level {

	public Screen screen;
	protected final Random rand = new Random();
	protected int width;
	protected int height;
	protected Sprite world;
	protected Tile[][] tiles;

	protected List<Player> players = new ArrayList<Player>();
	protected List<Entity> entities = new ArrayList<Entity>();
	
	public Level(Screen screen, int width, int height) {
		this.screen = screen;
		this.width = width;
		this.height = height;
	}

	public Level(Screen screen, Sprite world) {
		this.screen = screen;
		this.world = world;
		Load();
	}

	public void add(Entity e) {
		e.init(this);
		if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	protected void Load() {

	}

	protected void Generate() {

	}

	private int delay = 0;

	public void update() {
		if (delay > 0) delay--;
		if (Input.equalsKey(KeyEvent.VK_G) && delay == 0) {
			delay = 20;
			Generate();
		}
		if (Input.equalsKey(KeyEvent.VK_L) && delay == 0) {
			delay = 20;
			Load();
		}
		for (int i = 0; i < getEntities().size(); i++) {
			getEntities().get(i).update();
			if (getEntities().get(i).isRemoved()) {
				getEntities().remove(i);
			}
		}
		for (int i = 0; i < getPlayers().size(); i++) {
			getPlayers().get(i).update();
			if (getPlayers().get(i).isRemoved()) {
				getPlayers().remove(i);
			}
		}

		Player client = getClientPlayer();
		if (client != null) {
			int px = client.getX();
			int py = client.getY();
			int cx = screen.width / 2;
			int cy = screen.height / 2;
			screen.getCamera().setX(px - cx);
			screen.getCamera().setY(py - cy);
		}
	}

	public void render() {
		int cx = screen.getCamera().getX();
		int cy = screen.getCamera().getY();
		int cw = screen.width;
		int ch = screen.height;
		if (tiles != null) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (tiles[x][y] != null) {
						if (x * 64 >= cx - 64 && x * 64 <= cx + cw && y * 64 >= cy - 64 && y * 64 <= cy + ch) {
							tiles[x][y].render(screen);
						}
					}
				}
			}
		}
		for (int i = 0; i < getEntities().size(); i++) {
			getEntities().get(i).render(screen);
		}
		for (int i = 0; i < getPlayers().size(); i++) {
			getPlayers().get(i).render(screen);
		}
	}

	public PortalTile getStartPortal() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tiles[x][y] != null) {
					if (tiles[x][y] instanceof PortalTile) {
						PortalTile pt = (PortalTile) tiles[x][y];
						if (pt.getType() == PortalType.START) {
							return pt;
						}
					}
				}
			}
		}
		return null;
	}

	public PortalTile getEndPortal() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tiles[x][y] != null) {
					if (tiles[x][y] instanceof PortalTile) {
						PortalTile pt = (PortalTile) tiles[x][y];
						if (pt.getType() == PortalType.END) {
							return pt;
						}
					}
				}
			}
		}
		return null;
	}

	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	public Tile getUnderTile(Entity e) {
		int x = e.getX();
		int y = e.getY();
		int w = e.getW();
		int h = e.getH();
		int xa = 0;
		int ya = 0;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * w + 64) / 64;
			int yt = ((y + ya + 4) + c / 2 * h + 64) / 64;
			Tile t = getTile(xt - 1, yt - 1);
			if (t != null && !(t instanceof SpikeTile)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the entities
	 */
	public List<Entity> getEntities() {
		return entities;
	}

	public Player getClientPlayer() {
		if (getPlayers().size() > 0) {
			return getPlayers().get(0);
		}
		return null;
	}
}
