package bobby.hobby.hel.hel_project.ui.model;

import bobby.hobby.hel.hel_project.base.view.recyclerview.RecyclerItem;

public class EventItem implements RecyclerItem{
    String title;
    int image;
    String desc;

    public EventItem(String title, int image, String desc) {
        this.title = title;
        this.image = image;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
