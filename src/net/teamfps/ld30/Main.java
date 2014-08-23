package net.teamfps.ld30;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import net.teamfps.ld30.gfx.Screen;

/**
 * @author Zekye
 *
 */
public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private static JFrame f = new JFrame("LD30 - Connected Worlds");
	private static int width = 640; // 1024
	private static int height = 480; // 720
	private Thread thread;
	private boolean isRunning;
	private int fps;
	private int ups;
	private Input input;
	private Camera camera;
	private Screen screen;

	private void init() {
		input = new Input();
		camera = new Camera(0, 0);
		screen = new Screen(camera, width, height);
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		addMouseWheelListener(input);
	}

	public void start() {
		if (isRunning) return;
		isRunning = true;
		thread = new Thread(this, "LD30 Main Thread!");
		thread.start();
	}

	public void stop() {
		if (!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int ups = 0;
		int fps = 0;
		init();
		requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				delta--;
				ups++;
				update();
			}
			fps++;
			render();
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				setFpsAndUps(fps, ups);
				ups = 0;
				fps = 0;
			}
		}
		stop();
	}

	/**
	 * @param fps
	 * @param ups
	 */
	private void setFpsAndUps(int fps, int ups) {
		this.fps = fps;
		this.ups = ups;
	}

	public String getFpsAndUps() {
		return "fps[" + fps + "], ups[" + ups + "]";
	}

	/**
	 * Updating...
	 */
	private void update() {
		screen.width = getWidth();
		screen.height = getHeight();
		screen.update();
	}

	/**
	 * Rendering...
	 */
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, getWidth(), getHeight());
		screen.render(g);
		screen.renderString(getFpsAndUps(), 12, 16, 16, 0xffff00, false);
		g.dispose();
		bs.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main m = new Main();
		f.add(m, "Center");
		f.pack();
		f.setSize(new Dimension(width, height));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setResizable(true);
		f.setVisible(true);
		m.start();
	}

}
