package chat.client;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import chat.client.interfaces.ClientLocal;
import chat.client.interfaces.ClientRemote;
import chat.server.Server;
import chat.server.interfaces.ServerRemote;
import chat.utils.Constants;
import chat.utils.Message;
import java.io.Serializable;
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
    public boolean send(Message msg) {
        try {
            // TODO Auto-generated method stub
            server.broadcast(msg);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public boolean register() {
        try {
            // TODO Auto-generated method stub
            server.add((ClientRemote) this);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public void unregister() {
        try {
            // TODO Auto-generated method stub
            server.remove((ClientRemote) this);
        } catch (RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean receive(Message msg) {
        // TODO Auto-generated method stub
        System.out.println(msg.getText());
        return true;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
