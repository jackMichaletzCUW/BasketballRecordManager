package com.example.jackmichaletz.basketballrecordmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity
{
    private ListView teamListView;

    // This is the name of the table in the DB that the program will write
    // -> the players out to
    private final String TABLE_NAME = "PLAYERS";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database
        FirebaseApp.initializeApp(this);
        BasketballTeam.initializeDB(TABLE_NAME);

        // Fill the scroll view with data
        // -> Create an array adapter to hold the data.
        PlayerArrayAdapter aa = new PlayerArrayAdapter(this, R.layout.list_view_row_advanced, BasketballTeam.getPlayers());

        // -> This is what the list of players will be kept in.
        teamListView = (ListView) this.findViewById(R.id.teamListView);

        teamListView.setAdapter(aa);

        BasketballTeam.assignArrayAdapter(aa);

        teamListView.invalidateViews();

        // Add a logger for the DB that will output to the console if the DB is changed
        BasketballTeam.listenForChangesToDatabase();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        teamListView.invalidateViews();
    }

    public void onAddButtonClick(View v)
    {
        // Take us to the other screen
        Intent i = new Intent(this, AddPlayerActivity.class);

        this.startActivity(i);
    }
}
