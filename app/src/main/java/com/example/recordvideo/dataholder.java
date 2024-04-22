package com.example.recordvideo;

public class dataholder
{
    String name, place;

    public dataholder(String name, String place)
    {
        this.name = name;
        this.place = place;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }
}
