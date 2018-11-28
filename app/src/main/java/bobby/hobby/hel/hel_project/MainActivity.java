package bobby.hobby.hel.hel_project;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bobby.hobby.hel.hel_project.base.API.BaseClient;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseListAdapter;
import bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.repository.Repository;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.container);
        SwipeRefreshLayout refreshLayout = findViewById(R.id.swipe);
        Button btn = findViewById(R.id.search);

        User newUser = new User();
        newUser.setEmail("hoangl2@gmail.com");
        newUser.setPassword("hoangl2@gmail.com");
        Repository.getInstance(getApplication()).login(newUser, new BaseClient.Handler<User>() {
            @Override
            public void onSuccess(@NonNull User response, int code) {
                ListViewModel viewModel = ViewModelProviders.of(MainActivity.this).get(ListViewModel.class);
                ListAdapter adapter = new ListAdapter((v, position) -> Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show());
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                viewModel.getLiveList().observe(MainActivity.this, list -> {
                    adapter.submitList(list);
                    refreshLayout.setRefreshing(false);
                });

                refreshLayout.setOnRefreshListener(viewModel::refreshData);

                btn.setOnClickListener(view -> {
                    viewModel.populateData("Musiikki");
                });
            }

            @Override
            public void onError(@Nullable ResponseBody body, int code) {

            }
        });
    }
    private class ListViewHolder extends BaseAdapterViewHolder {
        public TextView id;

        public ListViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            id = itemView.findViewById(R.id.id);
        }

        public void bind(Event event) {
            id.setText(event.toString());
        }
    }

    private class ListAdapter extends BaseListAdapter<ListViewHolder> {
        protected ListAdapter(OnAdapterItemClickListener listener) {
            super(listener);
        }

        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false), mListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
            Event event = getItem(position);
            if (event != null) {
                holder.bind(event);
            }
        }
    }
}
