package server;

import java.io.Serializable;

public class Request implements Serializable {

    private String command;
    private String firstArg;
    private String[] args;

    public Request(String command, String firstArg, String[] args) {
        this.command = command;
        this.firstArg = firstArg;
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public String getFirstArg() {
        return firstArg;
    }

    public String[] getArgs() {
        return args;
    }
}
