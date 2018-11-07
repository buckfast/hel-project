package bobby.hobby.hel.hel_project.base.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import bobby.hobby.hel.hel_project.base.view.recyclerview.chat.BaseChatAdapter;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

/**
 * Description: This class setup recyclerview for a chat fragment
 * Works with:
 * {@link BaseChatAdapter}
 */

public abstract class BaseChatFragment<T extends BaseViewModel> extends BaseFragment<T> {
    protected RecyclerView recyclerView;
    protected abstract int returnRecyclerViewId();
    protected abstract BaseChatAdapter returnAdapter();
    protected BaseChatAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(returnRecyclerViewId());
        adapter = returnAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(adapter.getItemCount() -1);
    }
}
