package com.example.truyencuatui.server;

import android.app.Activity;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;

import com.example.truyencuatui.base.BaseFireBase;
import com.example.truyencuatui.listener.RegisterListener;
import com.example.truyencuatui.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class RegisterServer extends BaseFireBase {
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private Activity activity;

    public RegisterServer(Activity activity) {
        this.activity = activity;
        auth = getFirebaseAuth();
        mDatabase = getDatabaseReference();
    }

    /**
     * Đăng lý tài khoản bằng Email
     *
     * @param email
     * @param passWord
     * @param listener
     */
    public void registerAccount(String email, String passWord, final RegisterListener listener) {
        auth.createUserWithEmailAndPassword(email, passWord)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final FirebaseUser userFB = task.getResult().getUser();
                            if (userFB != null) {
                                //Gửi 1 email xác thực tài khoản
                                userFB.sendEmailVerification().addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            //Tiến hành thông tin user vào Database
                                            User users = new User();
                                            users.setUserID(userFB.getUid());
                                            users.setPass("www.AndroidCoBan.Com");
                                            users.setEmail(userFB.getEmail());
                                            createAccountInDatabase(users, new RegisterListener() {
                                                @Override
                                                public void registerSuccess() {
                                                    auth.signOut(); // Đăng xuất.
                                                    listener.registerSuccess();
                                                }

                                                @Override
                                                public void registerFailure(String message) {
                                                    listener.registerFailure(message);
                                                }
                                            });

                                        }
                                    }
                                });
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.registerFailure(e.getMessage());
            }
        });

    }

    /**
     * Lưu thông tin user
     *
     * @param users
     */
    public void createAccountInDatabase(User users, final RegisterListener listener) {
        mDatabase.child("user")
                .child(users.getUserID())
                .setValue(users)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.registerSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.registerFailure(e.getMessage());
            }
        });
    }
}
