package com.tchoug.tutorial.v3;

import com.jogamp.opengl.util.FPSAnimator;

import javax.media.opengl.awt.GLCanvas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created 18-oct.-2014
 * @author Tig
 *
 * JOGL 2.0 Program Template (GLCanvas)
 * This is the top-level "Container", which allocates and add GLCanvas ("Component")
 * and animator.
 */
@SuppressWarnings("serial")
public class JOGL2Ex2Rotate3D_GLCanva_RendererMain extends JFrame {
	// Define constants for the top-level container
	private static String TITLE = "JOGL 2.0 Setup (GLCanvas)";  // window's title
	private static final int CANVAS_WIDTH = 640;  // width of the drawable
	private static final int CANVAS_HEIGHT = 480; // height of the drawable
	private static final int FPS = 60; // animator's target frames per second

	/** Constructor to setup the top-level container and animator */
	public JOGL2Ex2Rotate3D_GLCanva_RendererMain() {
		// Create the OpenGL rendering canvas
		GLCanvas canvas = new JOGL2Ex2Rotate3D_GLCanvas();
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