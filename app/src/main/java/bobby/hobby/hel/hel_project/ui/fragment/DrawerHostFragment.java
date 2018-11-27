package bobby.hobby.hel.hel_project.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseNavViewListHostFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;


public class DrawerHostFragment extends BaseNavViewListHostFragment<FragmentViewModel, ActivityViewModel> {

    //ImageButton search_button;

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    protected Class<ActivityViewModel> returnViewModel() {
        return ActivityViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected BaseNavViewListChildFragment returnLeftChild() {
        return new DrawerLeftFragment();
    }

    @Override
    protected BaseNavViewListChildFragment returnRightChild() {
        return new DrawerRightFragment();
    }

    @Override
    protected int returnDrawerHostLayout() {
        return R.layout.fragment_drawer_host;
    }

    @Override
    protected int returnGuidelineId() {
        return R.id.guideline;
    }

    @Override
    protected int returnLeftChildId() {
        return R.id.left_child_drawer;
    }

    @Override
    protected int returnRightChildId() {
        return R.id.right_child_drawer;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentsViewModel.listPosition.observe(this, pos -> {
                mViewModel.title.setValue(mFragmentsViewModel.getTitle(pos));
        });

        mViewModel.hostViewCreated.setValue(true);

/*
        search_button = view.findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).addToBackStack(null).commit();
            }
        });*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public BaseFragment returnFragment() {
        return this;
    }
}
