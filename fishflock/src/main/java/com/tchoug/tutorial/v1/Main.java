package com.tchoug.tutorial.v1;

import java.awt.*;
		import java.awt.event.*;
		import javax.swing.*;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;


/**
 * JOGL 2.0 Program Template (GLCanvas)
 * This is a "Component" which can be added into a top-level "Container".
 * It also handles the OpenGL events to render graphics.
 *
 * Created 17-oct.-2014
 * @author Tig
 */
@SuppressWarnings("serial")
public class Main  {
	// Define constants for the top-level container
	private static String TITLE = "JOGL 2.0 Setup (GLCanvas)";  // window's title
	private static final int CANVAS_WIDTH = 640;  // width of the drawable
	private static final int CANVAS_HEIGHT = 480; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second


	/** The entry main() method to setup the top-level container and animator */
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Create the OpenGL rendering canvas
				GLCanvas canvas = new JOGL2Setup_GLCanvas();
				canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

				// Create a animator that drives canvas' display() at the specified FPS.
				final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

				// Create the top-level container
				final Frame frame = new Frame(); // instead of Swing's JFrame
				frame.add(canvas);               // instead of Swing's frame.setContentPane()
//				final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
//				frame.getContentPane().add(canvas);
				frame.addWindowListener(new WindowAdapter() {
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
				frame.setTitle(TITLE);
				frame.pack();
				frame.setVisible(true);
				animator.start(); // start the animation loop
			}
		});
	}
}