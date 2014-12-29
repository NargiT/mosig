package client.run.gui;

import client.controller.Controller;
import client.run.console.StartConsoleClient;
import client.view.Gui;

/**Main
 * 
 * Class starts the GUI-Client.
 * Instantiates the gui, and then the controller and makes the GUI visible to the user.
 *
 */
public class StartGuiClient {

	public static void main(String[] args) {
		System.setProperty("java.rmi.server.codebase",
                StartConsoleClient.class.getProtectionDomain().getCodeSource().getLocation().toString());
		Gui gui = new Gui();
		Controller controller = new Controller(gui);
		controller.run();
	}

}
