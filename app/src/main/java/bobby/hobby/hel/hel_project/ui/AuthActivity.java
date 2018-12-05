package bobby.hobby.hel.hel_project.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bobby.hobby.hel.hel_project.R;
import bobby.hobby.hel.hel_project.base.view.activity.BaseActivity;
import bobby.hobby.hel.hel_project.base.view.activity.BaseDrawerActivity;
import bobby.hobby.hel.hel_project.ui.fragment.LoginFragment;
import bobby.hobby.hel.hel_project.ui.fragment.TabHostFragment;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;
import bobby.hobby.hel.hel_project.ui.viewmodel.AuthActivityViewModel;

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
