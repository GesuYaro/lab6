package network;

import musicband.MusicBand;

import java.io.Serializable;
import java.util.ArrayList;

public class Response implements Serializable {

    private ArrayList<MusicBand> list;
    private String message;

    public Response(String message, ArrayList<MusicBand> list) {
        this.message = message;
        this.list = list;
    }

    public Response() {
        this.message = "";
        this.list = new ArrayList<>();
    }

    public void addToMessage(String string) {
        if (string != null) {
            message += string;
        }
    }

    public void addToList(MusicBand musicBand) {
        list.add(musicBand);
    }

    public void addToList(ArrayList<? extends MusicBand> musicBands) {
        list.addAll(musicBands);
    }

    public ArrayList<MusicBand> getList() {
        return list;
    }

    public String getMessage() {
        return message;
    }

    public void setList(ArrayList<MusicBand> list) {
        this.list = list;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
