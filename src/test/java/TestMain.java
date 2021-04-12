import collectionManager.Parser;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import server.Request;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class TestMain {
    public static final int PORT = 690;

    public static void main(String[] args) {
        try {
            Socket socket = null;
            while (socket == null) {
                try {
                    socket = new Socket(InetAddress.getLocalHost(), PORT);
                } catch (ConnectException e) {
                    System.out.println("Server is not available");
                }
            }
            System.out.println("Connected");
            OutputStream outputStream = socket.getOutputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(300);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String message = null;
            while (true) {
                message = console.readLine();
                if (message.equals("exit")) {
                    break;
                }
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                Request request = new Request(message, "arg", new String[]{"arg1", "arg2"});
                System.out.println(request);
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
                byteArrayOutputStream.writeTo(outputStream);
                byteArrayOutputStream.flush();
                byteArrayOutputStream.reset();
                System.out.println(in.readLine());
            }
        } catch(SocketException e) {
            System.out.println("Connection is refused");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
