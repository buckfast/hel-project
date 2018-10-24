package bobby.hobby.hel.hel_project.base.view.fragment.master;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import bobby.hobby.hel.hel_project.base.view.fragment.BaseHostFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseSwipeChildFragment;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseSwipeHostFragment<T extends BaseViewModel, V extends BaseViewModel, E extends BaseSwipeChildFragment> extends BaseHostFragment<T,V> {
    protected ViewPager viewPager;
    protected SwipeAdapter adapter;
    protected abstract int returnViewPagerId();
    protected abstract int returnNumberOfFragment();
    protected abstract FragmentManager returnFragmentManager();
    protected abstract Class<E> returnChildFragmentClass();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(returnViewPagerId());
        adapter = new SwipeAdapter(returnFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public class SwipeAdapter extends FragmentStatePagerAdapter {

        public SwipeAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return E.newInstance(position, returnChildFragmentClass());
        }

        @Override
        public int getCount() {
            return returnNumberOfFragment();
        }
    }
}
