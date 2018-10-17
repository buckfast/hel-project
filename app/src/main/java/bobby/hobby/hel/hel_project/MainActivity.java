package bobby.hobby.hel.hel_project;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;
import bobby.hobby.hel.hel_project.ui.ActivityViewModel;
import bobby.hobby.hel.hel_project.ui.DrawerHostFragment;
import bobby.hobby.hel.hel_project.ui.TabHostFragment;

public class MainActivity extends BaseDrawerActivity<ActivityViewModel> {

    @Override
    protected void accountButtonClicked() {

    }

    @Override
    protected NavigationDrawerLayout returnNavViewLayout() {
        return new DrawerHostFragment();
    }

    @Override
    protected int drawerDirection() {
        return GravityCompat.START;
    }

    @Override
    protected Class<ActivityViewModel> returnViewModel() {
        return ActivityViewModel.class;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();

        //ActionBar actionbar = getSupportActionBar();
        //getSupportActionBar().setDisplayOptions(actionbar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.action_bar);

        TextView title = findViewById(R.id.toolbar_title);
        mViewModel.getTitle().observe(this, s -> title.setText(s));
    }

}
