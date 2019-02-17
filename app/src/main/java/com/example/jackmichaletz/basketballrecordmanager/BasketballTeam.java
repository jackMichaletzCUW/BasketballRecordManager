package com.example.jackmichaletz.basketballrecordmanager;

import java.util.ArrayList;

public class BasketballTeam
{
    // Changed this class to use an array instead of an ArrayList to store basketball player objects,
    // -> which will have a size of MAX_PLAYERS
    public static final int MAX_PLAYERS = 100;

    private static int currentNumberOfPlayers = 0;
    private static BasketballPlayer[] team = new BasketballPlayer[MAX_PLAYERS];

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

    public static BasketballPlayer[] getPlayers() { return team; }

    public static BasketballPlayer getPlayer(int index)
    {
        return team[index];
    }
}
