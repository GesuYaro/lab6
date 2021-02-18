package Console.commands;

public interface Command {
    public CommandCode execute(String argument);
    public String getName();
    public String getDescription();
}
