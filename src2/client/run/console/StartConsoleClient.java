package client.run.console;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import client.model.Client;
import client.model.interfaces.ClientLocal;
import client.model.interfaces.ClientRemote;

public class StartConsoleClient {

    public static void main(String[] args) {

        try {
            /**
            System.setProperty("java.rmi.server.codebase",
            StartConsoleClient.class.getProtectionDomain().getCodeSource().getLocation().toString());
             **/
            System.out.println("Type in your username");
            Scanner talk = new Scanner(System.in);
            String nickname = talk.nextLine();

            //Create Client RemoteObject
            ClientLocal client = new Client(nickname, "CONSOLE", null);
            /**
            ClientRemote client_stub = (ClientRemote) UnicastRemoteObject.exportObject(client,0);

            //Register RemoteObject in RMI-Registry
            Registry registry = java.rmi.registry.LocateRegistry.getRegistry();
            registry.bind(client.getNickName(),  client_stub);

            client.register();
             **/
            boolean chatting = true;
            String text;
            while (chatting) {
                text = talk.nextLine();
                if (text.equals("/quit")) {
                    client.unregister();
                    chatting = false;
                } else if (text.contains("/history")) {
                    client.printHistory();
                } else {
                    client.send(text);
                }
            }
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
