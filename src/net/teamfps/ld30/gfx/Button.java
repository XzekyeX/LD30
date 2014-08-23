package net.teamfps.ld30.gfx;

import net.teamfps.ld30.Input;

/**
 * @author Zekye
 *
 */
public class Button {
	public int x, y, w, h, tx, ty, fsize, fcolor;
	public String text;
	private boolean custom = false;

	public Button(String text, int fsize, int fcolor) {
		this.text = text;
		this.fsize = fsize;
		this.fcolor = fcolor;
		custom = false;
	}

	public Button(String text, int fsize, int fcolor, int x, int y, int w, int h) {
		this.text = text;
		this.fsize = fsize;
		this.fcolor = fcolor;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		custom = false;
	}

	public Button(String text, int tx, int ty, int fsize, int fcolor) {
		this.text = text;
		this.fsize = fsize;
		this.fcolor = fcolor;
		this.tx = tx;
		this.ty = ty;
		custom = true;
	}

	private int bgcolor = 0x00ccff;
	private int rcolor = 0xffffff;

	public void render(Screen screen, int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		screen.fillRect(x, y, w, h, 3, bgcolor, rcolor, false);
		if (!custom) {
			int cx = (w - text.length() * (fsize / 2)) / 2;
			int cy = fsize;
			screen.renderString(text, fsize, x + tx + cx, y + cy + ty, fcolor, false);
		} else {
			screen.renderString(text, fsize, x + tx, y + ty, fcolor, false);
		}
	}

	public boolean isPressedLeft() {
		boolean pressed = false;
		if (isMouseOver()) {
			if (Input.ML) {
				rcolor = 0xff0000;
				pressed = true;
			}
		} else {
			rcolor = 0xffffff;
		}
		return pressed;
	}

	public boolean isMouseOver() {
		boolean over = false;
		if (Input.MX >= x && Input.MX <= x + w && Input.MY >= y && Input.MY <= y + h) {
			bgcolor = 0x1492C0;
			over = true;
		} else {
			bgcolor = 0x00ccff;
		}
		return over;
	}

	public void setTextOffs(int tx, int ty) {
		this.tx = tx;
		this.ty = ty;
		custom = true;
	}
}
