package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import bobby.hobby.hel.hel_project.base.view.activity.BaseActivity;
import bobby.hobby.hel.hel_project.repository.internal.model.User;

public class MainActivity extends BaseActivity<ActivityFragmentViewModel> {

    @Override
    protected Class<ActivityFragmentViewModel> returnViewModel() {
        return ActivityFragmentViewModel.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel.attachSocketClientTo(this);
        User user = new User();
        user.setEmail("hoangl@mail.com");
        user.setPassword("hoangl@gmail.com");
        mViewModel.login(user);
        mViewModel.currentUser.observe(this, user1 -> {
            Toast.makeText(this, user1.getToken(), Toast.LENGTH_SHORT).show();
        });
        mViewModel.getHobbyList();
        mViewModel.hobbyList.observe(this, hobby -> {
            Log.d("Test", hobby.get(0));
        });

        mViewModel.getEventList("Jalkapallo");
        mViewModel.eventList.observe(this, eventList -> {
            Log.d("Test", Integer.toString(eventList.getCount()));
        });

        mViewModel.emit();
    }
}
