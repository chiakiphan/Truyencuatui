package com.example.truyencuatui.activity.user;

import android.app.Activity;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.truyencuatui.R;
import com.example.truyencuatui.base.BaseFireBase;
import com.example.truyencuatui.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;

public class FaceBookLogin extends BaseFireBase {
    private static String TAG = FaceBookLogin.class.getSimpleName();
    private DatabaseReference mDatabase;
    private Activity activity;

    public FaceBookLogin(Activity activity) {
        mDatabase = getDatabaseReference();
        this.activity = activity;
    }

    public void loginFaceBook(CallbackManager callbackManager, final Activity activity, final FirebaseAuth mAuth) {
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "email"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(mAuth,mDatabase,loginResult.getAccessToken(),activity);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(activity, "Login Cancel!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(activity, "Login Error!", Toast.LENGTH_LONG).show();
                        Log.e(TAG,exception.toString());
                    }
                });
    }

    /**
     * Xử lý Đăng nhập
     * @param mAuth
     * @param mDatabase
     * @param token
     * @param activity
     */
    private  void handleFacebookAccessToken(FirebaseAuth mAuth, final DatabaseReference mDatabase, AccessToken token, final Activity activity) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User users = new User();
                            users.setUserID(task.getResult().getUser().getUid());
                            users.setEmail(task.getResult().getUser().getDisplayName());
                            users.setEmail(task.getResult().getUser().getEmail());
                            //users.setAvatar(String.valueOf(task.getResult().getUser().getPhotoUrl()));
                            createUser(users);
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(activity,"success", Toast.LENGTH_SHORT).show();
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(activity,"fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }


    /**
     * Tạo một User Mới
     * @param user
     */
    private void createUser(final User user) {
        mDatabase.child("user").child(user.getUserID()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: users created ");
            }
        });
    }
}
