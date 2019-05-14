package com.example.truyencuatui.activity.user;

import android.app.Activity;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.truyencuatui.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SimpleLogin {
    public static ArrayList<User> list;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    String ss="a";
    public SimpleLogin(){

    }

    public int register(String email,String pass){
        int result = checkUser(email,pass);
        if(result != 0) {
            int a =result+100;
            a++;
            User user = new User(email,pass);
            myRef.setValue("U"+a);
            myRef.child("U"+a).setValue(user);
            return 1;
        }
        return 0;
    }
    public ArrayList<User> getUser(){
        list = new ArrayList<User>();
        database = FirebaseDatabase.getInstance();
        myRef= database.getReference("user");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    String key = ds.getKey();
                    user.setUserID(key);
                    list.add(user);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SystemClock.sleep(1000);
        Log.d("LIST.",ss+"");
        return list;
    }

    public int checkUser(final String name, final String pass){
        ArrayList<User> list = getUser();
        for (User u:list){
            if(u.getEmail().equals(name) && u.getPass().equals(pass)){

                return list.size();
            }
        }
        return 0;
    }
}
