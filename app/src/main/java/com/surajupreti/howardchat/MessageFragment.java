package com.surajupreti.howardchat;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageFragment extends Fragment {

    //private FirebaseAuth mAuth;

    private MessageAdapter mMessageAdapter;
    private List<Message> mMessages;

    private ListView mListView;
    private EditText mEditText;
    private Button mSendButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_message, container, false);
        mListView = (ListView) v.findViewById(R.id.list_view);

        mEditText = (EditText) v.findViewById(R.id.edit_view);

        MessageDataSource.get(getContext()).getMessages(new MessageDataSource.MessageListener() {
            @Override
            public void onMessageReceived(List<Message> messages) {
                mMessages = messages;
                mListView.setAdapter(new MessageAdapter(getContext(), R.layout.list_view_item, messages));
            }
        });


        mSendButton = (Button) v.findViewById(R.id.send_button);
        mSendButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String id = currentUser.getUid();
                Log.i("aaa", "This is the id" + id);
                String name = currentUser.getDisplayName();
                String text = mEditText.getText().toString();
                Message message = new Message(name, id, text);
                MessageDataSource.get(getContext()).sendMessage(message);
            }
        });

        return v;
    }

}
