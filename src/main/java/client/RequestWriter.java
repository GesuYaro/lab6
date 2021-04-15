package client;

import server.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class RequestWriter {

    OutputStream outputStream;
    ByteArrayOutputStream byteArrayOutputStream;

    public RequestWriter(OutputStream outputStream, ByteArrayOutputStream byteArrayOutputStream) {
        this.outputStream = outputStream;
        this.byteArrayOutputStream = byteArrayOutputStream;
    }

    public void sendRequest(Request request) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();
        byteArrayOutputStream.writeTo(outputStream);
        byteArrayOutputStream.flush();
        byteArrayOutputStream.reset();
    }

}
