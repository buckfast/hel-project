package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseTabHostFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.ChatText;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class TabHostFragment extends BaseTabHostFragment<FragmentViewModel, ActivityViewModel> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*
        final Observer<Integer> tabPositionObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                int slide = getResources().getDimensionPixelSize(R.dimen.action_bar_height);
                FrameLayout sort_container = view.findViewById(R.id.sort_button_container);
                View tabs = view.findViewById(R.id.result_tabs);

                if (mFragmentsViewModel.getCurrentPosition().getValue() == 1) {
                    sort_container.startAnimation(Util.transformMargin(sort_container, slide,200,0));
                } else {
                    sort_container.startAnimation(Util.transformMargin(sort_container, slide, 200,1));
                }
            }
        };
        mFragmentsViewModel.getCurrentPosition().observe(this, tabPositionObserver);
        */
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mFragmentsViewModel.logout();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mFragmentsViewModel.currentUser.getValue() == null) {
            Log.d("asd", "current user was null");
            User user = new User();
            user.setEmail("hoangl@mail.com");
            user.setPassword("hoangl@gmail.com");
            mFragmentsViewModel.login(user);
        }

        mFragmentsViewModel.listPosition.observe(this, pos -> {
            if (mFragmentsViewModel.getHobbyByPosition(pos) != mFragmentsViewModel.lastKeyword) {
                Log.d("asd", pos+", "+mFragmentsViewModel.getHobbyByPosition(pos)+" --- "+mFragmentsViewModel.lastKeyword);
                mFragmentsViewModel.searchLinkedEvents(mFragmentsViewModel.getHobbyByPosition(pos));
                mFragmentsViewModel.emitJoinRoom(mFragmentsViewModel.getHobbyByPosition(pos));
                mFragmentsViewModel.chatMessageList.setValue(new ArrayList<ChatText>()); // TODO: 28.10.2018 do something some day 
                mFragmentsViewModel.lastKeyword = mFragmentsViewModel.getHobbyByPosition(pos);

            }

        });
    }
}