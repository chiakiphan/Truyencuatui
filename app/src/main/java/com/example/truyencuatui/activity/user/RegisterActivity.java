package com.example.truyencuatui.activity.user;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truyencuatui.R;
import com.example.truyencuatui.listener.RegisterListener;
import com.example.truyencuatui.model.User;
import com.example.truyencuatui.server.RegisterServer;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    private EditText user;
    private EditText pass;
    private EditText repass;
    private TextView note;
    private RegisterServer registerServer;
    private String email;
    private String passWord;
    private Button btnRegister;
    private SimpleLogin simpleLogin;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user = findViewById(R.id.tv_Register_email);
        pass = findViewById(R.id.tv_Register_pass);
        repass = findViewById(R.id.tv_Register_password);
        btnRegister = findViewById(R.id.btn_login_email);
        note = findViewById(R.id.note);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        registerServer = new RegisterServer(this);
        simpleLogin = new SimpleLogin();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("user");
                final int[] flag = {0,0};
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            User users = ds.getValue(User.class);
                            String key = ds.getKey();
                            users.setUserID(key);
                            flag[1] +=1;
                            if(users.getEmail().equals(users.getEmail())) flag[0] = 1;
                        }
                        SystemClock.sleep(2000);
                        if (flag[0] == 1)
                            note.setText("Email đã đăng ký");
                        else
                        {
                            myRef.setValue("U"+flag[1]);
                            myRef.child("U"+flag[1]).setValue(new User(user.getText().toString(),pass.getText().toString()));
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        repass.setOnKeyListener(new View.OnKeyListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if(!pass.getText().toString().equals(repass.getText().toString()))
                    {
                        note.setText("Mật khẩu nhập chưa chính xác");
                    }
                    else note.setText("");
                return false;
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
