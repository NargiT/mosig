/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.server;

import chat.server.interfaces.ServerRemote;
import chat.utils.Constants;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NargiT
 */
public class StartServer {

    public static void main(String[] args ) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            Server server = new Server();
            ServerRemote server_stub = (ServerRemote) UnicastRemoteObject.exportObject(server,0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind(Constants.HOST, server_stub);

            System.out.print("Crazy Chat Server is running.");
            
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AccessException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
