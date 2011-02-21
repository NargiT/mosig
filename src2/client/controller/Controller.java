package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.registry.Registry;

import utils.Message;

import client.model.Client;
import client.model.interfaces.ClientLocal;

import client.view.Gui;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Controller implements ActionListener, KeyListener {

    private Gui gui;
    private ClientLocal client;
    private Registry registry;

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
                System.out.println("WindowListener");
                if (client != null && client.isConnected()) {
                    client.unregister();
                }
                System.exit(0);
            }
        };
        gui.addWindowListener(wl);

    }

    public void run() {
        gui.startGUI();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("ACTIONLISTENER started");
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
                    client = new Client(gui.show_ConnectionDialog(), "GUI", this);

                    /**
                    ClientRemote client_stub = (ClientRemote) UnicastRemoteObject.exportObject(client,0);
                    //client = (ClientLocal) client_impl;
                    //Register RemoteObject in RMI-Registry
                    Registry registry = java.rmi.registry.LocateRegistry.getRegistry();
                    String nickname = client.getNickName();
                    registry.bind(client.getNickName(),  client_stub);

                    client.register();
                     **/
                } catch (Exception e) {
                    e.printStackTrace();
                }
                gui.tf_chat.setEnabled(true);
            } else {

                gui.throwErrorMessage("Client already connected");
            }
        } else if (ae.getSource().equals(gui.m_disconnect)) {
            if (!(client == null) || client.isConnected()) {
                client.unregister();
                gui.tf_chat.setEnabled(false);
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

    @Override
    public void keyPressed(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void displayMessage(Message msg) throws java.rmi.RemoteException {
        if (msg == null) {
            System.out.println("Message = NULL");
        }
        DateFormat df = new SimpleDateFormat("dd hh:mm");
        gui.ta_chat.append("[" + df.format(msg.getDate()) + "] " + msg.getFrom() + ": " + msg.getText() + "\n");
    }

    public void throwErrorMessage(String s) {
        gui.throwErrorMessage(s);
    }
}
