package bobby.hobby.hel.hel_project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Util {
    public static void changeBgColor(Context c, View v, int color) {
        v.setBackgroundColor(ResourcesCompat.getColor(c.getResources(), color, null));
    }
    public static void changeTextColor(TextView v, int color) {
        v.setTextColor(color);
    }

    public static void setTintMode(Context c, ImageView v, PorterDuff.Mode mode, int color) {
        //v.setImageTintMode(mode);
        //v.setColorFilter(color);
        v.setColorFilter(ContextCompat.getColor(c, color), mode);
    }

    public static void disableTint(ImageView v) {
        v.setColorFilter(null);
        v.setImageTintMode(null);
    }

    public static Animation transformMargin(View view, int amount, int duration, int direction) {
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                if (direction == 0) {
                    params.rightMargin = (int) (amount * interpolatedTime) + (-amount);
                } else {
                    params.rightMargin = (int) -(amount * interpolatedTime);
                }
                view.setLayoutParams(params);
            }
        };
        a.setDuration(duration);
        return a;
    }
    public static void hideKeyboard(Activity activity) {

    }

    public static List<String> parseHtml(String text) {
        List<String> parsed = new ArrayList<>();
        Document doc = Jsoup.parse(text);
        Elements ptags = doc.select("p");
        for (Element ptag : ptags) {
            parsed.add(ptag.ownText());
        }
        return parsed;
    }

    public static String getTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);
    }

}