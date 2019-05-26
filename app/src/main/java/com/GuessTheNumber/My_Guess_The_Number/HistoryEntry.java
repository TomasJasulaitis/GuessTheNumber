package com.GuessTheNumber.My_Guess_The_Number;

/**
 * Created by super on 10/6/2016.
 */
public class HistoryEntry {

    private int mID;
    private String mType;
    private String mName;
    private int mScore;

    public HistoryEntry()
    {
        mID = 0;
        mType = "";
        mName = "";
        mScore = 0;
    }

    public HistoryEntry(int id, String type, String name, int score)
    {
        mID = id;
        mType = type;
        mName = name;
        mScore = score;
    }

    public void setID(int val)
    {
        mID = val;
    }

    public int getID()
    {
        return mID;
    }

    public void setName(String val)
    {
        mName = val;
    }

    public String getName()
    {
        return mName;
    }

    public void setType(String val)
    {
        mType = val;
    }

    public String getType()
    {
        return mType;
    }


    public void setScore(int val)
    {
        mScore = val;
    }

    public int getScore()
    {
        return mScore;
    }
}
