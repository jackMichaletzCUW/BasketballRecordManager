package com.example.jackmichaletz.basketballrecordmanager;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class BasketballPlayer implements Serializable
{
    public String name;
    public int jerseyNumber;
    public int age;
    public int heightFeet;
    public int heightInches;

    @Exclude public final static int MIN_JERSEY_NUM = 1;
    @Exclude public final static int MAX_JERSEY_NUM = 99;
    @Exclude public final static int MIN_AGE = 18;

    public BasketballPlayer(String name, int jerseyNumber, int age, int heightFeet, int heightInches)
    {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
    }

    // Zero parameter constructor for serializable/firebase compatibility
    public BasketballPlayer()
    {
        // Just set these to placeholder values
        this.name = "NO DATA";
        this.jerseyNumber = 5;
        this.age = 25;
        this.heightFeet = 7;
        this.heightInches = 4;
    }

    // Get string representations of the player. toString is an overall representation, but the
    // -> following methods get it out in chunks for the player list
    @Override @Exclude
    public String toString()
    {
        return String.format("#%d %s: %d years old, %d'%d\" tall", this.jerseyNumber, this.name, this.age, this.heightFeet, this.heightInches);
    }

    @Exclude
    public String getJerseyNumberString()
    {
        return String.format("#%d:", this.jerseyNumber);
    }

    @Exclude
    public String getNameString()
    {
        return String.format("%s", this.name);
    }

    @Exclude
    public String getAgeString()
    {
        return String.format("%d Years Old", this.age);
    }

    @Exclude
    public String getHeightString()
    {
        return String.format("%d\'%d\" Tall", this.heightFeet, this.heightInches);
    }

    // I tried to create reasonable controls on the data
    @Exclude
    public static boolean isNameValid(String name)
    {
        if(!name.isEmpty() )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Exclude
    public static boolean isJerseyNumberValid(int jerseyNumber)
    {
        if(jerseyNumber >= MIN_JERSEY_NUM && jerseyNumber <= MAX_JERSEY_NUM)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @Exclude
    public static boolean isAgeValid(int age)
    {
        if(age >= MIN_AGE)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Exclude
    public static boolean isHeightFeetValid(int heightFeet)
    {
        if(heightFeet > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Exclude
    public static boolean isHeightInchesValid(int heightInches)
    {
        if(heightInches >= 0 && heightInches <= 12)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Exclude
    public void setJerseyNumber(int jerseyNumber)
    {
        if(isJerseyNumberValid(jerseyNumber))
        {
            this.jerseyNumber = jerseyNumber;
        }
    }

    @Exclude
    public void setHeightFeet(int heightFeet)
    {
        if(isHeightFeetValid(heightFeet))
        {
            this.heightFeet = heightFeet;
        }
    }

    @Exclude
    public void setHeightInches(int heightInches)
    {
        if(isHeightInchesValid(heightInches))
        {
            this.heightInches = heightInches;
        }
    }

    @Exclude
    public void setAge(int age)
    {
        if(isAgeValid(age))
        {
            this.age = age;
        }
    }

    @Exclude
    public void setName(String name)
    {
        if(isNameValid(name))
        {
            this.name = name;
        }
    }

    @Exclude
    public String getName()
    {
        return name;
    }

    @Exclude
    public int getJerseyNumber()
    {
        return jerseyNumber;
    }

    @Exclude
    public int getAge()
    {
        return age;
    }

    @Exclude
    public int getHeightFeet()
    {
        return heightFeet;
    }

    @Exclude
    public int getHeightInches()
    {
        return heightInches;
    }
}
