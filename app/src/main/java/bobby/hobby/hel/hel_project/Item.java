package bobby.hobby.hel.hel_project;

import bobby.hobby.hel.hel_project.base.view.recyclerview.RecyclerItem;

public class Item implements RecyclerItem {
    public String text;
    public Item(String item) {
        text = item;
    }
}
