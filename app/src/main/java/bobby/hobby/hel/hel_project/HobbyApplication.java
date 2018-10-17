package bobby.hobby.hel.hel_project;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class HobbyApplication extends Application {
    //TODO: insert base URI of the socket
    private static final String BASE_SOCKET_URI = "";
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket(BASE_SOCKET_URI);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }
}
