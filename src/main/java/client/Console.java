package client;

import client.exceptions.NotAResponseException;
import console.commands.ClientExecuteScriptCommand;
import musicband.MusicBand;
import server.Request;
import server.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Console {

    private RequestWriter requestWriter;
    private ResponseReader responseReader;
    private BufferedReader bufferedReader;
    private RequestFabric requestFabric;
    private ClientExecuteScriptCommand clientExecuteScriptCommand;
    private Socket socket;

    public Console(RequestWriter requestWriter, ResponseReader responseReader, RequestFabric requestFabric, BufferedReader bufferedReader, ClientExecuteScriptCommand clientExecuteScriptCommand, Socket socket) {
        this.requestWriter = requestWriter;
        this.responseReader = responseReader;
        this.requestFabric = requestFabric;
        this.bufferedReader = bufferedReader;
        this.clientExecuteScriptCommand = clientExecuteScriptCommand;
        this.socket = socket;
    }

    public void run() throws IOException {
        while (true) {
            String userMessage = bufferedReader.readLine();
            if (userMessage != null && !userMessage.trim().equals("")) {
                String[] command = userMessage.trim().split(" ", 2);
                command[0] = command[0].trim();
                if (command.length > 1) {
                    command[1] = command[1].trim();
                }
                if (command[0].equals("execute_script")) {
                    if (command.length > 1) {
                        clientExecuteScriptCommand.execute(command[1], null);
                    } else {
                        System.out.println("Argument not found");
                    }
                } else {
                    Request request = requestFabric.createRequest(command[0], command.length > 1 ? command[1] : "");
                    requestWriter.sendRequest(request);
                    if (command[0].equals("exit")) {
                        break;
                    }
                    try {
                        Response response = responseReader.readResponse();
                        System.out.println(response.getMessage());
                        if (!response.getList().isEmpty()) {
                            for (MusicBand mb : response.getList()) {
                                System.out.println(mb.toString());
                            }
                        }
                    } catch (ClassNotFoundException | NotAResponseException e) {
                        System.out.println("Problem with the response from server");
                    }
                }
            }
        }
        socket.close();
    }

}
