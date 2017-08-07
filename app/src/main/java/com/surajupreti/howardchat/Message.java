package com.surajupreti.howardchat;

import com.google.firebase.database.DataSnapshot;

public class Message {
    private String mUsername;
    private String mUserid;
    private String mUsertext;

    public Message(String name, String id, String text) {
        this.mUsername = name;
        this.mUserid = id;
        this.mUsertext = text;
    }

    public Message(DataSnapshot messageSnapshot) {
        mUsername = messageSnapshot.child("fromUserName").getValue(String.class);
        mUserid = messageSnapshot.child("fromUserId").getValue(String.class);
        mUsertext = messageSnapshot.child("content").getValue(String.class);
    }

    public String getId() {
        return mUserid;
    }

    public String getText() {
        return mUsertext;
    }

    public String getName() {
        return mUsername;
    }

}
