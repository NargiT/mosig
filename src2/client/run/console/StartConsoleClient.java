package client.run.console;


import java.util.Scanner;

import client.model.Client;
import client.model.interfaces.ClientLocal;

public class StartConsoleClient {

    public static void main(String[] args) {

        try {
          
            System.out.println("Type in your username");
            Scanner talk = new Scanner(System.in);
            String nickname = talk.nextLine();

            //Create Client RemoteObject
            ClientLocal client = new Client(nickname, "CONSOLE", null);
           
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
