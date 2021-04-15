package client;

import client.exceptions.NotAResponseException;
import server.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ResponseReader {

    private InputStream inputStream;

    public ResponseReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Response readResponse() throws ClassNotFoundException, IOException, NotAResponseException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Response responseFromServer;
        Object object = objectInputStream.readObject();
        if (object instanceof Response) {
            responseFromServer = (Response) object;
            //System.out.println(responseFromServer.getMessage());
            if (!responseFromServer.getList().isEmpty()) {
                //System.out.println(responseFromServer.getList());
            }
        } else {
            throw new NotAResponseException();
        }
        return responseFromServer;
    }

}
