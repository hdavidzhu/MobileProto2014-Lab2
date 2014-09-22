package com.hdavidzhu.chatapp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatFragment extends Fragment {
    // Constructs the class ChatFragment
    public ChatFragment(){}

    String numid = "";
    String username = "";
    Calendar cal = Calendar.getInstance();

    // The systems calls this when it's time to render it for the first time.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Declare all the views that are being rendered from the XML.
        final View rootView = inflater.inflate(R.layout.chat_fragment, container, false);
        final EditText inputText = (EditText) rootView.findViewById(R.id.input_text);
        final ListView chatDisplayView = (ListView) rootView.findViewById(R.id.chat_display_view);

        // Place to save all the chats.
        final ArrayList<ChatHolder> listChats = new ArrayList<ChatHolder>();

        // Creates a chat adapter.
        // Adapters act as the bridge between the adapter view and the data items.
        // Adapters also make a view from the data sets.
        final ChatAdapter adapter = new ChatAdapter(rootView.getContext(), R.layout.chat_item, listChats);

        // Connect the adapter with the chat display area.
        chatDisplayView.setAdapter(adapter);

        // Connect the button XML to the instance.
        Button sendButton = (Button) rootView.findViewById(R.id.send_button);

        // Allow for the button to 'listen' to when it's being clicked.
        sendButton.setOnClickListener(new View.OnClickListener() {

            ChatHolder toAdd;
            // What to do when clicked.
            @Override
            public void onClick(View v) {

                cal = Calendar.getInstance();
                toAdd = new ChatHolder(numid,username,String.valueOf(cal.get(Calendar.SECOND))
                        ,inputText.getText().toString());
                listChats.add(toAdd);
                Log.d("listChats",listChats.get(listChats.size()-1).timestamp);
                inputText.setText("");


                // Update the adapter and then update the view. Display the latest result.
                adapter.notifyDataSetChanged();
                chatDisplayView.setSelection(listChats.size() - 1);
            }
        });


        return rootView;
    }
}