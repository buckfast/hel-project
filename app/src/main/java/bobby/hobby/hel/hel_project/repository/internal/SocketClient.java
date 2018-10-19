package bobby.hobby.hel.hel_project.repository.internal;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.Map;

import bobby.hobby.hel.hel_project.HobbyApplication;

public class SocketClient implements LifecycleObserver {
    private EventListener mEventListener;
    private Socket mSocket;
    public SocketClient(Application application, EventListener listener) {
        mEventListener = listener;
        HobbyApplication app = (HobbyApplication) application;
        mSocket = app.getSocket();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void connect() {
        setUpEvents(true);
        mSocket.connect();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void disconnect() {
        mSocket.disconnect();
        setUpEvents(false);
    }

    public Socket getSocket() {
        return mSocket;
    }

    private void setUpEvents(boolean isConnect) {
        Map<String, Emitter.Listener> events = mEventListener.returnListeners();
        for (Map.Entry<String, Emitter.Listener> event : events.entrySet()) {
            if (isConnect) {
                mSocket.on(event.getKey(), event.getValue());
            } else {
                mSocket.off(event.getKey(), event.getValue());
            }
        }
    }

    public interface EventListener {
        Map<String, Emitter.Listener> returnListeners();
    }

}
