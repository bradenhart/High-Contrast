package com.bradenhart.hctester;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private final String LOGTAG = "MainActivity class";
    private ListView listView;
    private String[] items = {"Profile", "Pick A Challenge", "Completed Challenges"};
    private Toolbar toolbar;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
            getWindow().setNavigationBarColor(getResources().getColor(R.color.accent));
        }

        button1 = (Button) findViewById(R.id.button_profile_screen);
        button2 = (Button) findViewById(R.id.button_pick_a_challenge);
        button3 = (Button) findViewById(R.id.button_completed_challenges);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_nav_drawer);
        drawerFragment.setUp(R.id.fragment_nav_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        /*listView = (ListView) findViewById(R.id.listview1);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Clicked 'Settings'", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = null;
        switch (position) {
            case 0:
                i = new Intent(this, ProfileScreen.class);
                startActivity(i);
                break;
            case 1:
                i = new Intent(this, PickChallenge.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(this, CompletedChallenges.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_profile_screen:
                i = new Intent(this, ProfileScreen.class);
                startActivity(i);
                break;
            case R.id.button_pick_a_challenge:
                i = new Intent(this, PickChallenge.class);
                startActivity(i);
                break;
            case R.id.button_completed_challenges:
                i = new Intent(this, CompletedChallenges.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}