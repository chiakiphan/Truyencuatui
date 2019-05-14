package com.example.truyencuatui.activity.user;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.truyencuatui.R;
import com.example.truyencuatui.activity.MainActivity;
import com.example.truyencuatui.model.User;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private CallbackManager callbackManager ;
    private Button login ;
    private Button simplelogin;
    private EditText email;
    private EditText pass;
    private FaceBookLogin faceBookLogin;
    private TextView newUser;
    private SimpleLogin simpleLogin;
    private ImageView img;
    private FirebaseAuth mAuth;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String LOGIN="login";
    private static final String DATALOGIN="datalogin";
    private static final String ISLOGIN="islogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        simpleLogin = new SimpleLogin();

        newUser = findViewById(R.id.txt_Login_register);
        simplelogin = findViewById(R.id.btn_login_email);
        email = findViewById(R.id.txt_Login_username);
        pass = findViewById(R.id.txt_Login_pass);
        login = findViewById(R.id.btn_fb_login);

        faceBookLogin = new FaceBookLogin(this);
        callbackManager = CallbackManager.Factory.create();

        preferences = getSharedPreferences(DATALOGIN, Context.MODE_PRIVATE);
        editor = preferences.edit();

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faceBookLogin.loginFaceBook(callbackManager,LoginActivity.this,mAuth);

            }
        });
        simplelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               FirebaseDatabase database = FirebaseDatabase.getInstance();
               DatabaseReference myRef= database.getReference("user");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            User user = ds.getValue(User.class);
                            String key = ds.getKey();
                            user.setUserID(key);
                            if(user.getEmail().equals(email.getText().toString()) && user.getPass().equals(pass.getText().toString())){
                                editor.putBoolean(ISLOGIN,true);
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra(LOGIN,1);
                                startActivity(intent);
                                finish();
                            }else Toast.makeText(LoginActivity.this,"Sai thông tin đăng nhập",Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
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
