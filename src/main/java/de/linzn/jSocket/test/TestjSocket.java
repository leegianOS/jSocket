/*
 * Copyright (C) 2018. Niklas Linz - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 */

package de.linzn.jSocket.test;

import de.linzn.jSocket.client.JClientConnection;
import de.linzn.jSocket.server.JServer;
import de.linzn.jSocket.test.client.TestEventConnectionClient;
import de.linzn.jSocket.test.client.TestEventDataClient;
import de.linzn.jSocket.test.server.TestEventConnectionServer;
import de.linzn.jSocket.test.server.TestEventDataServer;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TestjSocket {
    public static TestjSocket testjSocket;
    public JServer jServer;
    public JClientConnection jClientConnection1;

    public TestjSocket(String[] args) {
        this.server();
        this.client();
    }

    public static void main(String[] input) {
        testjSocket = new TestjSocket(input);
    }

    private void client() {
        jClientConnection1 = new JClientConnection("localhost", 9090);
        jClientConnection1.registerIncomingDataListener("test_socket_connection", new TestEventDataClient());
        jClientConnection1.registerConnectionListener(new TestEventConnectionClient());
        jClientConnection1.setEnable();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            outputStream.writeUTF("GRE4gterfe23fw3g54EBFilzujasdEWR");
            outputStream.writeInt(51112);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jClientConnection1.writeOutput("test_socket_connection", byteArrayOutputStream.toByteArray());
    }

    private void server() {
        jServer = new JServer("localhost", 9090);
        jServer.registerIncomingDataListener("test_socket_connection", new TestEventDataServer());
        jServer.registerConnectionListener(new TestEventConnectionServer());
        jServer.openServer();
    }
}
