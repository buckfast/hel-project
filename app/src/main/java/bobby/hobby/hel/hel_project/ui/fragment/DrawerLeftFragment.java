package bobby.hobby.hel.hel_project.ui.fragment;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.Util;
import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;
import bobby.hobby.hel.hel_project.base.view.fragment.detail.BaseNavViewListChildFragment;
import bobby.hobby.hel.hel_project.ui.model.DrawerListItem;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

/**
 * Description: Left side child fragment in host drawer fragment
 * Features:
 * - Observers and handles list of hobby items user has subscribed
 */
public class DrawerLeftFragment extends BaseNavViewListChildFragment<FragmentViewModel> {

    private TypedArray colors;
    private BaseDrawerActivity context;
    private int i;

    @Override
    protected Class<FragmentViewModel> returnFragmentsViewModel() {
        return FragmentViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer_child_left, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        List<DrawerListItem> array = new ArrayList<>();
        array.add(new DrawerListItem("Pesäpallo", "@mipmap/ic_launcher_round"));
        array.add(new DrawerListItem("Jalkapallo", "@drawable/ic_launcher_foreground"));
        array.add(new DrawerListItem("Playstation", "@mipmap/ic_launcher_round"));
        array.add(new DrawerListItem("kekkonen", "@drawable/ic_launcher_foreground"));
        mFragmentsViewModel.drawerList.setValue(array);
        */

/*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("asd", "drawerleft; "+String.valueOf(mFragmentsViewModel.currentUser.getValue()));
            }
        }, 10000);
        */

        // TODO: hehe
        mFragmentsViewModel.hobbyList.observe(context, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                Log.d("asd", "drawerleft hobbilist changed, new: " + strings);
                List<DrawerListItem> newHobbyList = new ArrayList<>();
                for (int i = 0; i < strings.size(); i++) {
                    //Log.d("asd", "user hobby: "+strings.get(i));
                    newHobbyList.add(new DrawerListItem(strings.get(i), "@mipmap/ic_launcher_round"));
                }
                mFragmentsViewModel.drawerList.setValue(newHobbyList);
            }
        });

        colors = getContext().getResources().obtainTypedArray(R.array.super_colors);

        /*if (mFragmentsViewModel.lastView.getValue() != null) {
            Log.d("asd", "hehhe");
            Util.changeBgColor(getContext(), mFragmentsViewModel.lastView.getValue(), R.color.colorAccent);
            Util.changeBgColor(getContext(), mFragmentsViewModel.lastView.getValue().findViewById(R.id.list_item_margin_line), R.color.colorAccentDarker);
        }*/
    }

    @Override
    protected BaseAdapter setUpAdapter() {
        return new DrawerLeftAdapter((v, position) -> {
            mFragmentsViewModel.listPosition.setValue(position);
            mFragmentsViewModel.setCurrentPositionDrawer(position);


            Util.changeBgColor(getContext(), v, R.color.colorAccent);
            Util.changeBgColor(getContext(), v.findViewById(R.id.list_item_margin_line), R.color.colorAccentDarker);
            Util.changeBgColor(getContext(), v.findViewById(R.id.list_item_margin_line_right), R.color.transparent);

            //Util.changeTextColor(getContext(), v.findViewById(R.id.item_text), R.color.colorWhite);
            Util.disableTint(v.findViewById(R.id.image));

            View lv = mFragmentsViewModel.lastView.getValue();
            if (lv == null) {
                mFragmentsViewModel.lastView.setValue(v);
            } else {
                if (lv != v) {
                    Util.changeBgColor(getContext(), lv, R.color.colorAccentDark);
                    Util.changeBgColor(getContext(), lv.findViewById(R.id.list_item_margin_line), R.color.colorAccentDark);
                    Util.changeBgColor(getContext(), lv.findViewById(R.id.list_item_margin_line_right), R.color.colorAccentDarker);

                    //Util.changeTextColor(getContext(), lv.findViewById(R.id.item_text), R.color.colorGray);
                    Util.setTintMode(getContext(), lv.findViewById(R.id.image), PorterDuff.Mode.MULTIPLY, R.color.colorGray);
                    mFragmentsViewModel.lastView.setValue(v);
                }
            }
        }, this);
    }

    @Override
    protected int returnRecyclerViewId() {
        return R.id.recycler_view;
    }

    private class DrawerLeftChildViewHolder extends BaseAdapterViewHolder {
        public TextView textView;
        public ImageView imageView;
        private LinearLayout marginRight;

        DrawerLeftChildViewHolder(View itemView, OnAdapterItemClickListener listener) {
            super(itemView, listener);
            textView = itemView.findViewById(R.id.item_text);
            imageView = itemView.findViewById(R.id.image);
            marginRight = itemView.findViewById(R.id.list_item_margin_line_right);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (BaseDrawerActivity) context;
    }

    private class DrawerLeftAdapter extends BaseAdapter<DrawerLeftChildViewHolder> {
        private List<DrawerListItem> listData;

        DrawerLeftAdapter(OnAdapterItemClickListener listener, Fragment context) {
            super(listener);
            listData = new ArrayList<>();
            mFragmentsViewModel.drawerList.observe(context, this::refreshData);
        }

        private void refreshData(List<DrawerListItem> data) {
            listData.clear();
            listData.addAll(data);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public DrawerLeftChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_drawer_list_item, parent, false);
            //Util.changeBgColor(getContext(),v.findViewById(R.id.list_item_container),colors.getResourceId(i%colors.length(),0));
            //i++;
            return new DrawerLeftChildViewHolder(v, mListener);
        }

        @Override
        public void onBindViewHolder(@NonNull DrawerLeftChildViewHolder holder, int position) {
            holder.textView.setText(listData.get(position).tv);
            holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
            Util.changeBgColor(getContext(), holder.marginRight, R.color.colorAccentDarker);
            Util.setTintMode(getContext(), holder.imageView, PorterDuff.Mode.MULTIPLY, R.color.colorGray);

            //holder.imageButton.setImageResource(mData.get(position).ib);
        }

        @Override
        public int getItemCount() {
            return listData.size();
        }
    }


}
