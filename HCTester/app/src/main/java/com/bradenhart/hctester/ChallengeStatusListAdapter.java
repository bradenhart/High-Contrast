package com.bradenhart.hctester;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bradenhart on 17/06/15.
 */
public class ChallengeStatusListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ChallengeStatusListItem> challenges;

    public ChallengeStatusListAdapter() {};

    public ChallengeStatusListAdapter(Context context, ArrayList<ChallengeStatusListItem> challenges) {
        this.context = context;
        this.challenges = challenges;
    }

    @Override
    public int getCount() {
        return challenges.size();
    }

    @Override
    public Object getItem(int position) {
        return challenges.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.list_item_challenge_status, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.status_challenge_title);
        title.setText(challenges.get(position).getTitle());

        return convertView;
    }
}
