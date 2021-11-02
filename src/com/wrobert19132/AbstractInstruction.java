package com.wrobert19132;

import java.util.HashMap;


public abstract class AbstractInstruction {
    final String[] line;

    /**
     * An instruction- the equivalent of a function for barebones.
     */
    public AbstractInstruction(String[] line) {
        this.line = line;
    }

    /**
     * Called when the program is ran.
     * @param envVars all the existing variables currently in barebones.
     */
    public abstract void go(HashMap<String, Integer> envVars);
}
