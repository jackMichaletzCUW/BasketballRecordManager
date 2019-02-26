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

    public static void addPlayer(BasketballPlayer b)
    {
        team[currentNumberOfPlayers] = b;
        currentNumberOfPlayers++;

        // Put the player out onto the team database so it can be accessed later
        BasketballTeam.writePlayerToFirebase(b);
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
                System.out.println(dataSnapshot.toString());

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    BasketballPlayer playerInQuestion = ds.getValue(BasketballPlayer.class);
                    System.out.printf("DATA CHANGED:\n\t%s\n", playerInQuestion.toString());
                }
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
