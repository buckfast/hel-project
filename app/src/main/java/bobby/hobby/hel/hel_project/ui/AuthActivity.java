package bobby.hobby.hel.hel_project.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.activity.BaseActivity;
import bobby.hobby.hel.hel_project.ui.fragment.LoginFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;

public class AuthActivity extends BaseActivity<ActivityViewModel> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);



        /*SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.shared_preferences), this.MODE_PRIVATE);
        String username = sharedPref.getString("username", "null");
        if (username != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();
        } else {*/
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit();
        //this.getIntent().putExtra("logout",false);

        //}

        //TextView title = findViewById(R.id.toolbar_title);
        //mViewModel.getTitle().observe(this, s -> title.setText(s));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    @Override
    protected Class<ActivityViewModel> returnViewModel() {
        return null;
    }


}
