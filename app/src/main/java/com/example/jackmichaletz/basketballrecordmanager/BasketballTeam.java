package com.example.jackmichaletz.basketballrecordmanager;

import java.util.ArrayList;

public class BasketballTeam
{
    // This class basically just provides methods to do things that arraylist can already do with one method at this point
    // -> but more functionality could be added at a later point -- BasketballPlayer getPlayer(String name), etc...

    private static ArrayList<BasketballPlayer> team;

    public static void init()
    {
        team = new ArrayList<BasketballPlayer>();
    }

    public static void addPlayer(BasketballPlayer b)
    {
        team.add(b);
    }

    public static boolean isEmpty()
    {
        if(team.size() == 0)
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
        return team.size();
    }

    public static void removePlayer(int index)
    {
        team.remove(index);
    }

    public static BasketballPlayer getPlayer(int index)
    {
        return team.get(index);
    }
}
