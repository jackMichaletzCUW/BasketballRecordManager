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

        // Test the linked list implementation
        int[] argsInt = {1,2,3,4,5,6,7,8,9};

        LinkedList list = new LinkedList();

        for(int ac = 0; ac < argsInt.length; ac++)
        {
            list.addEnd(argsInt[ac]);
        }

        System.out.printf("\n****BEGIN TEST****\nLINKED LIST:\n\t%s\n", list.toString());

        list.addFront(22);

        System.out.printf("ADD 22 TO FRONT:\n\t%s\n", list.toString());

        list.addFront(43);

        System.out.printf("ADD 43 TO FRONT:\n\t%s\n", list.toString());

        list.addEnd(67);

        System.out.printf("ADD 67 TO END:\n\t%s\n", list.toString());

        System.out.printf("REMOVE %d FROM INDEX %d:", list.getValueAtIndex(0), 0);

        list.removeAtIndex(0);

        System.out.printf("\n\t%s\n", list.toString());

        System.out.printf("REMOVE %d FROM INDEX %d:", list.getValueAtIndex(list.getSize() - 1), list.getSize() - 1);

        list.removeAtIndex(list.getSize() - 1);

        System.out.printf("\n\t%s\n", list.toString());

        System.out.printf("REMOVE %d FROM INDEX %d:", list.getValueAtIndex(1), 1);

        list.removeAtIndex(1);

        System.out.printf("\n\t%s\n", list.toString());

        list.addAtIndex(73, 1);

        System.out.printf("ADD %d TO LIST AT INDEX %d:\n\t%s\n", 73, 1, list.toString());

        System.out.printf("****END TEST****\n\n");

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
