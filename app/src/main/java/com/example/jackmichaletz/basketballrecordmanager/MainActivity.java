package com.example.jackmichaletz.basketballrecordmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private LinearLayout scrollerVLL;
    private final String PLACEHOLDER_STR = "No Players in Roster...";
    private boolean showingPlaceholder = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Call this to initialize the arraylist that stores the players
        BasketballTeam.init();

        // This is what the list of players will be kept in
        scrollerVLL = (LinearLayout)this.findViewById(R.id.scrollerVLL);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        // Fill the scroll view with data
        if(!BasketballTeam.isEmpty())
        {
            // Remove the "no players in roster" message if there are actually players in the roster
            if(showingPlaceholder)
            {
                scrollerVLL.removeViewAt(0);
                showingPlaceholder = false;
            }

            // This adds players that aren't already on the list to the list
            for(int pc = scrollerVLL.getChildCount(); pc < BasketballTeam.getSize(); pc++)
            {
                TextView playerText = new TextView(this);
                playerText.setText(BasketballTeam.getPlayer(pc).getSummaryString());

                scrollerVLL.addView(playerText);
            }
        }
        else if(scrollerVLL.getChildCount() == 0)
        {
            // Add the "no players in roster" placeholder message to the scrollview
            TextView placeholderText = new TextView(this);
            placeholderText.setText(PLACEHOLDER_STR);
            scrollerVLL.addView(placeholderText);

            showingPlaceholder = true;
        }
    }

    public void onAddButtonClick(View v)
    {
        // Take us to the other screen
        Intent i = new Intent(this, AddPlayerActivity.class);

        this.startActivity(i);
    }
}
