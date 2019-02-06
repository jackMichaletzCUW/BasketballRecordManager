package com.example.jackmichaletz.basketballrecordmanager;

import java.util.ArrayList;

public class BasketballTeam
{
    // Changed this class to use an array instead of an ArrayList to store basketball player objects,
    // -> which will have a size of MAX_PLAYERS
    public static final int MAX_PLAYERS = 100;

    private static int currentNumberOfPlayers = 0;
    private static BasketballPlayer[] team = new BasketballPlayer[MAX_PLAYERS];

    // This string array stores the human-readable list of basketball players on the team
    private static String[] teamStr = new String[MAX_PLAYERS];

    public static void init()
    {
        // Set these to be space so we will not have null string errors
        for(int pc = 0; pc < MAX_PLAYERS; pc++)
        {
            teamStr[pc] = " ";
        }

        // This will be overwritten by the first player that is added into the list -- this is just
        // -> here so that the user knows that this is where a list of players will appear.
        teamStr[0] = "No players in roster...";
    }

    public static void addPlayer(BasketballPlayer b)
    {
        team[currentNumberOfPlayers] = b;
        currentNumberOfPlayers++;
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

    public static int getSize()
    {
        return currentNumberOfPlayers;
    }

    public static String[] getTeamStrings()
    {
        return teamStr;
    }

    public static void updateTeamStrings()
    {
        for(int pc = 0; pc < currentNumberOfPlayers; pc++)
        {
            teamStr[pc] = team[pc].getSummaryString();
        }
    }

    public static BasketballPlayer getPlayer(int index)
    {
        return team[index];
    }
}
