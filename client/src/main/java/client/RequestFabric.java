package client;

import console.FieldsReader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;
import network.Request;


import java.util.Collection;

public class RequestFabric {

    private Collection<String> commandsWithExtendedRequest;
    private FieldsReader fieldsReader;

    public RequestFabric(Collection<String> commandsWithExtendedRequest, FieldsReader fieldsReader) {
        this.commandsWithExtendedRequest = commandsWithExtendedRequest;
        this.fieldsReader = fieldsReader;
    }

    public Request createRequest(String command, String argument) {
        Request request;
        if (commandsWithExtendedRequest.contains(command)) {
            request = createExtendedRequest(command, argument);
        } else {
            request = new Request(command, argument, null);
        }
        return request;
    }

    private Request createExtendedRequest(String command, String argument) {
        String name = fieldsReader.readName();
        Coordinates coordinates = fieldsReader.readCoordinates();
        int numberOfParticipants = fieldsReader.readNumberOfParticipants();
        Integer singlesCount = fieldsReader.readSinglesCount();
        MusicGenre musicGenre = fieldsReader.readMusicGenre();
        Label label = fieldsReader.readLabel();
        return new Request(command, argument, new MusicBand(0, name, coordinates, null, numberOfParticipants, singlesCount, musicGenre, label));
    }

}
