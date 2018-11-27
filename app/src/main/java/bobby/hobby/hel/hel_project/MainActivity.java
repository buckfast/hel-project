package bobby.hobby.hel.hel_project;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

import java.lang.reflect.Method;
import java.util.Objects;


import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;
import bobby.hobby.hel.hel_project.repository.internal.model.User;
import bobby.hobby.hel.hel_project.ui.AuthActivity;
import bobby.hobby.hel.hel_project.ui.fragment.LoginFragment;
import bobby.hobby.hel.hel_project.ui.fragment.SearchFragment;
import bobby.hobby.hel.hel_project.ui.fragment.TabHostFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;
import bobby.hobby.hel.hel_project.ui.fragment.DrawerHostFragment;

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
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.logout:
                Log.d("asd", "menuclick");
                logout();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



    private void logout() {
        mViewModel.logout();
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        //Objects.requireNonNull(getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit());

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

    /*
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
        return super.dispatchTouchEvent( event );
    }*/

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
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //invalidateOptionsMenu();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();
       //getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit();

        TextView title = findViewById(R.id.toolbar_title);
        mViewModel.getTitle().observe(this, s -> title.setText(s));


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
                            title.setText("Search hobbies");
                            Objects.requireNonNull(getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment(), "search_fragment").addToBackStack(null).commit());
                        }
                    });
                }
            }
        });
    }

    /*
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
    */
}
