package bobby.hobby.hel.hel_project.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseTabChildFragment;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import bobby.hobby.hel.hel_project.ui.intterfase.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.ui.model.EventItem;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class TabEventsFragment extends BaseTabChildFragment<FragmentViewModel> implements OnAdapterItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private EventList eventList;

    public TabEventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabEventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabEventsFragment newInstance(String param1, String param2) {
        TabEventsFragment fragment = new TabEventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mFragmentsViewModel.currentUser.observe(this, u -> {
            Log.d("asd", u.getToken());
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //eventList = new ArrayList<>();

        recyclerView = (RecyclerView) getView().findViewById(R.id.events_recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new EventAdapter(this,eventList, this);

        mFragmentsViewModel.linkedEvents.observe(this, data ->{
            ((EventAdapter)adapter).refreshData(data);
            Log.d("asd", "eventissa tapahtuu");
        });
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //mFragmentsViewModel.getHobbyByPosition(mFragmentsViewModel.getCurrentPositionDrawer());
        //mFragmentsViewModel.searchLinkedEvents("jalkapallo");
        mFragmentsViewModel.listPosition.observe(this, pos -> {
            Log.d("asd", pos+", "+mFragmentsViewModel.getHobbyByPosition(pos));
            mFragmentsViewModel.searchLinkedEvents(mFragmentsViewModel.getHobbyByPosition(pos));

        });



        /*eventList.add(new EventItem("Jalkkis", R.drawable.a342_sahly_2, "hyvä tapahtuma kannattaa tulla"));
        eventList.add(new EventItem("hands :)", R.drawable.a74_muut8,"abcdefg" ));
        eventList.add(new EventItem("Cool event", R.drawable.aaaa, "nice event, cool"));
        eventList.add(new EventItem("esfjk fe", R.drawable.a74_muut8, "fekfj"));
        eventList.add(new EventItem("sfe kejfjk s fkjs", R.drawable.a74_muut8, "kjef fj kejk skj"));
        eventList.add(new EventItem("asda", R.drawable.a342_sahly_2,"asdasdadsa"));
        eventList.add(new EventItem("asdaasd", R.drawable.aaaa, "gdthbt"));
        mFragmentsViewModel.eventList.setValue(eventList);
        */
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mFragmentsViewModel.logout();
    }

    @Override
    public void onClick(View v, int position) {
        List<EventItem> list = mFragmentsViewModel.eventList.getValue();
        list.remove(position);
        mFragmentsViewModel.eventList.setValue(list);
    }


    private class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

        private Context c;
        private EventList eventList;
        private final OnAdapterItemClickListener listener;

        public EventAdapter(Fragment context, EventList eventList, OnAdapterItemClickListener listener) {
            this.eventList = eventList;
            this.listener = listener;
        }

        public void refreshData(EventList newData) {
            //eventList = data;
            /*DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return eventList.size();
                }

                @Override
                public int getNewListSize() {
                    return newData.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return eventList.get(oldItemPosition).getTitle() ==
                            newData.get(newItemPosition).getTitle();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return eventList.get(oldItemPosition).equals(newData.get(newItemPosition));
                }
            });
            result.dispatchUpdatesTo(this);*/

            eventList = newData;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView title, short_desc;
            public ImageView image;
            public RecyclerView.Adapter adapter;


            public ViewHolder(View view, RecyclerView.Adapter adapter) {
                super(view);
                this.adapter = adapter;
                title = (TextView) view.findViewById(R.id.event_title);
                short_desc = (TextView) view.findViewById(R.id.event_short_desc);
                image = (ImageView) view.findViewById(R.id.event_image);
                view.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Log.d("testi", "klik");
                listener.onClick(view, this.getAdapterPosition());
            }

        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_tab_events_list_item, parent, false);
            return new ViewHolder(itemView, adapter);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Event event = eventList.getEvents().get(position);
            holder.title.setText(event.getName().getFi());
            holder.short_desc.setText(Util.parseHtml(event.getDesc().getFi()).get(0));

            //holder.image.setImageResource(event.getImage());

            if (position%3==0) {
                holder.itemView.findViewById(R.id.top_gradient).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.top_gradient));
            } else if (position%3==1) {
                holder.itemView.findViewById(R.id.top_gradient).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.top_gradient2));
            } else if (position%3==2 ) {
                holder.itemView.findViewById(R.id.top_gradient).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.top_gradient3));

            }

            //holder.bind(event, listener);
        }

        @Override
        public int getItemCount() {
            return eventList == null ? 0 : eventList.getEvents().size();
        }
    }

}
