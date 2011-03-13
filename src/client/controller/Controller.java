package client.controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import utils.Message;

import client.model.Client;
import client.model.interfaces.ClientLocal;

import client.view.Gui;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * Controller Class communicates between client model and the graphical user interface (view).
 * Handles events occuring in the gui and calls the appropriate methods in the client.
 * The method displayMessage() is also by the client to communicate with the gui.
 *
 */
public class Controller implements ActionListener, KeyListener {

    private Gui gui;
    private ClientLocal client;

    /**
     * Constructor the controller.
     * Makes actionlisteners, keylisteners and windowslisteners of the components of the graphical userinterface refer to this class.
     * ActionListeners react to the buttons clicked in the gui, windowslistener reacts when the user closes the window (correctly unregisters the user if he is still connected), and the keylistener reacts when typing a new message in the edit-field. 
     * @param gui
     */
    public Controller(Gui gui) {
        this.client = null;
        this.gui = gui;

        gui.m_connect.addActionListener(this);
        gui.m_disconnect.addActionListener(this);
        gui.m_history.addActionListener(this);
        gui.m_exit.addActionListener(this);
        gui.tf_chat.addKeyListener(this);

        WindowListener wl = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                if (client != null && client.isConnected()) {
                    client.unregister();
                }
                System.exit(0);
            }
        };
        gui.addWindowListener(wl);
    }

    /**
     * Makes the GUI visible to the user
     */
    public void run() {
        gui.startGUI();
    }

    /**
     * Handles actions occurring in the gui.
     * Differentiates between the components and performs the required action for this component.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
    	
        if (ae.getSource().equals(gui.m_exit)) {
            if (client != null) {
                if (client.isConnected()) {
                    client.unregister();
                }
            }
            System.exit(0);
        } else if (ae.getSource().equals(gui.m_connect)) {
            if (client == null || !client.isConnected()) {
                try {
                    String username = "";
                    while (username.isEmpty()) {
                        username = gui.show_ConnectionDialog();
                        if (username == null) {
                            break;
                        }
                    }
                    if (username != null) {
                        client = new Client(username, "GUI", this);
                        client.register();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gui.tf_chat.setEnabled(true);
            } else {
                gui.throwErrorMessage("Client already connected");
            }
        } else if (ae.getSource().equals(gui.m_disconnect)) {
            if (!(client == null)) {
                if (client.isConnected()) {
                    client.unregister();
                    gui.tf_chat.setEnabled(false);
                } else {
                    gui.throwErrorMessage("Client not connected");
                }
            } else {
                gui.throwErrorMessage("Client not connected");
            }
        } else if (ae.getSource().equals(gui.m_history)) {
            if (!(client == null)) {
                if (client.isConnected()) {
                    client.printHistory();
                }
            }
        }


    }

    /**
     * Reacts on the keys pressed by the user while he is typing in the edit-field.
     * If he hits return, the message is sent to the server and the edit-field is cleared.
     */
    @Override
    public void keyTyped(KeyEvent ke) {
        int key = ke.getKeyChar();
        if (ke.getSource() == gui.tf_chat) {

            //Bei return
            if (key == 10) {
                if (client != null && client.isConnected()) {
                    String s = gui.tf_chat.getText();
                    gui.tf_chat.setText("");
                    client.send(s);

                } else {
                    gui.throwErrorMessage("You are not connected to the Server");
                }
            }
        }

    }
    
    /**
     * Method has to be there when implementing a keylistener interface,
     * but the method is not used in this project.
     */
    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    /**
     * Method has to be there when implementing a keylistener interface,
     * but the method is not used in this project.
     */
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    /**
     * This method displays a message on the textarea-component in the user interface.
     * It builds a string from the data in the message object, and appends it to the content in the textarea.
     * 
     * @param msg
     * @throws java.rmi.RemoteException
     */
    public void displayMessage(Message msg) throws java.rmi.RemoteException {
        if (msg == null) {
            System.out.println("Message = NULL");
        }
        DateFormat df = new SimpleDateFormat("dd hh:mm");
        gui.ta_chat.append("[" + df.format(msg.getDate()) + "] " + msg.getFrom() + ": " + msg.getText() + "\n");
        gui.ta_chat.scrollRectToVisible(new Rectangle(0, gui.ta_chat.getHeight() - 2, 1, 1));
    }

    /**
     * This method creates a new Window for an error message, and displays the text of the string s.
     * 
     * @param s
     */
    public void throwErrorMessage(String s) {
        gui.throwErrorMessage(s);
    }
}
