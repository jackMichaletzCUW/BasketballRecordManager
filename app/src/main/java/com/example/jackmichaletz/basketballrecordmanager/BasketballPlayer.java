package com.example.jackmichaletz.basketballrecordmanager;

public class BasketballPlayer
{
    private String name;
    private int jerseyNumber;
    private int age;
    private int heightFeet;
    private int heightInches;

    public final static int MIN_JERSEY_NUM = 1;
    public final static int MAX_JERSEY_NUM = 99;
    public final static int MIN_AGE = 18;

    public BasketballPlayer(String name, int jerseyNumber, int age, int heightFeet, int heightInches)
    {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
    }

    public String getSummaryString()
    {
        return String.format("#%d %s: %d years old, %d'%d\" tall", this.jerseyNumber, this.name, this.age, this.heightFeet, this.heightInches);
    }

    // I tried to create reasonable controls on the data
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

    public void setJerseyNumber(int jerseyNumber)
    {
        if(isJerseyNumberValid(jerseyNumber))
        {
            this.jerseyNumber = jerseyNumber;
        }
    }

    public void setHeightFeet(int heightFeet)
    {
        if(isHeightFeetValid(heightFeet))
        {
            this.heightFeet = heightFeet;
        }
    }

    public void setHeightInches(int heightInches)
    {
        if(isHeightInchesValid(heightInches))
        {
            this.heightInches = heightInches;
        }
    }

    public void setAge(int age)
    {
        if(isAgeValid(age))
        {
            this.age = age;
        }
    }

    public void setName(String name)
    {
        if(isNameValid(name))
        {
            this.name = name;
        }
    }

    public String getName()
    {
        return name;
    }

    public int getJerseyNumber()
    {
        return jerseyNumber;
    }

    public int getAge()
    {
        return age;
    }

    public int getHeightFeet()
    {
        return heightFeet;
    }

    public int getHeightInches()
    {
        return heightInches;
    }
}
