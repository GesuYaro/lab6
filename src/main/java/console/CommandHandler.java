package console;

import console.exсeptions.NoSuchCommandException;
import console.commands.*;

import java.util.HashMap;

/**
 * Класс обработчика команд, он же Invoker
 */
public class CommandHandler {

    private HistoryStorage historyStorage;
    private HashMap<String,Command> commands = new HashMap<>();

//    public CommandHandler(HistoryStorage historyStorage ,HelpCommand helpCommand, InfoCommand infoCommand, ShowCommand showCommand, AddCommand addCommand, UpdateCommand updateCommand, RemoveByIdCommand removeByIdCommand, ClearCommand clearCommand, ExitCommand exitCommand, InsertAtCommand insertAtCommand, RemoveLastCommand removeLastCommand, HistoryCommand historyCommand) {
//        this.historyStorage = historyStorage;
//        commands.put("help", helpCommand);
//        commands.put("info", infoCommand);
//        commands.put("show", showCommand);
//        commands.put("add", addCommand);
//        commands.put("update", updateCommand);
//        commands.put("remove_by_id", removeByIdCommand);
//        commands.put("clear", clearCommand);
//        commands.put("exit", exitCommand);
//        commands.put("insert_at", insertAtCommand);
//        commands.put("remove_last", removeLastCommand);
//        commands.put("history", historyCommand);
//
//    }

    /**
     * @param commands Мапа с объектами команд, которые нужно будет исполнять, каждый объект должен реализовывать интерфейс Command
     * @param historyStorage Хранитель истории
     */
    public CommandHandler(HashMap<String, Command> commands, HistoryStorage historyStorage){
        this.commands = commands;
        this.historyStorage = historyStorage;
    }

    /**
     * @param commands Мапа с командами
     */
    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }


    /**
     * @return Массив строк с названиями команд без аргументов
     */
    public String[] getCommandHistory() {
        return historyStorage.getCommandHistory();
    }

    /**
     * Исполняет программу
     * @param command Название команды
     * @return CommandCode в зависимости от результата выполнения команды
     */
    public CommandCode execute(String[] command) {
        if(commands.containsKey(command[0])) {
            historyStorage.addToCommandHistory(command[0]);
            return commands.get(command[0]).execute(command.length > 1 ? command[1] : "");
        }
        else {
            if (!command[0].equals("")) throw new NoSuchCommandException(command[0]);
            return CommandCode.NO_SUCH_COMMAND;
        }
    }


    /**
     * @return Мапу с командами, которые использует CommandHandler
     */
    public HashMap<String,Command> getCommands() {
        return commands;
    }

    /**
     * Класс, хранящий историю  команд, которые вызвал пользователь
     */
    public static class HistoryStorage {
        private String[] commandHistory = new String[7];

        /**
         * @return Историю команд
         */
        public String[] getCommandHistory() {
            return commandHistory;
        }

        /**
         * Добавляет команду в историю
         * @param command
         */
        private void addToCommandHistory(String command) {
            for (int i = commandHistory.length-2; i >= 0; i--) {
                commandHistory[i+1] = commandHistory[i];
            }
            commandHistory[0] = command;
        }
    }
}
