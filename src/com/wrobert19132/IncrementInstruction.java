package com.wrobert19132;

import java.util.HashMap;

public class IncrementInstruction extends AbstractInstruction {
    final String varName;

    public IncrementInstruction(String[] line) {
        super(line);
        varName = line[1];
    }


    /**
     * Called when the function is run. increases a variable by 1.
     * @param envVars all the existing variables currently in barebones.
     */
    @Override
    public void go(HashMap<String, Integer> envVars) {
        envVars.put(varName, envVars.get(varName)+1);
    }
}
