package server;

import console.CommandHandler;
import console.commands.CommandCode;
import console.exсeptions.*;
import server.exceptions.WrongRequestException;

import java.io.IOException;

public class RequestHandler implements Runnable {

    private CommandHandler commandHandler;
    private RequestReader requestReader;
    private ServerWriter writer;

    public RequestHandler(CommandHandler commandHandler, RequestReader requestReader, ServerWriter writer) {
        this.commandHandler = commandHandler;
        this.requestReader = requestReader;
        this.writer = writer;
    }

    @Override
    public void run() {
            CommandCode commandCode = CommandCode.DEFAULT;
            do {
                try {
                    Request request = null;
                    try {
                        request = requestReader.readRequest();
                        if (request != null) {
                            try {
                                commandCode = commandHandler.execute(request);
                            } catch (NoArgumentFoundException | InputValueException | IndexOutOfBoundsException | NoSuchIdException |
                            NotEnoughArgumentsException e) {
                                writer.write(e.getMessage());
                            }
                        }
                    } catch (NoSuchCommandException | WrongRequestException e) {
                        writer.write(e.getMessage());
                    } finally {
                        if (request != null) {
                            writer.sendResponse();
                            //System.out.println("send response");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected");
                    break;
                }
            } while (!commandCode.equals(CommandCode.EXIT));
    }
}
