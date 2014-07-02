package com.alexcorrigan.fixIntTest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest {

    private static final int PORT = 9991;

    @Test
    public void testHost() throws Exception {
        Socket host = new ServerSocket(PORT).accept();
        BufferedReader hostReader = new BufferedReader(new InputStreamReader(host.getInputStream()));
        String message;
        while ((message = hostReader.readLine()) != null) {
            System.out.println("RECEIVED: " + message);
        }
    }
}
