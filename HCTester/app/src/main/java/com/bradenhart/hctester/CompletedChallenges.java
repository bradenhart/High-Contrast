package com.bradenhart.hctester;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by bradenhart on 17/06/15.
 */
public class CompletedChallenges extends Activity {

    private Context context;
    private ListView listView;
    private ChallengeStatusListAdapter mAdapter;
    private ArrayList<ChallengeStatusListItem> challenges = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_challenges);

        context = this;
        listView = (ListView) findViewById(R.id.status_challenge_listview);
        for (int i = 0; i < 10; i++) {
            ChallengeStatusListItem item = new ChallengeStatusListItem("challenge #" + (i+1));
            challenges.add(item);
        }
        mAdapter = new ChallengeStatusListAdapter(context, challenges);
        listView.setAdapter(mAdapter);

    }
}
