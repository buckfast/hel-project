package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseTabHostFragment;

public class TestFragment extends BaseTabHostFragment<FragmentToFragmentTabViewModel, FragmentToActivityViewModel> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.dataAccross.observe(this, data -> {
            mFragmentsViewModel.dataAccross.setValue(data);
        });
    }

    @Override
    protected void setUpAdater(Adapter adater) {
        TestChildFragment fragment1 = new TestChildFragment();
        TestChildFragment2 fragment2 = new TestChildFragment2();
        adater.addFragment(fragment1, "Chat");
        adater.addFragment(fragment2, "Events");
    }

    @Override
    protected Class<FragmentToFragmentTabViewModel> returnFragmentsViewModel() {
        return FragmentToFragmentTabViewModel.class;
    }

    @Override
    protected Class<FragmentToActivityViewModel> returnViewModel() {
        return FragmentToActivityViewModel.class;
    }
}
