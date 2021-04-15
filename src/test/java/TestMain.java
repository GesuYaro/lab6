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
import server.Response;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.CharBuffer;
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
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(300);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String message = null;
            while (true) {
                System.out.print(">");
                message = console.readLine();
                if (message.equals("exit")) {
                    break;
                }
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                Request request = new Request(message, "arg", new String[]{"arg1", "arg2"});
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();
                byteArrayOutputStream.writeTo(outputStream);
                byteArrayOutputStream.flush();
                byteArrayOutputStream.reset();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                try {
                    Object o = objectInputStream.readObject();
                    if (o instanceof Response) {
                        Response responseFromServer = (Response) o;
                        System.out.println(responseFromServer.getMessage());
                        if (!responseFromServer.getList().isEmpty()) {
                            System.out.println(responseFromServer.getList());
                        }
                    } else {
                        System.out.println("Not a response");
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Класса нет");
                }
            }
        } catch(SocketException e) {
            System.out.println("Connection is refused");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
