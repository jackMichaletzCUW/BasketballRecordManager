package com.example.jackmichaletz.basketballrecordmanager;


import android.support.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BasketballTeam
{
    // Changed this class to use an array instead of an ArrayList to store basketball player objects,
    // -> which will have a size of MAX_PLAYERS
    public static final int MAX_PLAYERS = 100;

    private static int currentNumberOfPlayers = 0;
    private static BasketballPlayer[] team = new BasketballPlayer[MAX_PLAYERS];

    // These objects allow connection to the firebase database. They get initialized in initializeDB()
    private static FirebaseDatabase database;
    private static DatabaseReference dbRef;

    private static PlayerArrayAdapter paa;

    public static void addPlayer(BasketballPlayer b, boolean addToDB)
    {
        team[currentNumberOfPlayers] = b;
        currentNumberOfPlayers++;

        if(addToDB)
        {
            // Put the player out onto the team database so it can be accessed later
            BasketballTeam.writePlayerToFirebase(b);
        }
    }

    public static void assignArrayAdapter(PlayerArrayAdapter paa)
    {
        BasketballTeam.paa = paa;
    }

    // Initializes the database to write players to the table name that was passed in
    public static void initializeDB(String tableName)
    {
        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference(tableName);
    }

    public static void listenForChangesToDatabase()
    {
        // This object will log information related to DB changes
        ValueEventListener playerListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                //System.out.println(dataSnapshot.toString());

                // Clear out the current local list of players and rebuild it from what is in the DB
                BasketballTeam.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    BasketballPlayer loadedPlayer = ds.getValue(BasketballPlayer.class);

                    BasketballTeam.addPlayer(loadedPlayer, false);
                    //System.out.printf("DATA CHANGED:\n\t%s\n", loadedPlayer.toString());
                }

                paa.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                System.err.printf("ERROR READING FROM DATABASE: %s\n", databaseError.toException().toString());
            }
        };

        // Add the DB change logger to the area of the DB defined in dbRef.
        BasketballTeam.dbRef.addValueEventListener(playerListener);
    }

    public static boolean isEmpty()
    {
        if(currentNumberOfPlayers == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void writePlayerToFirebase(BasketballPlayer p)
    {
        dbRef.push().setValue(p);
    }

    // Clears out the team, starting it from scratch (locally - does not affect DB)
    public static void clear()
    {
        for(int pc = 0; pc < currentNumberOfPlayers; pc++)
        {
            team[pc] = null;
        }

        currentNumberOfPlayers = 0;
    }

    public static int getSize()
    {
        return currentNumberOfPlayers;
    }

    public static BasketballPlayer[] getPlayers() { return team; }

    public static BasketballPlayer getPlayer(int index)
    {
        return team[index];
    }


}
