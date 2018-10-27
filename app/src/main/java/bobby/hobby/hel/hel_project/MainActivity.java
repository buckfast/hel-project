package bobby.hobby.hel.hel_project;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.view.GravityCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}
