package com.wrobert19132;

import java.util.*;

public class BareBonesInterpreter {
    private final ArrayList<String> allCode = new ArrayList<>();

    private final HashMap<String, Integer> variables = new HashMap<>();
    private final Stack<Integer> loopStack = new Stack<>();

    private Integer lineNum = 0;

    public BareBonesInterpreter () {
    }

    /**
     * Adds new code to the Interpreter.
     */
    public void addCode(String code) {
        code = code.replace("\n", "").replace("   ", "");
        String[] split = code.split(";");
        allCode.addAll(Arrays.asList(split));
    }


    public void go() {
        while (lineNum != allCode.size()) {
            String line = allCode.get(lineNum);
            parseLine(line);
            lineNum += 1;
        }
    }

    /**
     * Called for each line of code. interprets it.
     * Also handles loops.
     */
    public void parseLine(String line) {
        String[] splitLine = line.split(" ");

        /* Not particularly a fan of this bulky statement; tried implementing this
        with a dictionary, but couldn't get it to work the way i wanted it to.
         */
        if (splitLine[0].equals("while")) {
            String logicStatement = String.join(" ", Arrays.copyOfRange(splitLine, 1, splitLine.length));
            if (evaluateStatement(logicStatement)) {
                loopStack.push(lineNum);
            }
        } else if (splitLine[0].equals("end")) {
            Integer loop_start = loopStack.pop();
            String[] statement_line = allCode.get(loop_start).split(" ");
            String logicStatement = String.join(" ", Arrays.copyOfRange(statement_line, 1, statement_line.length));
            if (evaluateStatement(logicStatement)) {
                lineNum = loop_start-1;
            }

        } else {
            AbstractInstruction instr = switch (splitLine[0]) {
                case "clear" -> new ClearInstruction(splitLine);
                case "incr" -> new IncrementInstruction(splitLine);
                case "decr" -> new DecrementInstruction(splitLine);
                case "out" -> new OutputInstruction(splitLine);
                default -> null;
            };
            if (instr != null) {
                instr.go(variables);
            }
        }
    }

    /**
     * This is hacky - it can only handle not statements, and just overall isn't
     * well made.
     */
    public boolean evaluateStatement(String statement) {
        String[] splitStatement = statement.split(" ");
        if (splitStatement[1].equals("not")) {
            Integer value1;
            Integer value2;

            if (splitStatement[0].matches("\\d")) {
                value1 = Integer.parseInt(splitStatement[0]);
            } else {
                value1 = variables.get(splitStatement[0]);
            }

            if (splitStatement[2].matches("\\d")) {
                value2 = Integer.parseInt(splitStatement[2]);
            } else {
                value2 = variables.get(splitStatement[2]);
            }
            return !value1.equals(value2);

        }
        return false;
    }


}
