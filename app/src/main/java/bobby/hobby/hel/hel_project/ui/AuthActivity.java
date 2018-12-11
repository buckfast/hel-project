package bobby.hobby.hel.hel_project.ui;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

        if (
                this.getIntent().getExtras() != null &&
                this.getIntent().getExtras().getBoolean("logout",false)) {
            forceCrashAndRestart();
        } else {

        /*SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.shared_preferences), this.MODE_PRIVATE);
        String username = sharedPref.getString("username", "null");
        if (username != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new TabHostFragment()).commit();
        } else {*/
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit();
        }
        //}

        //TextView title = findViewById(R.id.toolbar_title);
        //mViewModel.getTitle().observe(this, s -> title.setText(s));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    /**
     * restart the app by crashing it
     */
    private void forceCrashAndRestart() {
        Intent intent = new Intent(this, AuthActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) AuthActivity.this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
        this.finish();
        System.exit(2);
    }

    @Override
    protected Class<ActivityViewModel> returnViewModel() {
        return null;
    }


}
