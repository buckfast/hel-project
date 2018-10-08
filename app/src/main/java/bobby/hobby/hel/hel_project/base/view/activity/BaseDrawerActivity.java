package bobby.hobby.hel.hel_project.base.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.Objects;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.fragment.BaseFragment;
import bobby.hobby.hel.hel_project.base.viewmodel.BaseViewModel;

public abstract class BaseDrawerActivity<T extends BaseViewModel> extends BaseActivity<T> {
    private DrawerLayout mDrawerLayout;

    protected int getToolbarTitle() {
        return -1;
    }

    protected abstract void accountButtonClicked();

    protected abstract BaseFragment returnNavViewFragment();

    protected abstract int drawerDirection();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        setUpToolbar(toolbar);
        setUpNavigationView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                openDrawer();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpNavigationView() {
        if (returnNavViewFragment() != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_view_container, returnNavViewFragment()).commit();
        }
    }

    private void setUpToolbar(Toolbar toolbar) {
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

    protected void openDrawer() {
        mDrawerLayout.openDrawer(drawerDirection());
    }

    protected void closeDrawer() {
        mDrawerLayout.closeDrawer(drawerDirection());
    }
}
