package bobby.hobby.hel.hel_project.base.ui.fragment;


import android.arch.lifecycle.ViewModelProviders;
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

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.ui.viewmodel.BaseViewModel;

public abstract class BaseTabHostFragment<T extends BaseViewModel, V extends BaseViewModel> extends BaseFragment<V> {
    protected T mTabViewModel;

    protected Class<T> returnTabViewModel() {
        return null;
    }

    protected abstract void setUpAdater(Adapter adater);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (returnTabViewModel() != null) {
            mTabViewModel = ViewModelProviders.of(this).get(returnTabViewModel());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_base_tab_host, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        setUpAdater(adapter);
        viewPager.setAdapter(adapter);
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
