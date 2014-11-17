package net.teamfps.ld30;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @author Zekye
 *
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static boolean[] keys = new boolean[65536];
	public static boolean ML = false;
	public static boolean MC = false;
	public static boolean MR = false;
	public static int MX = -1;
	public static int MY = -1;
	public static int SCROLL = 0;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		SCROLL = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MX = e.getX();
		MY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int b = e.getButton();
		if (b == MouseEvent.BUTTON1) {
			ML = true;
		}
		if (b == MouseEvent.BUTTON2) {
			MC = true;
		}
		if (b == MouseEvent.BUTTON3) {
			MR = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int b = e.getButton();
		if (b == MouseEvent.BUTTON1) {
			ML = false;
		}
		if (b == MouseEvent.BUTTON2) {
			MC = false;
		}
		if (b == MouseEvent.BUTTON3) {
			MR = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static boolean equalsKey(int key) {
		return keys[key];
	}
}
