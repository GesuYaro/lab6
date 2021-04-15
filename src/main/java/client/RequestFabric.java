package client;

import console.FieldsReader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicGenre;
import server.Request;

import java.util.HashSet;

public class RequestFabric {

    private HashSet<String> commandsWithExtendedRequest;
    private FieldsReader fieldsReader;

    public RequestFabric(HashSet<String> commandsWithExtendedRequest, FieldsReader fieldsReader) {
        this.commandsWithExtendedRequest = commandsWithExtendedRequest;
        this.fieldsReader = fieldsReader;
    }

    public Request createRequest(String command, String argument) {
        Request request;
        if (commandsWithExtendedRequest.contains(command)) {
            request = createExtendedRequest(command, argument);
        } else {
            request = new Request(command, argument, new String[]{});
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
        return new Request(command, argument, new String[]{name,
                coordinates.getX().toString(),
                coordinates.getY().toString(),
                Integer.toString(numberOfParticipants),
                singlesCount.toString(),
                musicGenre.name(),
                label.getName()});
    }

}
