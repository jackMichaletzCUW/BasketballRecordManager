package com.example.jackmichaletz.basketballrecordmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlayerArrayAdapter extends ArrayAdapter
{
    Context context;
    int resource;
    BasketballPlayer[] players;

    public PlayerArrayAdapter(Context context, int resource, BasketballPlayer[] players)
    {
        super(context, resource, players);

        this.context = context;
        this.resource = resource;
        this.players = players;
    }

    // This is the method that sets the text of the individual rows (players)
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItem = convertView;

        // Inflate the view into existence if it has not already been initialized
        if(listItem == null)
        {
            listItem = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        // Find our text views...
        TextView jerseyTV = listItem.findViewById(R.id.jerseyTV);
        TextView nameTV = listItem.findViewById(R.id.nameTV);
        TextView ageTV = listItem.findViewById(R.id.ageTV);
        TextView heightTV = listItem.findViewById(R.id.heightTV);

        // If there is no player in this row, we shouldn't try to pull data from it (null object error)
        // -> instead, set the text as blank.
        if(players[position] != null)
        {
            //... and set their text values
            jerseyTV.setText(players[position].getJerseyNumberString());
            nameTV.setText(players[position].getNameString());
            ageTV.setText(players[position].getAgeString());
            heightTV.setText(players[position].getHeightString());
        }
        else
        {
            jerseyTV.setText("");
            nameTV.setText("");
            ageTV.setText("");
            heightTV.setText("");
        }

        // We are returning the view that we changed
        return listItem;
    }

}
