package chat.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import chat.client.interfaces.ClientLocal;
import chat.client.interfaces.ClientRemote;
import chat.server.interfaces.ServerRemote;
import chat.utils.Constants;
import chat.utils.Message;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements ClientLocal, ClientRemote, Serializable {

    private String nickname;
    private int id;
    private ServerRemote server;

    public Client(String nickname) {
        super();
        this.nickname = nickname;
        initialize();
    }

    public final void initialize() {
        try {
            Registry registry = LocateRegistry.getRegistry(Constants.HOST);
            server = (ServerRemote) registry.lookup(Constants.SERVER);
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean send(String msg) {
        if (msg.equals("quit")) {
            unregister();
            return true;
        }
        Message toSend = new Message(nickname, msg, new Date());
        try {
            server.broadcast(toSend);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public boolean register() {
        try {
            server.add(this.getNickname());
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public void unregister() {
        try {
            server.remove(this.getNickname());
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean receive(Message msg) {
        String from = getNickname();
        if (!msg.getFrom().equals(this.getNickname())) {
            from = msg.getFrom();
        }
        DateFormat df = new SimpleDateFormat(Constants.DATEFORMAT);
        System.out.println(df.format(msg.getDate()) + " " + from + ": " + msg.getText());
        return true;
    }

    @Override
    public boolean receive(LinkedList<Message> history) {
        for (int i = 0; i < history.size(); i++) {
            Message msg = history.get(i);
            if (!receive(msg)) {
                System.out.println("--");
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
