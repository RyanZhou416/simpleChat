package edu.seg2105.edu.server.ui;

import edu.seg2105.client.common.ChatIF;
import edu.seg2105.edu.server.backend.EchoServer;

import java.io.IOException;
import java.util.Scanner;

public class ServerConsole implements ChatIF {

    Scanner fromConsole;
    EchoServer server;

    public ServerConsole(int port) {
        server = new EchoServer(port);
        try {
            server.listen();
        } catch (IOException e) {
            System.out.println("Error: Could not listen for clients.");
        }

        fromConsole = new Scanner(System.in);
        Thread consoleThread = new Thread(this::accept);
        consoleThread.start();
    }


    /**
     * 当被重写时，该方法用于在UI上显示对象。
     *
     * @param message
     */
    @Override
    public void display(String message) {
        System.out.println("SERVER MSG> " + message);
    }

    public void accept() {
        try {
            String message;

            while (true) {
                message = fromConsole.nextLine();

                server.handleMessageFromServerConsole(message);

            }
        } catch (Exception ex) {
            System.out.println("从控制台读取时发生意外错误！");
        }
    }
}
