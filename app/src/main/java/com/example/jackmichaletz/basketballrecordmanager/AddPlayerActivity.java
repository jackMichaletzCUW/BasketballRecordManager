package com.example.jackmichaletz.basketballrecordmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayerActivity extends AppCompatActivity
{

    private EditText nameET;
    private EditText jerseyNumET;
    private EditText ageET;
    private EditText heightFootET;
    private EditText heightInchET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        nameET = (EditText)this.findViewById(R.id.nameET);
        jerseyNumET = (EditText)this.findViewById(R.id.jerseyNumET);
        ageET = (EditText)this.findViewById(R.id.ageET);
        heightFootET = (EditText)this.findViewById(R.id.heightFootET);
        heightInchET = (EditText)this.findViewById(R.id.heightInchET);

        jerseyNumET.setHint(String.format("Jersey Number: %d-%d", BasketballPlayer.MIN_JERSEY_NUM, BasketballPlayer.MAX_JERSEY_NUM));
        ageET.setHint(String.format("Age: %d+", BasketballPlayer.MIN_AGE));
    }

    public void onAddButtonPressed(View v)
    {
        // Add the player
        BasketballTeam.addPlayer(
          new BasketballPlayer(
              nameET.getText().toString(),
              Integer.parseInt(jerseyNumET.getText().toString()),
              Integer.parseInt(ageET.getText().toString()),
              Integer.parseInt(heightFootET.getText().toString()),
              Integer.parseInt(heightInchET.getText().toString())
          )
        );

        // Create a notification that lets the user know that the player was successfully added
        Toast.makeText(this, String.format("%s Added to the Team.", nameET.getText().toString()), Toast.LENGTH_SHORT).show();

        // Zero out the text fields in case the user wants to add another player
        nameET.setText("");
        jerseyNumET.setText("");
        ageET.setText("");
        heightFootET.setText("");
        heightInchET.setText("");
    }
}
