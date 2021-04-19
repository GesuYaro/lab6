package server;

import server.exceptions.WrongRequestException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class RequestReader {

    private ByteBuffer inBuffer;
    private SocketChannel socketChannel;

    public RequestReader(SocketChannel socketChannel, ByteBuffer inBuffer) {
        this.socketChannel = socketChannel;
        this.inBuffer = inBuffer;
    }

    public Request readRequest() throws IOException, WrongRequestException {
        Object returningObject = null;
        inBuffer.clear();
        int bytesRead = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         do {
            try {
                if (socketChannel != null) {
                    bytesRead = socketChannel.read(inBuffer);
                }
            } finally {
                inBuffer.flip();
            }
            for (int i = 0; i < inBuffer.limit(); i++) {
                byteArrayOutputStream.write(inBuffer.get());
            }
        } while (bytesRead > 0);
        if (byteArrayOutputStream.size() > 0) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()))) {
                returningObject = objectInputStream.readObject();
            } catch (ClassNotFoundException | EOFException e) {
                throw new WrongRequestException();
            }
        }
        if (returningObject != null) {
            if (returningObject instanceof Request) {
                return (Request) returningObject;
            } else {
                throw new WrongRequestException();
            }
        } else {
            return null;
        }

    }

}
