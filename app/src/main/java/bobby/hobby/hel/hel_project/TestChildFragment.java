package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseTabChildFragment;

public class TestChildFragment extends BaseTabChildFragment<FragmentToActivityViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = view.findViewById(R.id.button1);
        TextView txt = view.findViewById(R.id.result1);

        btn.setOnClickListener(v -> {
            if (Objects.equals(mFragmentsViewModel.data.getValue(), "Hello")) {
                mFragmentsViewModel.data.setValue("World!");
            } else {
                mFragmentsViewModel.data.setValue("Hello");
            }
        });

        mFragmentsViewModel.data.observe(this, txt::setText);
    }

    @Override
    protected Class<FragmentToActivityViewModel> returnFragmentsViewModel() {
        return FragmentToActivityViewModel.class;
    }
}
