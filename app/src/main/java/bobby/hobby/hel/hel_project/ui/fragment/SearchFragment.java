package bobby.hobby.hel.hel_project.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.FragmentViewModel;

/**
 * Description: Fragment for adding more hobbies to account
 * Features:
 * - Hold searchview for searching hobbies (not in use)
 * - Creates swipe cards for searching more hobbies
 */
public class SearchFragment extends BaseFragment<FragmentViewModel> implements BaseFragment.LongRunningTaskBehaviour {
    private android.support.v7.widget.SearchView searchView;
    private String lastKeyword;

    public SearchFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.longRunningTask(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchview);
        searchView.clearFocus();

        /*
        mViewModel.foundLinkedEvents.observe(getActivity(), new Observer<EventList>() {
            @Override
            public void onChanged(@Nullable EventList eventList) {
                if (eventList != null) {
                    events_found.setText(eventList.getCount()+" events found for keyword "+lastKeyword);
                } else {
                    events_found.setText("No events found for keyword "+lastKeyword);
                }
            }
        });

*/
        Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.swipe_container, new SearchPageSwipeFragment()).commit();


        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                lastKeyword = query;
                //mViewModel.checkLinkedEvents(query);
                //mViewModel.longRunningTask(true);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected Class<FragmentViewModel> returnViewModel() {
        return FragmentViewModel.class;
    }


    @Override
    public int returnProgressBarContainer() {
        return R.id.search_progressbar_container;
    }

    @Override
    public Fragment returnProgressBarFragment() {
        return new ProgressBarFragment();
    }

    @Override
    protected LongRunningTaskBehaviour returnLongRunningTaskBehaviour() {
        return this;
    }
}
