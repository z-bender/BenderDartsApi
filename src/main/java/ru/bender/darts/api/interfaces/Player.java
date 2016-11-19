package ru.bender.darts.api.interfaces;

public interface Player {
    String getName();

    short getBestResultOfStep();

    short getBestResultOfCountShots();

    void setName(String name);

    void setBestResultOfStep(short bestResultOfStep);

    void setBestResultOfCountShots(short bestResultOfCountShots);
}
