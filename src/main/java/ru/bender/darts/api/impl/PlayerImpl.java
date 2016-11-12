package ru.bender.darts.api.impl;

import ru.bender.darts.api.interfaces.Player;

/**
 * Created by bender on 27.09.2016.
 */
public class PlayerImpl implements Player {
    // имя
    private String name;
    // лучший результат за ход TODO
    private short bestResultOfStep;

    // лучший результат количества бросков TODO
    private short bestResultOfCountShots;

    public PlayerImpl(String name) {
        this.name = name;
    }

    //-------------------- Getters/Setters ------------------------//

    @Override
    public String getName() {
        return name;
    }

    @Override
    public short getBestResultOfStep() {
        return bestResultOfStep;
    }


    @Override
    public short getBestResultOfCountShots() {
        return bestResultOfCountShots;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBestResultOfStep(short bestResultOfStep) {
        this.bestResultOfStep = bestResultOfStep;
    }

    @Override
    public void setBestResultOfCountShots(short bestResultOfCountShots) {
        this.bestResultOfCountShots = bestResultOfCountShots;
    }
}
