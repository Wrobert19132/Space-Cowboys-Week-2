package com.wrobert19132;


import java.io.*;
import java.util.ArrayList;

public class Main {
    /**
     * Loads up the "code.txt" file and passes it to the BareBonesInterpreter.
     */
    public static void main(String[] args) {
        BareBonesInterpreter intr = new BareBonesInterpreter();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("code.txt"));

            ArrayList<String> contents = new ArrayList<>();
            while (reader.ready()) {
                contents.add(reader.readLine());
            }
                intr.addCode(String.join("\n", contents));
                intr.go();
            }
        catch(FileNotFoundException e){
                System.out.println("Code file doesn't exist!");
            }
        catch(IOException e){
                System.out.println("Error reading the file!");
            }
        }
}
