/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.server.interfaces;

import chat.client.interfaces.ClientRemote;
import chat.utils.Message;
import java.rmi.Remote;

/**
 *
 * @author TCHOUGOURIAN Tigran
 */
public interface ServerRemote extends Remote {

    public void add(ClientRemote c);

    public void remove(ClientRemote c);

    public void broadcast(Message msg);
    
}
