package server;

import console.Writer;
import musicband.MusicBand;
import server.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;

public class ServerWriter implements Writer {

    private SocketChannel socketChannel;
    private Response response;

    public ServerWriter(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        this.response = new Response();
    }

    public void write(String string) {
        if (string != null) {
            response.addToMessage(string + "\n");
        } else {
            response.addToMessage("\n");
        }
    }

    public void write(ArrayList<? extends MusicBand> musicBands) {
        if (musicBands != null) {
            response.addToList(musicBands);
        }
    }

    public void write(MusicBand musicBand) {
        if (musicBand != null) {
            response.addToList(musicBand);
        }
    }

    public void sendResponse() throws IOException {
        Collections.sort(response.getList());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(response);
        ByteBuffer outBuffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        socketChannel.write(outBuffer);
        outBuffer.clear();
        response = new Response();
    }

}