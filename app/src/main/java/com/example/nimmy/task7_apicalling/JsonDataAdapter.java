package com.example.nimmy.task7_apicalling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Nimmy on 08-02-2017.
 */

public class JsonDataAdapter extends BaseAdapter {
    Context context;
    ArrayList<Post> posts;
    LayoutInflater inflater;
    TextView TV1,TV2,TV3,TV4;

    public JsonDataAdapter(Context context,ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
        inflater = LayoutInflater.from(context);

    }

        @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
//    static class ViewHolder{
//        TextView textView;
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;

        if(convertView == null)
            //holder = new ViewHolder();
            convertView =inflater.inflate(R.layout.single_row,null);
            TV1 = (TextView)convertView.findViewById(R.id.txt_userId);
            TV2 = (TextView)convertView.findViewById(R.id.txt_id);
            TV3= (TextView)convertView.findViewById(R.id.txt_title);
            TV4= (TextView)convertView.findViewById(R.id.txt_body);

        Post m = posts.get(position);
        TV1.setText("userID:"+String.valueOf(m.getUserId()));
        TV2.setText("ID:"+String.valueOf(m.getId()));
        TV3.setText("Title:"+m.getTitle());
        TV4.setText("Body:"+m.getBody());

        return convertView;
    }
}
