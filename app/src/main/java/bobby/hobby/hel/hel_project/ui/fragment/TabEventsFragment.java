package bobby.hobby.hel.hel_project.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseProgressBarFragment;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseTabChildFragment;
import bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapterViewHolder;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.Event;
import bobby.hobby.hel.hel_project.repository.internal.model.eventlist.EventList;
import bobby.hobby.hel.hel_project.ui.GlideApp;
import bobby.hobby.hel.hel_project.ui.intterfase.AsyncListener;
import bobby.hobby.hel.hel_project.ui.intterfase.OnAdapterItemClickListener;
import bobby.hobby.hel.hel_project.ui.model.EventItem;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

public class TabEventsFragment extends BaseTabChildFragment<FragmentViewModel> implements bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener, BaseFragment.LongRunningTaskBehaviour, BaseFragment.SwipeToRefreshBehaviour {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private int prevExpanded = -1;
    private int currExpanded = -1;
    private boolean isExpanded = false;
    private int again = 0;
    private int[] backgrounds = {R.drawable.event_placeholder_image, R.drawable.event_placeholder_image2, R.drawable.event_placeholder_image3};

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

        /*mFragmentsViewModel.currentUser.observe(this, u -> {
            Log.d("asd", u.getToken());
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_events, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.pu
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //eventList = new ArrayList<>();

        recyclerView = (RecyclerView) getView().findViewById(R.id.events_recycler_view);
        recyclerView.setHasFixedSize(true);

        //recyclerView.setItemAnimator(new SlideInDownAnimator());
        /*RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }*/


        adapter = new EventAdapter(this,eventList, this);

        adapter.setHasStableIds(true);

        //((EventAdapter)adapter).refreshData(mFragmentsViewModel.linkedEvents.getValue());
        mFragmentsViewModel.linkedEvents.observe(getActivity(), data ->{
            Log.d("asd", "tabeventsfragment linkedevets observer::reefreh");
            mFragmentsViewModel.title.setValue(mFragmentsViewModel.getTitle(mFragmentsViewModel.getCurrentPositionDrawer()));
            isExpanded = false;
            currExpanded = -1;
            prevExpanded = -1;
            ((EventAdapter)adapter).refreshData(data);
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //mFragmentsViewModel.getHobbyByPosition(mFragmentsViewModel.getCurrentPositionDrawer());
        //mFragmentsViewModel.searchLinkedEvents("jalkapallo");

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
        if (currExpanded == position) {
            again++;
        } else {
            currExpanded = position;
            recyclerView.getLayoutManager().scrollToPosition(position);
        }

        Log.d("asd", "curexapanded: "+currExpanded+",  "+"prevExapande: "+prevExpanded+",   "+"isexnapde: "+isExpanded);


        adapter.notifyItemChanged(position);
        if (prevExpanded != currExpanded) {
            adapter.notifyItemChanged(prevExpanded);
        }







        //Log.d("asd", "curexapanded: "+currExpanded+",  "+"prevExapande: "+prevExpanded+",   "+"isexnapde: "+isExpanded);
        /*if (v.isActivated()) {
            Animation anim = new SlideAnim(
                    v.findViewById(R.id.event_top_container),
                    (int)getResources().getDimension(R.dimen.event_top_container_min_height),
                    (int)getResources().getDimension(R.dimen.event_top_container_max_height)
            );
            anim.setInterpolator(new AccelerateInterpolator());
            anim.setDuration(0);
            v.setAnimation(anim);
            v.startAnimation(anim);
        }*/

    }

    @Override
    public int returnProgressBarContainer() {
        return R.id.events_progressbar_container;
    }

    @Override
    public Fragment returnProgressBarFragment() {
        return new ProgressBarFragment();
    }

    @Override
    protected LongRunningTaskBehaviour returnLongRunningTaskBehaviour() {
        return this;
    }

    @Override
    protected SwipeToRefreshBehaviour returnSwipeToRefresh() {
        return this;
    }

    @Override
    public int returnSwipeRefreshLayout() {
        return R.id.swipe_refresh;
    }

    @Override
    public void refresh(SwipeRefreshLayout layout) {
        if(layoutManager.findFirstCompletelyVisibleItemPosition()==0) {
            mFragmentsViewModel.searchLinkedEvents(mFragmentsViewModel.getHobbyByPosition(mFragmentsViewModel.getCurrentPositionDrawer()));
        }

    }

    private class EventAdapter extends bobby.hobby.hel.hel_project.base.view.recyclerview.BaseAdapter<EventAdapter.ViewHolder, EventItem> implements AsyncListener {

        private Context c;
        private EventList eventList;
        //private final OnAdapterItemClickListener listener;

        //public EventAdapter(Fragment context, EventList eventList, OnAdapterItemClickListener listener) {
        public EventAdapter(Fragment context, EventList eventList, bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener listener) {
            super(listener);
            this.eventList = eventList;
            //this.listener = listener;
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

        @Override
        public void finish(Bitmap image, int id) {
            ImageView iv = (ImageView) getView().findViewById(id);
            iv.setImageBitmap(image);
        }

        @Override
        public void preDownload(int id) {

        }

        //public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //public class ViewHolder extends RecyclerView.ViewHolder {
        public class ViewHolder extends BaseAdapterViewHolder {

            private TextView title, short_desc, short_desc_real, desc, info;
            public ImageView image;
            private RelativeLayout details_container, top_container;
            private RecyclerView.Adapter adapter;


            //public ViewHolder(View view, RecyclerView.Adapter adapter) {
            public ViewHolder(View view, RecyclerView.Adapter adapter, bobby.hobby.hel.hel_project.base.view.recyclerview.OnAdapterItemClickListener listener) {
                super(view, listener);
                //super(view);
                this.adapter = adapter;
                title = (TextView) view.findViewById(R.id.event_title);
                short_desc = (TextView) view.findViewById(R.id.event_short_desc);
                short_desc_real = (TextView) view.findViewById(R.id.event_short_desc_real);

                image = (ImageView) view.findViewById(R.id.event_image);
                desc = view.findViewById(R.id.event_details_desc);
                details_container = view.findViewById(R.id.event_details);
                top_container = view.findViewById(R.id.event_top_container);
                info = view.findViewById(R.id.event_details_info);
               // view.setOnClickListener(this);
            }

            /*@Override
            public void onClick(View view) {
                listener.onClick(view, this.getAdapterPosition());
            }
            */
        }


        @Override
       // public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_tab_events_list_item, parent, false);
            //return new ViewHolder(itemView, adapter);
            return new ViewHolder(itemView, adapter, mListener);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Event event = eventList.getEvents().get(position);
            holder.title.setText(event.getName().getFi());
            if (event.getSDesc() != null && event.getSDesc().getFi() != null) {
                //Log.d("asd", "sdesc: "+position+": "+event.getSDesc().getFi());
                holder.short_desc.setText(Html.fromHtml(event.getSDesc().getFi(), 0));
                holder.short_desc_real.setText(Html.fromHtml(event.getSDesc().getFi(), 0));
            } else {
                if (event.getName().getFi() != null) {
                    holder.short_desc.setText(event.getName().getFi());
                    holder.short_desc_real.setText(event.getName().getFi());
                }
            }
            holder.short_desc.setVisibility(View.INVISIBLE);
            holder.desc.setText(Html.fromHtml("<h1>"+event.getName().getFi()+"</h1>"+event.getDesc().getFi(),0));
            holder.desc.setMovementMethod(LinkMovementMethod.getInstance());
            holder.desc.setLinkTextColor(ContextCompat.getColor(getContext(),R.color.colorAccent));


            if (event.getInfo() != null) {
                holder.info.setVisibility(View.VISIBLE);
                final Spanned text = Html.fromHtml("<a href='" + event.getInfo().getFi()+"'>LISÄTIETOJA</a>", 0);
                holder.info.setMovementMethod(LinkMovementMethod.getInstance());
                holder.info.setText(text);
                holder.info.setLinkTextColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
            } else {
                holder.info.setVisibility(View.GONE);
            }

            final Boolean containsImages = (event.getImages().size() > 0);

            holder.image.setImageBitmap(null);
            if (containsImages) {
                /*String[] params = {
                        event.getImages().get(0).getUrl(),
                        String.valueOf(holder.image.getId())
                };
                LoadImage imageLoader = new LoadImage();
                imageLoader.response = this;
                imageLoader.execute(params);
                */
                GlideApp.with(getContext()).load(event.getImages().get(0).getUrl()).into(holder.image);
            } else {
                holder.image.setBackgroundResource(backgrounds[position%3]);
            }


            /*
                if (position % 3 == 0) {
                    holder.itemView.findViewById(R.id.top_gradient).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.top_gradient));
                } else if (position % 3 == 1) {
                    holder.itemView.findViewById(R.id.top_gradient).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.top_gradient2));
                } else if (position % 3 == 2) {
                    holder.itemView.findViewById(R.id.top_gradient).setBackground(ContextCompat.getDrawable(getContext(), R.drawable.top_gradient3));
                }
             */


            isExpanded = position==currExpanded;
            if (again == 1) {
                isExpanded = false;
                currExpanded = -1;
                again = 0;
            }
            holder.title.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
            holder.details_container.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            holder.short_desc_real.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
            holder.itemView.setActivated(isExpanded);
            if (isExpanded) {
                /*if (containsImages) {
                    Util.resetViewDrawable(holder.itemView.findViewById(R.id.top_gradient));
                } else {
                }*/
                prevExpanded = position;
            } else {
                again = 0;
            }
            //holder.bind(event, listener);
        }

        @Override
        public long getItemId(int position) {
           //return Long.parseLong(eventList.getEvents().get(position).getId().replace("helmet:", ""));
            return position; // TODO: 29.10.2018 get unique long id somewhere some day 
        }

        @Override
        public int getItemCount() {
            return eventList == null ? 0 : eventList.getEvents().size();
        }
    }

    /*
    @Override
    public void finish(Bitmap image) {
        ImageView iv = (ImageView) getView().findViewById(id);
        iv.setImageBitmap(result);
    }*/

    private static class LoadImage extends AsyncTask<String, Void, Bitmap> {
        private int id = 0;
        public AsyncListener response = null;

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                id = Integer.parseInt(params[1]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap image = BitmapFactory.decodeStream(input);
                return image;

            }catch (Exception e){
                Log.d("asd",e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            response.preDownload(id);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            response.finish(result, id);
        }
    }

}
