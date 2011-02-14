package chat.client;

import chat.client.interfaces.ClientLocal;
import chat.client.interfaces.ClientRemote;
import chat.utils.Constants;
import chat.utils.Message;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartClient {

    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.codebase", ClientRemote.class.getProtectionDomain().getCodeSource().getLocation().toString());
            Client c = new Client("SomeUser");
            ClientRemote client_stub = (ClientRemote) UnicastRemoteObject.exportObject(c, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(c.getNickname(), client_stub);

            c.register();
            Message msg = new Message("Hello me", new Date());
            c.send(msg);
            c.send(msg);


        } catch (AlreadyBoundException ex) {
            Logger.getLogger(StartClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(StartClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(StartClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
