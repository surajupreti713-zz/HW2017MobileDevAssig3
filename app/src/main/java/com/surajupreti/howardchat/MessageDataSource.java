package com.surajupreti.howardchat;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDataSource {

    public interface MessageListener {
        void onMessageReceived(List<Message> messages);
    }

    private static MessageDataSource sMessageDataSource;
    private Context mContext;

    public static MessageDataSource get(Context context) {
        if (sMessageDataSource == null) {
            sMessageDataSource = new MessageDataSource(context);
        }
        return sMessageDataSource;
    }

    private MessageDataSource(Context context) {
        mContext = context;
    }

    //Firebase methods
    public void getMessages(final MessageListener messageListener) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference messagesRef = databaseRef.child("messages");
        messagesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Message> messages = new ArrayList<>();
                Iterable<DataSnapshot> iter = dataSnapshot.getChildren();
                for(DataSnapshot messageSnapshot: iter) {
                    if (messageSnapshot.child("fromUserId").getValue() != null) {
                        Message message = new Message(messageSnapshot);
                        messages.add(message);
                    }
                }
                messageListener.onMessageReceived(messages);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //sending message
    public void sendMessage(Message message) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference messagesRef = databaseRef.child("messages");
        DatabaseReference newMessageRef = messagesRef.push();

        Map<String, String> messageValMap = new HashMap<>();
        messageValMap.put("mUserName", message.getName());
        messageValMap.put("mUserId", message.getId());
        messageValMap.put("mContent", message.getText());

        newMessageRef.setValue(messageValMap, new DatabaseReference.CompletionListener(){
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(mContext, "Message sent!", Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }
}
