package bobby.hobby.hel.hel_project.base.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.ui.viewmodel.BaseViewModel;

public abstract class BaseDrawerActivity<T extends BaseViewModel> extends BaseActivity<T> {

    protected int getToolbarTitle() {
        return -1;
    }

    protected abstract void accountButtonClicked();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        Objects.requireNonNull(actionbar).setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        if (getToolbarTitle() != -1) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            toolbar.setTitle(getTitle());
        } else {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        ImageView accountButton = findViewById(R.id.account_button);
        accountButton.setOnClickListener(v -> {
            accountButtonClicked();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
