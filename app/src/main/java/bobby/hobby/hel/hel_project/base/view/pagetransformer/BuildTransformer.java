package bobby.hobby.hel.hel_project.base.view.pagetransformer;

import android.support.v4.view.ViewPager;

import java.lang.reflect.InvocationTargetException;

public final class BuildTransformer {
    private BuildTransformer() {}
    private static final String PACKAGE_PATH = "bobby.hobby.hel.hel_project.base.view.pagetransformer.transformer.";

    public enum Type {
        AntiClockSpinTransformation, ClockSpinTransformation, CubeInDepthTransformation, CubeInScalingTransformation,
        CubeInTransformation, CubeOutDepthTransformation, CubeOutRotationTransformation, CubeOutScalingTransformation,
        DepthTransformation, FadedOutTransformation, FanTransformation, FidgetSpinTransformation, HingeTransformation,
        HorizontalFlipTransformation, PopTransformation, SpinnerTransformation, TossTransformation, VerticalFlipTransformation,
        VerticalShutTransformation, ZoomOutTransformation
    }

    public static ViewPager.PageTransformer getPageTransformerOf(Type type) {
        try {
            return (ViewPager.PageTransformer) Class.forName(PACKAGE_PATH+type.toString()).getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
