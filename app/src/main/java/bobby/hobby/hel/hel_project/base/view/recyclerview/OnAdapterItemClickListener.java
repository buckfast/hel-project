package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.view.View;

/**
 * Description: This interface defy the click behaviour in recyclerview
 * Works with:
 * {@link BaseAdapter}
 * {@link BaseAdapterViewHolder}
 */

public interface OnAdapterItemClickListener {
    void onClick(View v, int position);
}