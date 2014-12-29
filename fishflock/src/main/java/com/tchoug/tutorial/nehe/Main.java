package com.tchoug.tutorial.nehe;

import com.jogamp.opengl.util.Animator;

import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created 18-oct.-2014
 *
 * @author Tig
 */
public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame("NeHe Lesson 1");
		GLCanvas canvas = new GLCanvas();
		canvas.addGLEventListener(new Lesson1());
		frame.add(canvas);
		frame.setSize(640, 480);
		final Animator animator = new Animator(canvas);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
// Run this on another thread than the AWT event queue to
// make sure the call to Animator.stop() completes before
// exiting
				new Thread(new Runnable() {
					public void run() {
						animator.stop();
						System.exit(0);
					}
				}).start();
			}
		});
// Center frame
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		animator.start();
	}
}
