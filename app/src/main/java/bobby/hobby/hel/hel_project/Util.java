package bobby.hobby.hel.hel_project;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;

public final class Util {
    public static void changeBgColor(Context c, View v, int color) {
        v.setBackgroundColor(ResourcesCompat.getColor(c.getResources(), color, null));
    }
}