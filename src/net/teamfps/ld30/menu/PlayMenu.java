package net.teamfps.ld30.menu;

import java.util.ArrayList;
import java.util.List;

import net.teamfps.ld30.gfx.Screen;
import net.teamfps.ld30.gfx.Sprite;
import net.teamfps.ld30.gfx.level.Level;
import net.teamfps.ld30.gfx.level.World;

/**
 * @author Zekye
 *
 */
public class PlayMenu extends Menu {
	private Level level;
	private List<World> worlds = new ArrayList<World>();
	private int lvl = 0;

	/**
	 * @param screen
	 */
	public PlayMenu(Screen screen) {
		super(screen);
	}

	public PlayMenu init() {
		worlds.add(new World(screen, Sprite.world_0));
		worlds.add(new World(screen, Sprite.world_1));
		worlds.add(new World(screen, Sprite.world_2));
		return this;
	}

	public PlayMenu loadLevel() {
		if (worlds.size() >= 0 && lvl <= worlds.size()) {
			level = worlds.get(lvl);
			lvl += 1;
		}
		return this;
	}

	public World getWorld() {
		if (lvl < worlds.size()) {
			return worlds.get(lvl);
		}
		return null;
	}

	public void setWorld(World world) {
		level = world;
	}

	@Override
	public void update() {
		if (level != null) {
			level.update();
		}
	}

	@Override
	public void render() {
		if (level != null) {
			level.render();
		}
	}

}
