package com.unpas.potatosoft.potatofit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText editUsername, editPassword;
    Button btnLogin;

    SharedPreferences pref ; // 0 - for private mode
    SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences("Data user", 0);
        editor = pref.edit();
        editUsername = (EditText) findViewById(R.id.username);
        editPassword = (EditText) findViewById(R.id.password);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login().execute();

            }
        });

    }
    class Login extends AsyncTask<String, String, String>{
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        @Override
        protected String doInBackground(String... strings) {
            String url = "https://mighty-earth-45008.herokuapp.com/rest/json/post";
            String input = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
            Log.e("input", input);
            Log.e("up", username+" "+password);
            String json = ClientRequest.SendDataForLogin(url,input);
            try {
                JSONObject obj = new JSONObject(json);
                String status = obj.getString("status").toString();

                return status;

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("ressss", s.toString());
            if(s.toString().equals("success")){
                editor.putString("username", username);
                editor.commit();
                Intent mIntent = new Intent(getApplicationContext(), MainActivity.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mIntent);

                finish();

            } else {
                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                alertDialog.setTitle("Perhatian !");
                alertDialog.setMessage("Username atau Password yang anda masukkan salah !");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

        }
    }
}
