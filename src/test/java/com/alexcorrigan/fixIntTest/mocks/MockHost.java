package com.alexcorrigan.fixIntTest.mocks;

import com.alexcorrigan.fixIntTest.features.FeatureStepDefinitions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MockHost {

    private List<MockHostListener> mockHostListeners = new ArrayList<MockHostListener>();
    private int testPort;

    public MockHost(int testPort) {
        this.testPort = testPort;
    }

    public void start() throws IOException {
        Socket host = new ServerSocket(testPort).accept();
        BufferedReader hostReader = new BufferedReader(new InputStreamReader(host.getInputStream()));
        String receivedMessage;
        while ((receivedMessage = hostReader.readLine()) != null) {
            for (MockHostListener mockHostListener : mockHostListeners) {
                mockHostListener.onReceivedMessage(receivedMessage);
            }
        }
    }

    public void listen(MockHostListener mockHostListener) {
        mockHostListeners.add(mockHostListener);
    }
}
