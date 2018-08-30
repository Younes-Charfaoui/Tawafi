package me.mxcsyounes.tawaf.models;

public class State {

    public void setStateHajar(int stateHajar) {
        this.stateHajar = stateHajar;
    }

    public void setStateRokn(int stateRokn) {
        this.stateRokn = stateRokn;
    }

    private int stateHajar;

    public State() {
    }

    private int stateRokn;

    public State(int stateHajar, int stateRokn) {
        this.stateHajar = stateHajar;
        this.stateRokn = stateRokn;
    }

    public int getState() {
        if (stateHajar == 1) {
            if (stateRokn == 1) return 1;
            else return 2;
        } else {
            if (stateRokn == 1) return 4;
            else return 3;
        }
    }
}
