package com.wrobert19132;

import java.util.HashMap;

public class ClearInstruction extends AbstractInstruction {
    final String varName;

    public ClearInstruction(String[] line) {
        super(line);
        varName = line[1];
    }

    /**
     * Called when the function is run. sets a varible to 0.
     * @param envVars all the existing variables currently in barebones.
     */
    @Override
    public void go(HashMap<String, Integer> envVars) {
        envVars.put(varName, 0);
    }
}
