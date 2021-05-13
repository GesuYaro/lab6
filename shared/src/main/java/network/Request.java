package network;

import musicband.MusicBand;

import java.io.Serializable;

public class Request implements Serializable {

    private String command;
    private String firstArg;
    private MusicBand musicBand;

    public Request(String command, String firstArg, MusicBand musicBand) {
        this.command = command;
        this.firstArg = firstArg;
        this.musicBand = musicBand;
    }

    public String getCommand() {
        return command;
    }

    public String getFirstArg() {
        return firstArg;
    }

    public MusicBand getMusicBand() {
        return musicBand;
    }
}
