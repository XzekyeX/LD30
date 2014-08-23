package net.teamfps.ld30.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import net.teamfps.ld30.Camera;

/**
 * @author Zekye
 *
 */
public class Bitmap {
	public int width;
	public int height;
	private Graphics2D g;
	private Camera camera;
	private int defaultColor = 0xffffff;

	/**
	 * @param width
	 * @param height
	 */
	public Bitmap(Camera camera, int width, int height) {
		this.camera = camera;
		this.width = width;
		this.height = height;
	}

	/**
	 * @param graphics
	 */
	public void initGFX(Graphics2D g) {
		this.g = g;
	}

	public void fillRect(int x, int y, int w, int h, Color color, boolean offset) {
		if (offset) {
			x -= camera.getX();
			y -= camera.getY();
		}
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(new Color(defaultColor));
	}

	public void fillRect(int x, int y, int w, int h, int color, boolean offset) {
		if (offset) {
			x -= camera.getX();
			y -= camera.getY();
		}
		g.setColor(new Color(color));
		g.fillRect(x, y, w, h);
		g.setColor(new Color(defaultColor));
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param b
	 * @param BGColor
	 * @param rectColor
	 * @param offset
	 */
	public void fillRect(int x, int y, int w, int h, int b, int bgcolor, int rcolor, boolean offset) {
		fillRect(x, y, w, h, bgcolor, offset);
		drawRect(x, y, w, h, b, rcolor, offset);
	}
	
	public void fillRect(int x, int y, int w, int h, int b, Color bgcolor, Color rcolor, boolean offset) {
		fillRect(x, y, w, h, bgcolor, offset);
		drawRect(x, y, w, h, b, rcolor, offset);
	}

	public void drawRect(int x, int y, int w, int h, int b, int color, boolean offset) {
		fillRect(x, y, w, b, color, offset);
		fillRect(x, y, b, h, color, offset);
		fillRect(x + w, y, b, h, color, offset);
		fillRect(x, y + h, w + b, b, color, offset);
	}

	public void drawRect(int x, int y, int w, int h, int b, Color color, boolean offset) {
		fillRect(x, y, w, b, color, offset);
		fillRect(x, y, b, h, color, offset);
		fillRect(x + w, y, b, h, color, offset);
		fillRect(x, y + h, w + b, b, color, offset);
	}

	public void drawCircle(int x, int y, int w, int h, int color, boolean offset) {
		if (offset) {
			x -= camera.getX();
			y -= camera.getY();
		}
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, w, h);
		g.setColor(new Color(color));
		g.fill(circle);
		g.setColor(new Color(defaultColor));
	}

	public void drawCircle(int x, int y, int w, int h, Color color, boolean offset) {
		if (offset) {
			x -= camera.getX();
			y -= camera.getY();
		}
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, w, h);
		g.setColor(color);
		g.fill(circle);
		g.setColor(new Color(defaultColor));
	}

	public void renderSprite(Sprite sprite, int x, int y, int w, int h, boolean offset) {
		if (offset) {
			x -= camera.getX();
			y -= camera.getY();
		}
		if (sprite != null) {
			g.drawImage(sprite.getImage(), x, y, w, h, null);
		} else {
			System.out.println("Sprite = null!");
		}
	}

	public void renderString(String str, int fsize, int x, int y, int color, boolean offset) {
		if (offset) {
			x -= camera.getX();
			y -= camera.getY();
		}
		g.setColor(new Color(color));
		g.setFont(new Font("Tahoma", 1, fsize));
		g.drawString(str, x, y);
		g.setColor(new Color(defaultColor));
	}

	/**
	 * @return the camera
	 */
	public Camera getCamera() {
		return camera;
	}
}
