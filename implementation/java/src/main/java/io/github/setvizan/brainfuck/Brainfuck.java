package io.github.setvizan.brainfuck;

import java.io.*;
import java.util.stream.Collectors;

public class Brainfuck {
    public static void run(String file){
        File bfFile = new File(file);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(bfFile));

            final String source = bufferedReader.lines().collect(Collectors.joining());
            final String optSource = Optimizer.apply(source);
            Interpreter.interpret(optSource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
