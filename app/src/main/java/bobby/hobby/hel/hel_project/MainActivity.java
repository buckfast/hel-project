package bobby.hobby.hel.hel_project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.widget.Toast;

import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;

public class MainActivity extends BaseDrawerActivity<FragmentToActivityViewModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TestFragment()).commit();
    }

    @Override
    protected Class<FragmentToActivityViewModel> returnViewModel() {
        return FragmentToActivityViewModel.class;
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.app_name;
    }

    @Override
    protected void accountButtonClicked() {

    }

    @Override
    protected NavigationDrawerLayout returnNavViewLayout() {
        return new DrawerLayoutFragment();
    }

    @Override
    protected int drawerDirection() {
        return GravityCompat.START;
    }
}
