package test;

import server.Server;

public class ServerTest {
    public static void main(String[] args) {
        new Server("test.conf").run();
    }
}
