package server;

import app.ServerMain;

public class ShutdownSaver implements Runnable{
    @Override
    public void run() {
        ServerMain.saveFile();
    }
}
