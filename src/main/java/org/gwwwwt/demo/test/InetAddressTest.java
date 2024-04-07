package org.gwwwwt.demo.test;


import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetAddressTest {
    public static void main(String[] args) {
        final InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        final InetAddress address = inetSocketAddress.getAddress();

        System.out.println(address.getHostName());
    }
}
