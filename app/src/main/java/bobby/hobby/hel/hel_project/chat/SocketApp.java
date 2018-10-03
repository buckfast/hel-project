package bobby.hobby.hel.hel_project.chat;

import android.app.Application;


import java.net.URISyntaxException;

import bobby.hobby.hel.hel_project.Constants;
import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketApp extends Application {

    private Socket socket;
    {
        try {
            socket = IO.socket(Constants.CHAT_SERVER);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
