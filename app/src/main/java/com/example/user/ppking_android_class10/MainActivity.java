package com.example.user.ppking_android_class10;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("gameData",MODE_PRIVATE);
        edit = sp.edit();
        textView = (TextView)findViewById(R.id.main_TextView);

    }
    public void test(View v){
        edit.putInt("Stage", 5);
        edit.putString("User", "ppking");
        edit.commit();
        Log.v("ppking", "test button");
        Toast.makeText(this, "Save OK", Toast.LENGTH_SHORT).show();
    }
    public void game(View v){
        int stage = sp.getInt("Stage", 0);
        String userName = sp.getString("User","nobody");
        textView.setText("Stage" + stage + "\n" + "User" + userName);
    }
}
