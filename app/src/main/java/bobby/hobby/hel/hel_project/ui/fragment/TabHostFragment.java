package bobby.hobby.hel.hel_project.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseTabHostFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.ChatText;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class TabHostFragment extends BaseTabHostFragment<FragmentViewModel, ActivityViewModel> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Observer<Integer> tabPositionObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer == 1) {
                    Util.hideKeyboard(getActivity(),view);
                }
            }
        };
        mFragmentsViewModel.getCurrentPosition().observe(getActivity(), tabPositionObserver);

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

    public void populateFromIntent() {
        Intent intent = getActivity().getIntent();
        if ((User)(intent.getSerializableExtra("user")) != null) {
            mFragmentsViewModel.currentUser.setValue((User) (intent.getSerializableExtra("user")));
            mFragmentsViewModel.hobbyList.setValue(mFragmentsViewModel.currentUser.getValue().getHobbies());
            mViewModel.currentUser.setValue(mFragmentsViewModel.currentUser.getValue());
            mFragmentsViewModel.token = intent.getStringExtra("token");

            SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.shared_preferences), getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            String username = sharedPref.getString("username", null);
            if (username == null) {
                editor.putString("username", ((User) (intent.getSerializableExtra("user"))).getName());
                editor.apply();
            }
        }

       // Log.d("asd", "useri!!!!!!!: "+username);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateFromIntent();
        /*SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.shared_preferences), getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", mFragmentsViewModel.currentUser.getValue().getToken());
        editor.commit();
        */

       // mFragmentsViewModel.longRunningTask(false);

        /*
        if (mFragmentsViewModel.currentUser.getValue() == null) {
            Log.d("asd", "current user was null");
            User user = new User();
            user.setEmail("hoangl@mail.com");
            user.setPassword("hoangl@gmail.com");
            mFragmentsViewModel.login(user);
        }
        */

        mFragmentsViewModel.title.observe(getActivity(), title -> {
            mViewModel.title.setValue(mFragmentsViewModel.getTitle(mFragmentsViewModel.getCurrentPositionDrawer()));
        });



        mFragmentsViewModel.clearTitle.observe(getActivity(), b -> {
           mViewModel.title.setValue("");
           mViewModel.logoutClick.setValue(false);
        });

        mViewModel.logoutClick.observe(getActivity(), b -> {

            Log.d("asd", "logout called "+b);
            if (b) {
                mFragmentsViewModel.logout();
                mViewModel.logoutClick.setValue(false);

                SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.shared_preferences), getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("username", "null");
                editor.commit();
            }
        });

        mFragmentsViewModel.listPosition.observe(getActivity(), pos -> {
           // Log.d("asd", "intabostfrafme "+String.valueOf(mFragmentsViewModel.hobbyList.getValue()));

            if (mFragmentsViewModel.hobbyList.getValue() != null && mFragmentsViewModel.getHobbyByPosition(pos) != mFragmentsViewModel.lastKeyword) {
                mFragmentsViewModel.longRunningTask(true);
                //Log.d("asd", pos+", "+mFragmentsViewModel.getHobbyByPosition(pos)+" --- "+mFragmentsViewModel.lastKeyword);
                mFragmentsViewModel.searchLinkedEvents(mFragmentsViewModel.getHobbyByPosition(pos));
                mFragmentsViewModel.emitJoinRoom(mFragmentsViewModel.getHobbyByPosition(pos));
                //mFragmentsViewModel.chatMessageList.setValue(new ArrayList<ChatText>()); // TODO: get chat log from server
                mFragmentsViewModel.getChatLog(mFragmentsViewModel.getHobbyByPosition(pos));
                mFragmentsViewModel.lastKeyword = mFragmentsViewModel.getHobbyByPosition(pos);

            }

        });
    }

}
