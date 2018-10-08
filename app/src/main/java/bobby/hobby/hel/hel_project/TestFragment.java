package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import bobby.hobby.hel.hel_project.base.view.fragment.master.BaseTabHostFragment;

public class TestFragment extends BaseTabHostFragment<FragmentToFragmentViewModel, FragmentToActivityViewModel> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.data.observe(this, s -> {
            Toast.makeText(getActivity(), "From fragment: "+s, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void setUpAdater(Adapter adater) {
        TestChildFragment fragment1 = new TestChildFragment();
        TestChildFragment2 fragment2 = new TestChildFragment2();
        fragment1.returnHostFragment(this);
        fragment2.returnHostFragment(this);
        adater.addFragment(fragment1, "Chat");
        adater.addFragment(fragment2, "Events");
    }

    @Override
    protected Class<FragmentToFragmentViewModel> returnFragmentsViewModel() {
        return FragmentToFragmentViewModel.class;
    }

    @Override
    protected Class<FragmentToActivityViewModel> returnViewModel() {
        return FragmentToActivityViewModel.class;
    }
}
