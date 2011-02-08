/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.interfaces;

import chat.utils.Message;
import java.rmi.RemoteException;

/**
 *
 * @author TCHOUGOURIAN Tigran
 */
public interface ServerLocal {

    public boolean broadcast(Message msg)
            throws RemoteException;

    ;
}
