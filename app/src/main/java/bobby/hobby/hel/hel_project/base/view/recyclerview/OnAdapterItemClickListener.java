package bobby.hobby.hel.hel_project.base.view.recyclerview;

import android.view.View;

/**
 * Description: This interface decide the method for when an item in recyclerview is clicked
 * Works with:
 * {@link BaseAdapter}
 * {@link BaseAdapterViewHolder}
 */

public interface OnAdapterItemClickListener {
    void onClick(View v, int position);
}