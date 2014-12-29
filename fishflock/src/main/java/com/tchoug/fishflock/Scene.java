package com.tchoug.fishflock;

import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created 18-oct.-2014
 *
 * @author Tig
 */
public class Scene extends JFrame {

	// Define constants for the top-level container
	private static String TITLE = "Fish Flock Simulation";  // window's title
	private static final int CANVAS_WIDTH = 640;  // width of the drawable
	private static final int CANVAS_HEIGHT = 480; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second

	/** Constructor to setup the top-level container and animator */
	public Scene() {
		// Create the OpenGL rendering canvas
		GLCanvas canvas = new Stuff();
		canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

		// Create a animator that drives canvas' display() at the specified FPS.
		final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

		// Create the top-level container frame
		this.getContentPane().add(canvas);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Use a dedicate thread to run the stop() to ensure that the
				// animator stops before program exits.
				new Thread() {
					@Override
					public void run() {
						if (animator.isStarted()) animator.stop();
						System.exit(0);
					}
				}.start();
			}
		});
		this.setTitle(TITLE);
		this.pack();
		this.setVisible(true);
		animator.start(); // start the animation loop
	}
}
