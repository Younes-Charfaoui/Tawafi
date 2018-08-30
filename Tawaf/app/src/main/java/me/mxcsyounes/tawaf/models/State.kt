package me.mxcsyounes.tawaf.models;

data class State(var stateHajar: Int, var stateRokn: Int) {

    //no argument constructor
    constructor() : this(0, 0)

    //function to get state in the haram
    fun getState() =
            if (stateHajar == 1) {
                if (stateRokn == 1) 1
                else 2
            } else {
                if (stateRokn == 1) 4
                else 3
            }

}
