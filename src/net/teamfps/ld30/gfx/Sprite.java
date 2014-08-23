package net.teamfps.ld30.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Zekye
 *
 */
public class Sprite {
	private int width;
	private int height;
	private String path;
	private BufferedImage img;
	private int[] pixels;

	public static Sprite bg = new Sprite("/bg.png");

	public static Sprite player = new Sprite("/player.png");

	public static Sprite world_0 = new Sprite("/world_0.png");
	public static Sprite world_1 = new Sprite("/world_1.png");
	public static Sprite world_2 = new Sprite("/world_2.png");

	public static Sprite tile_spawn = new Sprite("/tile_spawn.png");
	public static Sprite tile_portal = new Sprite("/tile_portal.png");
	public static Sprite tile_ground = new Sprite("/tile_ground.png");
	public static Sprite tile_spike = new Sprite("/tile_spike.png");

	public Sprite(String path) {
		this.path = path;
		load();
	}

	private void load() {
		try {
			img = ImageIO.read(getClass().getResource(path));
			width = img.getWidth();
			height = img.getHeight();
			pixels = new int[width * height];
			img.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return image
	 */
	public BufferedImage getImage() {
		return img;
	}

	/**
	 * @return the pixels
	 */
	public int[] getPixels() {
		return pixels;
	}
}
