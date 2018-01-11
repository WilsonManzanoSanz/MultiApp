package manzano.wilson.multiapp.Activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Objects;

import manzano.wilson.multiapp.Objects.User;
import manzano.wilson.multiapp.R;

public class MainActivity extends AppCompatActivity {

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String receive = bundle.getString(getString(R.string.preference_user_key));
            mUser = new Gson().fromJson(receive, User.class);
            Context context = getApplicationContext();
            SharedPreferences sharedPref = context.getSharedPreferences(
                    getString(R.string.preference_user_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.preference_user_key), receive);
            editor.apply();
            editor.commit();

        }

    }
}
