package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;
import bobby.hobby.hel.hel_project.ui.FragmentViewModel;

public class DrawerRightFragment extends BaseNavViewListChildFragment<FragmentViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer_child_right, container, false);
    }

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    protected BaseAdapter setUpAdapter() {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.texti);
        textView.setOnClickListener(v -> {
//            Intentionally ask to close the drawer when click, no more isCloseable(), then this, too much repetitive
            mFragmentsViewModel.setClickReaction();
            //mFragmentsViewModel.dataAccross.setValue(textView.getText().toString());
        });
        mFragmentsViewModel.listPosition.observe(this, position -> {
            DrawerListItem data = Objects.requireNonNull(mFragmentsViewModel.drawerList.getValue().get(position));
            textView.setText(data.tv);
        });
    }
}