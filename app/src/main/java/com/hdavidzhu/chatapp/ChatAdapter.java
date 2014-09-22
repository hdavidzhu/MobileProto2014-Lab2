package com.hdavidzhu.chatapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

// Declares the class ChatAdapter
public class ChatAdapter extends ArrayAdapter<ChatHolder> {

    // Allows context to be accessed outside of the construction and become a class variable.
    Context context;
    int resource;
    List<ChatHolder> listChats;

    public class ViewHolder {
        public TextView message, name, timestamp;
    }

    public ChatAdapter(Context context, int resource, List<ChatHolder> listChats) {
        super(context, resource, listChats);
        this.context = context;
        this.resource = resource;
        this.listChats = listChats;
    }

    // [???] Does this mean that we are overriding the method that String already provides with this new one?
//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        for (ChatHolder chat: this.addedChats) {
//            sb.append("Chat ");
//            sb.append(chat.id);
//            sb.append(" at ");
//            sb.append(chat.timestamp);
//            sb.append("\n");
//        }
//        return sb.toString();
//    }

    @Override
    public int getCount(){
        Log.d("Stuff","Is getView working?");
        return listChats.size();
    }

    @Override
    public ChatHolder getItem(int position) {
        Log.d("Stuff","Is getItem working?");
        return listChats.get(position);
    }

    // Get a view that displays the data at the specified position in the data set.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        // If we don't have an initial view,
        if (convertView == null) {
            // We will generate it from scratch using the following line.
            // convertView = activity.getLayoutInflater().inflate(R.layout.chat_item,null);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chat_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.message = (TextView) convertView.findViewById(R.id.chat_message);
            viewHolder.name = (TextView) convertView.findViewById(R.id.chat_name);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.chat_timestamp);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(listChats.get(position).name);
        Log.d(listChats.get(position).name,listChats.get(position).name);
        viewHolder.message.setText(listChats.get(position).message);
        Log.d(listChats.get(position).message,listChats.get(position).message);
        viewHolder.message.setText(listChats.get(position).timestamp);
        Log.d(listChats.get(position).timestamp,listChats.get(position).timestamp);

        return convertView;
    }
}