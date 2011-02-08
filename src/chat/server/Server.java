/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat.server;

import chat.client.interfaces.ClientRemote;
import chat.server.interfaces.ServerRemote;
import chat.utils.Message;

/**
 *
 * @author NargiT
 */
public class Server implements ServerRemote {

    public void add(ClientRemote c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove(ClientRemote c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void broadcast(Message msg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
