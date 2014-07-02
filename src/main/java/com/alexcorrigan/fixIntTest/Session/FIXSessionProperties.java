package com.alexcorrigan.fixIntTest.Session;

public class FIXSessionProperties {

    private final String connectHost;
    private final int connectPort;

    public FIXSessionProperties(String connectHost, int connectPort) {
        this.connectHost = connectHost;
        this.connectPort = connectPort;
    }

}
