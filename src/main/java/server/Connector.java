package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Connector {

    private int port;
    private ServerSocketChannel serverSocketChannel;

    public Connector() throws IOException {
        this.port = 690;
        SocketAddress address = new InetSocketAddress(port);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(address);
        serverSocketChannel.configureBlocking(false);
    }

    public Connector(int port) throws IOException {
        this.port = port;
        SocketAddress address = new InetSocketAddress(port);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(address);
        serverSocketChannel.configureBlocking(false);
    }

//    {
//        SocketAddress address = new InetSocketAddress(port);
//        serverSocketChannel = ServerSocketChannel.open();
//        serverSocketChannel.bind(address);
//        serverSocketChannel.configureBlocking(false);
//    }

    public SocketChannel getSocketChannel() throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        if (socketChannel != null) {
            socketChannel.configureBlocking(false);
        }
        return socketChannel;
    }

}
