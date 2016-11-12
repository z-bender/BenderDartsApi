package ru.bender.darts.api.interfaces;

/**
 * Created by bender on 12.11.16.
 */
public interface Player {
    String getName();

    short getBestResultOfStep();

    short getBestResultOfCountShots();

    void setName(String name);

    void setBestResultOfStep(short bestResultOfStep);

    void setBestResultOfCountShots(short bestResultOfCountShots);
}
