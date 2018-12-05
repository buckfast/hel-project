package bobby.hobby.hel.hel_project;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Description: This class provide application wide scope context
 * Features:
 * - Socket instance
 * Works with:
 * {@link bobby.hobby.hel.hel_project.repository.internal.SocketClient}
 */

public class HobbyApplication extends Application {
    private static final String BASE_SOCKET_URI = "https://hobotti-backend-testing.herokuapp.com/";
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
