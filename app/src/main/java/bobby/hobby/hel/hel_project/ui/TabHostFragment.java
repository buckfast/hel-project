package bobby.hobby.hel.hel_project.ui;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseTabHostFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;

public class TabHostFragment extends BaseTabHostFragment<FragmentViewModel, ActivityViewModel> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        User user = new User();
        user.setEmail("hoangl@mail.com");
        user.setPassword("hoangl@gmail.com");
        mFragmentsViewModel.login(user);
        mFragmentsViewModel.currentUser.observe(this, user1 -> {
            Log.d("testi", user1.getToken());
        });
        mFragmentsViewModel.getHobbyList();
        mFragmentsViewModel.hobbyList.observe(this, hobby -> {
            Log.d("testi", hobby.get(2));
        });

        mFragmentsViewModel.linkedEvents.observe(this, eventList -> {
            Log.d("testi", "oolrait");
        });
        mFragmentsViewModel.getEventList("jalkapallo");
        mFragmentsViewModel.logout();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    protected Class<ActivityViewModel> returnViewModel() {
        return ActivityViewModel.class;
    }

    @Override
    protected void setUpAdater(Adapter adater) {
        TabChatFragment chatFragment = new TabChatFragment();
        TabEventsFragment eventsFragment = new TabEventsFragment();
        adater.addFragment(chatFragment, "Chat");
        adater.addFragment(eventsFragment, "Events");
    }
}
