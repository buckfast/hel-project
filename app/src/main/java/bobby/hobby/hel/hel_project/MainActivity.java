package bobby.hobby.hel.hel_project;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.Objects;

import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.AuthActivity;
import bobby.hobby.hel.hel_project.ui.fragment.DrawerHostFragment;
import bobby.hobby.hel.hel_project.ui.fragment.SearchFragment;
import bobby.hobby.hel.hel_project.ui.fragment.TabHostFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;

public class MainActivity extends BaseDrawerActivity<ActivityViewModel> {

    @Override
    protected void accountButtonClicked() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);

        MenuItem top = menu.getItem(0);
        Observer userObserver = new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.d("asd", "mainactivity: currentuser:" + user.getName());
                top.setTitle(user.getName());
            }
        };
        mViewModel.currentUser.observe(this, userObserver);


        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.logout:
                Log.d("asd", "menuclick");
                logout();
                return true;
            case android.R.id.home:
                Fragment f = getSupportFragmentManager().findFragmentByTag("search_fragment");
                if (f != null) {
                    Log.d("asd", "search fragment visible");
                } else {
                    openDrawer();
                    Util.hideKeyboard(this, null);
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


    private void logout() {
        mViewModel.logout();
        Intent intent = new Intent(this, AuthActivity.class);
        //intent.putExtra("logout", true);
        startActivity(intent);


    }


    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }

        return super.onPrepareOptionsPanel(view, menu);
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
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentByTag("search_fragment");
        if (f != null) {
            getSupportFragmentManager().popBackStackImmediate();
            //mViewModel.title.setValue(mViewModel.getT);
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            Toast.makeText(this, "keyboard visible", Toast.LENGTH_SHORT).show();
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            Toast.makeText(this, "keyboard hidden", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //invalidateOptionsMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit();

        TextView title = findViewById(R.id.toolbar_title);
        mViewModel.getTitle().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Fragment f = getSupportFragmentManager().findFragmentByTag("search_fragment");
                if (f == null) {
                    title.setText(s);
                }
            }
        });


        mViewModel.hostViewCreated.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    ImageButton search_button;
                    search_button = findViewById(R.id.search_button);
                    search_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            closeDrawer();
                            title.setText(getResources().getString(R.string.search_title));
                            Objects.requireNonNull(getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment(), "search_fragment").addToBackStack(null).commit());
                        }
                    });
                }
            }
        });
    }

}
