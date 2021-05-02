import musicband.MusicGenre;
import server.Request;
import server.Response;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class TestMain {
    public static final int PORT = 690;

    public static void main(String[] args) {
        System.out.println(MusicGenre.POP.ordinal());
        System.out.println(MusicGenre.RAP.ordinal());
        System.out.println(MusicGenre.BLUES.ordinal());
        System.out.println(MusicGenre.POP.compareTo(MusicGenre.BLUES));
    }

}
