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

    private final int GOOD_COLOR = 0xFFFFFFFF; // white
    private final int BAD_COLOR = 0xFFFF6969; // pastel red

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

    private void clearBGColors()
    {
        nameET.setBackgroundColor(GOOD_COLOR);
        jerseyNumET.setBackgroundColor(GOOD_COLOR);
        ageET.setBackgroundColor(GOOD_COLOR);
        heightFootET.setBackgroundColor(GOOD_COLOR);
        heightInchET.setBackgroundColor(GOOD_COLOR);
    }

    public void onAddButtonPressed(View v)
    {
        this.clearBGColors();

        String toastText = "";

        String nameInput = nameET.getText().toString();

        int jerseyNumInput;
        int ageInput;
        int heightFootInput;
        int heightInchInput;

        // Avoid an exception when parsing the integers
        if(!jerseyNumET.getText().toString().isEmpty())
        {
            jerseyNumInput = Integer.parseInt(jerseyNumET.getText().toString());
        }
        else
        {
            jerseyNumInput = -1;
        }

        if(!ageET.getText().toString().isEmpty())
        {
            ageInput = Integer.parseInt(ageET.getText().toString());
        }
        else
        {
            ageInput = -1;
        }

        if(!heightFootET.getText().toString().isEmpty())
        {
            heightFootInput = Integer.parseInt(heightFootET.getText().toString());
        }
        else
        {
            heightFootInput = -1;
        }

        if(!heightInchET.getText().toString().isEmpty())
        {
            heightInchInput = Integer.parseInt(heightInchET.getText().toString());
        }
        else
        {
            heightInchInput = -1;
        }

        boolean fullyValid = true;

        if(nameInput == null || !BasketballPlayer.isNameValid(nameInput))
        {
            nameET.setBackgroundColor(BAD_COLOR);
            nameET.setText("");
            fullyValid = false;
        }

        if(!BasketballPlayer.isJerseyNumberValid(jerseyNumInput))
        {
            jerseyNumET.setBackgroundColor(BAD_COLOR);
            jerseyNumET.setText("");
            fullyValid = false;
        }

        if(!BasketballPlayer.isAgeValid(ageInput))
        {
            ageET.setBackgroundColor(BAD_COLOR);
            ageET.setText("");
            fullyValid = false;
        }

        if(!BasketballPlayer.isHeightFeetValid(heightFootInput))
        {
            heightFootET.setBackgroundColor(BAD_COLOR);
            heightFootET.setText("");
            fullyValid = false;
        }

        if(!BasketballPlayer.isHeightInchesValid(heightInchInput))
        {
            heightInchET.setBackgroundColor(BAD_COLOR);
            heightInchET.setText("");
            fullyValid = false;
        }

        if(fullyValid)
        {
            // Add the player
            BasketballTeam.addPlayer(
                    new BasketballPlayer(
                            nameInput,
                            jerseyNumInput,
                            ageInput,
                            heightFootInput,
                            heightInchInput
                    )
            );

            this.clearBGColors();

            // Zero out the text fields in case the user wants to add another player
            nameET.setText("");
            jerseyNumET.setText("");
            ageET.setText("");
            heightFootET.setText("");
            heightInchET.setText("");

            toastText = String.format("%s Added to the Team", nameInput);

            // Go back to the previous screen
            this.finish();
        }
        else
        {
            toastText = "Invalid Input. Please try again.";
        }

        // Create a notification that lets the user know if the player was successfully added
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}
