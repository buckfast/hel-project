package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseTabHostFragment;

public class TabHostFragment extends BaseTabHostFragment<FragmentViewModel, ActivityViewModel> {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
