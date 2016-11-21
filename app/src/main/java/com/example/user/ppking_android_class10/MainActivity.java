package com.example.user.ppking_android_class10;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private File sdroot , approot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("gameData",MODE_PRIVATE);
        edit = sp.edit();
        textView = (TextView)findViewById(R.id.main_TextView);

        //測試手機環境是否有sd card的設定磁區
        //String state = Environment.getExternalStorageState();
        sdroot = Environment.getExternalStorageDirectory();

        Log.v("ppking",sdroot.getAbsolutePath());

        //檢查使用者是否有確認權限

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_CONTACTS},
                    321);
        }
        else {
            init();
        }


    }

    private void init(){
        approot = new File(sdroot, "Android/data/" + getPackageName());
        if (!approot.exists()) approot.mkdirs();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for( int grantResult :grantResults ){
            if(grantResult == PackageManager.PERMISSION_GRANTED){
                Log.v("ppking","OK");
                init();
            }
        }
    }

    public void test1(View v){
        edit.putInt("Stage", 5);
        edit.putString("User", "ppking");
        edit.commit();
        Log.v("ppking", "test button");
        Toast.makeText(this, "Save OK", Toast.LENGTH_SHORT).show();
    }
    public void test2(View v){
        int stage = sp.getInt("Stage", 0);
        String userName = sp.getString("User","nobody");
        textView.setText("Stage" + stage + "\n" + "User" + userName);
    }
    //內存write
    public void test3(View v) {
        try {
            FileOutputStream fout = openFileOutput("ppking data", MODE_PRIVATE);
            fout.write("Hello pping+\n Hello Workd" .getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"SAVE OK",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.v("ppikng","test3:"+e.toString());
        }

    }
    //內存read
    public void test4(View v) {
        textView.setText("");
        try {
            BufferedReader fin = new BufferedReader(
                    new InputStreamReader(openFileInput("ppking data")));
            String line ;
            while ((line = fin.readLine()) != null){
                textView.append(line+"\n");
            }
            fin.close();
        } catch (IOException e) {
            Log.v("ppikng","test4:"+e.toString());
        }

    }
    public void test5(View v){
        try {
            FileOutputStream fout = new FileOutputStream(new File(sdroot,"file.text"));
            fout.write("Hello World".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"Save ok",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.v("ppking",e.toString());
        }
    }
    public void test6(View v){
        try {
            FileOutputStream fout = new FileOutputStream(new File(sdroot,"file.text"));
            fout.write("Hello World".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this,"Save ok",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.v("ppking",e.toString());
        }
    }
    public void test7(View v){
        textView.setText("");
        try {
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(new File(approot, "file.txt")));
            String line;
            while ( (line = reader.readLine()) != null){
                textView.append(line + "\n");
            }
            reader.close();
        }catch (Exception e){
            Log.v("brad", "test7():" +e.toString());
        }
    }

}
