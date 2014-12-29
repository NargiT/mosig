package com.tchoug.fishflock;

import javax.swing.SwingUtilities;

/**
 * Created 18-oct.-2014
 *
 * @author Tig
 */
public class Main {
	public static void main(String[] args) {
		// Run the GUI codes in the event-dispatching thread for thread safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Scene();  // run the constructor
			}
		});
	}
}
