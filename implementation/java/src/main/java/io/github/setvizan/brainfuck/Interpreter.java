package io.github.setvizan.brainfuck;

import io.github.setvizan.utils.ReducedIntStack;

import java.io.IOException;
import java.util.Scanner;

public class Interpreter {
    private static       Scanner ob         = new Scanner(System.in);
    private static final int     MAX_LENGTH = 65535;
    private final        int[]   jmp        = new int[MAX_LENGTH];
    private final        int[]   arr        = new int[MAX_LENGTH];
    private              int     ptr        = 0;

    public static void interpret(String c) throws IOException {
        char[]      commands    = c.toCharArray();
        Interpreter interpreter = new Interpreter();
        interpreter.preloadJumpTable(commands);
        interpreter.run(commands);
    }

    private void run(char[] commands) throws IOException {
        for (int i = -1, size = commands.length; ++i < size; ) {
            switch (commands[i]) {
                case '+':
                    arr[ptr]++;
                    break;
                case '-':
                    arr[ptr]--;
                    break;
                case '>':
                    ptr++;
                    break;
                case '<':
                    if (ptr != 0) ptr--;
                    break;
                case '[':
                    if (arr[ptr] == 0) i = jmp[i];
                    break;
                case ']':
                    if (arr[ptr] != 0) i = jmp[i];
                    break;
                case '.':
                    System.out.print((char) arr[ptr]);
                    break;
                case ',':
                    arr[ptr] = (char) System.in.read();
                    break;
            }
        }
    }

    private void preloadJumpTable(char[] commands) {
        ReducedIntStack stk = new ReducedIntStack(MAX_LENGTH);
        for (int i = -1; ++i < commands.length; ) {
            if (commands[i] == '[') {
                stk.push(i);
            } else if (commands[i] == ']') {
                jmp[i] = stk.pop();
                jmp[jmp[i]] = i;
            }
        }
    }
}
