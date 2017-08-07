package com.surajupreti.howardchat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

public class MessageFragment extends Fragment {

    private ListView mListView;
    private EditText mEditText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message, container, false);
        mListView = (ListView) v.findViewById(R.id.list_view);
        mEditText = (EditText) v.findViewById(R.id.edit_view);

        return v;
    }



}
