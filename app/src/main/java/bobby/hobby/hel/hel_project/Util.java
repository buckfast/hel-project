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

    public static void recursiveHideKeyboard(final Context context, View view) {
        try {
            if (!(view instanceof EditText || view instanceof ScrollView)) {
                view.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {
                        InputMethodManager in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        return false;
                    }
                });
            }
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View innerView = ((ViewGroup) view).getChildAt(i);
                    recursiveHideKeyboard(context, innerView);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}