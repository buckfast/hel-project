package bobby.hobby.hel.hel_project.base.view.fragment.master;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity.NavigationDrawerLayout;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseHostFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseNavViewListHostFragment<T extends BaseViewModel, V extends BaseViewModel> extends BaseHostFragment<T,V> implements NavigationDrawerLayout {

    protected abstract BaseNavViewListChildFragment returnLeftChild();
    protected abstract BaseNavViewListChildFragment returnRightChild();
    protected abstract int returnDrawerHostLayout();

    protected float getGuidelineSplitPercentage () {
        return 0.3f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(returnDrawerHostLayout(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Guideline guideline = view.findViewById(R.id.guideline);
        guideline.setGuidelinePercent(getGuidelineSplitPercentage());
        if (returnLeftChild() != null && returnRightChild() != null) {
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.left_child_drawer, returnLeftChild())
                    .replace(R.id.right_child_drawer, returnRightChild())
                    .commit();
        }
        mFragmentsViewModel.getClickReaction().observe(this, v-> mViewModel.setClickReaction());
    }
}