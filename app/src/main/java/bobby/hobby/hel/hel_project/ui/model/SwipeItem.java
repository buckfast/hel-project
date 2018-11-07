package bobby.hobby.hel.hel_project.ui.model;

import bobby.hobby.hel.hel_project.base.view.recyclerview.RecyclerItem;

public class SwipeItem implements RecyclerItem {
    private String text;
    public SwipeItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
