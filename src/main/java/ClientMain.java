import client.*;
import console.ConsoleWriter;
import console.FieldsReader;
import musicband.MusicBandFieldsChecker;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;

public class ClientMain {

    private static int PORT = 690;

    public static void main(String[] args) {
        Connector connector = null;
        Socket socket = null;
        try {
            try {
                connector = new Connector(InetAddress.getLocalHost(), PORT);
            } catch (UnknownHostException e) {
                System.out.println("Unknown host");
            }
            if (connector != null) {
                socket = connector.getSocket();
            }
            if (socket != null) {
                RequestWriter requestWriter = new RequestWriter(socket.getOutputStream(), new ByteArrayOutputStream(1024));
                ResponseReader responseReader = new ResponseReader(socket.getInputStream());
                HashSet<String> commandsWithExtendedRequest = new HashSet<>();
                commandsWithExtendedRequest.add("add");
                commandsWithExtendedRequest.add("insert_at");
                commandsWithExtendedRequest.add("update");
                ConsoleWriter consoleWriter = new ConsoleWriter();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                FieldsReader fieldsReader = new FieldsReader(new MusicBandFieldsChecker(bufferedReader), consoleWriter);
                RequestFabric requestFabric = new RequestFabric(commandsWithExtendedRequest, fieldsReader);
                Console console = new Console(requestWriter, responseReader, requestFabric, bufferedReader);
                console.run();
            }
        } catch (IOException e) {
            System.out.println("Connection refused");
        }
    }

}
