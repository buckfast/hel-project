package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;

public class DrawerChildTwoFragment extends BaseNavViewListChildFragment<FragmentToFragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navview_child_two, container, false);
    }

    @Override
    protected Class<FragmentToFragmentViewModel> returnFragmentsViewModel() {
        return FragmentToFragmentViewModel.class;
    }

    @Override
    protected BaseAdapter setUpAdapter() {
        return null;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.result);
        textView.setOnClickListener(v-> {
//            Intentionally ask to close the drawer when click, no more isCloseable(), then this, too much repetitive
            mFragmentsViewModel.setClickReaction();
        });
        mFragmentsViewModel.position.observe(this, position -> {
            textView.setText(Integer.toString(position));
        });
    }
}
