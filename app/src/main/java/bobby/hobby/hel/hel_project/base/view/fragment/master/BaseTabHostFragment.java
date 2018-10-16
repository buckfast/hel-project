package bobby.hobby.hel.hel_project.base.view.fragment.master;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseHostFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseTabChildFragment;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseTabHostFragment<T extends BaseViewModel, V extends BaseViewModel> extends BaseHostFragment<T, V> {
    private ViewPager mViewPager;
    protected abstract void setUpAdater(Adapter adater);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_tab_host, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = view.findViewById(R.id.viewpager);
        setupViewPager();
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(mFragmentsViewModel.getCurrentPosition());
    }

    private void setupViewPager() {
        Adapter adapter = new Adapter(getChildFragmentManager());
        setUpAdater(adapter);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {mFragmentsViewModel.setCurrentPosition(position);}

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    public static class Adapter<V extends BaseTabChildFragment> extends FragmentPagerAdapter {
        private final List<V> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(V fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
