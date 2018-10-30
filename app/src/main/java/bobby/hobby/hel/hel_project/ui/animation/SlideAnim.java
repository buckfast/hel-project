package bobby.hobby.hel.hel_project.ui.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class SlideAnim extends Animation {
    private View v;

    private int startHeight;
    private int endHeight;

    public SlideAnim(View v, int startHeight, int endHeight) {
        this.v = v;
        this.startHeight = startHeight;
        this.endHeight = endHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        int newHeight;

        if (v.getHeight() != endHeight) {
            newHeight = (int) (startHeight + ((endHeight - startHeight) * interpolatedTime));
            v.getLayoutParams().height = newHeight;
            v.requestLayout();
        }
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}

