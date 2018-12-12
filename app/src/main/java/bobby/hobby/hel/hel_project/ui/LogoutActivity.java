package bobby.hobby.hel.hel_project.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import bobby.hobby.hel.hel_project.base.view.activity.BaseActivity;
import bobby.hobby.hel.hel_project.ui.viewmodel.ActivityViewModel;

public class LogoutActivity extends BaseActivity<ActivityViewModel> {


    public LogoutActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        forceCrashAndRestart();

    }

    /**
     * crash the app and restart it
     */
    private void forceCrashAndRestart() {
        Intent intent = new Intent(this, AuthActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 6666, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
        this.finish();
        System.exit(2);
    }

    @Override
    protected Class<ActivityViewModel> returnViewModel() {
        return null;
    }
}