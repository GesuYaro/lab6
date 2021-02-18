package Console;

import Console.Exeptions.NoSuchCommandExeption;
import Console.commands.*;

import java.util.HashMap;

public class CommandHandler {

    private HistoryStorage historyStorage;
    private HashMap<String,Command> commands = new HashMap<>();

    public CommandHandler(HistoryStorage historyStorage ,HelpCommand helpCommand, InfoCommand infoCommand, ShowCommand showCommand, AddCommand addCommand, UpdateCommand updateCommand, RemoveByIdCommand removeByIdCommand, ClearCommand clearCommand, ExitCommand exitCommand, InsertAtCommand insertAtCommand, RemoveLastCommand removeLastCommand, HistoryCommand historyCommand) {
        this.historyStorage = historyStorage;
        commands.put("help", helpCommand);
        commands.put("info", infoCommand);
        commands.put("show", showCommand);
        commands.put("add", addCommand);
        commands.put("update", updateCommand);
        commands.put("remove_by_id", removeByIdCommand);
        commands.put("clear", clearCommand);
        commands.put("exit", exitCommand);
        commands.put("insert_at", insertAtCommand);
        commands.put("remove_last", removeLastCommand);
        commands.put("history", historyCommand);

    }

    public CommandHandler(HashMap<String, Command> commands, HistoryStorage historyStorage){
        this.commands = commands;
        this.historyStorage = historyStorage;
    }

    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }


    public String[] getCommandHistory() {
        return historyStorage.getCommandHistory();
    }

    public CommandCode execute(String[] command) {
        if(commands.containsKey(command[0])) {
            historyStorage.addToCommandHistory(command[0]);
            return commands.get(command[0]).execute(command.length > 1 ? command[1] : "");
        }
        else {
            if (!command[0].equals("")) throw new NoSuchCommandExeption(command[0]);
            return CommandCode.NO_SUCH_COMMAND;
        }
    }

//    public CommandCode execute(String[] command) {
//        switch (command[0]) {
//            case "" :
//                return CommandCode.DEFAULT;
//                break;
//
//            case "help" :
//                return helpCommand.execute(command[1]);
//                break;
//
//            case "info" :
//                return infoCommand.execute(command[1]);
//                break;
//
//            case "show" :
//                return showCommand.execute(command[1]);
//                break;
//
//            case "add" :
//                return addCommand.execute(command[1]);
//                break;
//
//            case "update" :
//                return updateCommand.execute(command[1]);
//                break;
//
//            case "remove_by_id" :
//                return remove_by_idCommand.execute(command[1]);
//                break;
//
//            case "clear" :
//                return clearCommand.execute(command[1]);
//                break;
//
//            case "save" :
//                return saveCommand.execute(command[1]);
//                break;
//
//            case "execute_script" :
//                return execute_scriptCommand.execute(command[1]);
//                break;
//
//            case "exit" :
//                return exitCommand.execute(command[1]);
//                break;
//
//            case "insert_at index" :
//                return insert_at indexCommand.execute(command[1]);
//                break;
//
//            case "remove_last" :
//                return remove_lastCommand.execute(command[1]);
//                break;
//
//            case "history" :
//                return historyCommand.execute(command[1]);
//                break;
//
//            case "count_greater_than_genre" :
//                return count_greater_than_genreCommand.execute(command[1]);
//                break;
//
//            case "filter_less_than_singles_count" :
//                return filter_less_than_singles_countCommand.execute(command[1]);
//                break;
//
//            case "print_field_descending_genre" :
//                return print_field_descending_genreCommand.execute(command[1]);
//                break;
//
//            default:
//                return CommandCode.NO_SUCH_COMMAND;
//
//        }
//    }

    public HashMap<String,Command> getCommands() {
        return commands;
    }

    public static class HistoryStorage {
        private String[] commandHistory = new String[7];

        public String[] getCommandHistory() {
            return commandHistory;
        }

        private void addToCommandHistory(String command) {
            for (int i = commandHistory.length-2; i >= 0; i--) {
                commandHistory[i+1] = commandHistory[i];
            }
            commandHistory[0] = command;
        }
    }
}
