package com.GuessTheNumber.My_Guess_The_Number;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by super on 10/27/2016.
 */
public class HistoryAdapter extends BaseAdapter {

    ArrayList<HistoryEntry> dataList;
    Activity activity;

    HistoryAdapter(ArrayList<HistoryEntry> d, Activity a)
    {
        dataList = d;
        activity = a;
    }

    public int getCount()
    {
        if (dataList != null)
        {
            return dataList.size();
        }
        return 0;
    }

    public long getItemId(int position) { return position; }

    public Object getItem(int position)
    {
        if(dataList != null) {
            return dataList.get(position);
        }
        return null;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup)
    {
        View vi = convertView;
        if(vi == null)
        {
            LayoutInflater li = LayoutInflater.from(activity);
            vi = li.inflate(R.layout.activity_history_list, null);
        }

        TextView typeText = (TextView)vi.findViewById(R.id.listview_type);
        TextView nameText = (TextView)vi.findViewById(R.id.listview_name);
        TextView scoreText = (TextView)vi.findViewById(R.id.listview_score);
        ImageView playerImg = (ImageView)vi.findViewById(R.id.listview_image);

        HistoryEntry le = dataList.get(position);

        typeText.setText(le.getType());
        nameText.setText(le.getName());
        scoreText.setText(Integer.toString(le.getScore()));
        playerImg.setImageResource(R.drawable.circle);

        return vi;
    }
}
