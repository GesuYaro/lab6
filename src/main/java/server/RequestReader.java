package server;

import server.exceptions.WrongRequestException;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestReader {

    private ByteBuffer inBuffer;
    private byte[] bytes;
    private SocketChannel socketChannel;

    public RequestReader(SocketChannel socketChannel, ByteBuffer inBuffer) {
        this.socketChannel = socketChannel;
        this.inBuffer = inBuffer;
    }

    public Request readRequest() throws IOException, WrongRequestException {
        Object returningObject = null;
        inBuffer.clear();
        try {
            if (socketChannel != null) {
                socketChannel.read(inBuffer);
            }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            inBuffer.flip();
        }
        bytes = new byte[inBuffer.limit()];
        for (int i = 0; i < inBuffer.limit(); i++) {
            bytes[i] = inBuffer.get();
        }
        if (bytes.length > 0) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
                returningObject = objectInputStream.readObject();
            } catch (ClassNotFoundException | EOFException e) {
                throw new WrongRequestException();
            }
        }
        if (returningObject instanceof Request) {
            return (Request) returningObject;
        } else {
            return null;
        }

    }

}
