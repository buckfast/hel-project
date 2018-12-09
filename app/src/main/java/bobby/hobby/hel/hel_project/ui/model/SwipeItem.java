package bobby.hobby.hel.hel_project.ui.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwipeItem swipeItem = (SwipeItem) o;
        return Objects.equals(text, swipeItem.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text);
    }
}
