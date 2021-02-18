package Console;

import Console.Exeptions.NoSuchCommandExeption;
import Console.commands.Command;
import Console.commands.CommandCode;
import Console.CommandHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class Console implements Runnable {

    private CommandHandler commandHandler;
    private BufferedReader reader;
    private ConsoleWriter writer;


    public Console(CommandHandler commandHandler, BufferedReader reader, ConsoleWriter writer) {
        this.commandHandler = commandHandler;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() {
        CommandCode commandCode;
        String[] userCommand = {"", ""};
        commandCode = CommandCode.DEFAULT;
        do {
            try {
            userCommand = reader.readLine().trim().split(" ", 2);
            userCommand[0] = userCommand[0].trim();
            if (userCommand.length > 1) userCommand[1] = userCommand[1].trim();
            commandCode = commandHandler.execute(userCommand);
            } catch (NoSuchCommandExeption e) {
                this.print(e.getMessage());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
//            if (commandCode.equals(CommandCode.NO_SUCH_COMMAND)) {
//                print("Команда " + userCommand[0] + " не найдена");
//            }
        } while (!commandCode.equals(CommandCode.EXIT));
//        try {
//            reader.close();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    public void setCommandHandler(CommandHandler commandHandler) {
//        this.commandHandler = commandHandler;
//    }

//    public void setReader(BufferedReader reader) {
//        this.reader = reader;
//    }

    public void print(String string) {
        writer.write(string);

    }
}
