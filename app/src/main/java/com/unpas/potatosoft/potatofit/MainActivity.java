package com.unpas.potatosoft.potatofit;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    TextView usernameTextView;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("Data user", MODE_PRIVATE);
        btnLogout = (Button) findViewById(R.id.logout);
        usernameTextView = (TextView) findViewById(R.id.username);


        String good = prefs.getString("username", null);
        usernameTextView.setText(good);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
