package ru.bender.darts.api.interfaces;

public interface Player {

    int getID();

    void setID(int ID);

    String getName();

    short getBestResultOfStep();

    short getBestResultOfCountShots();

    void setName(String name);

    void setBestResultOfStep(short bestResultOfStep);

    void setBestResultOfCountShots(short bestResultOfCountShots);
}
