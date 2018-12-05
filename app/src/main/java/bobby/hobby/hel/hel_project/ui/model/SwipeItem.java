package bobby.hobby.hel.hel_project.ui.model;

import bobby.hobby.hel.hel_project.base.view.recyclerview.RecyclerItem;

public class SwipeItem implements RecyclerItem {
    private String text;
    private String imageUrl;
    public SwipeItem(String text, String imageUrl) {
        this.text = text;this.imageUrl=imageUrl;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
