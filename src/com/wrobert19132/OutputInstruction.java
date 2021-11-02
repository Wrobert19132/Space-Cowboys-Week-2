package com.wrobert19132;

import java.util.HashMap;

public class OutputInstruction extends AbstractInstruction {
    final String varName;

    public OutputInstruction(String[] line) {
        super(line);
        varName = line[1];
    }

    @Override
    public void go(HashMap<String, Integer> envVars) {
        System.out.println(envVars.getOrDefault(varName, 0));
    }
}
