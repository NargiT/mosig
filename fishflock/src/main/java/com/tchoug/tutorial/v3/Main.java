package com.tchoug.tutorial.v3;

import javax.swing.*;

/**
 * Created 18-oct.-2014
 *
 * @author Tig
 */
public class Main {

	/** The entry main() method */
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new JOGL2Ex2Rotate3D_GLCanva_RendererMain();  // run the constructor
			}
		});
	}
}
