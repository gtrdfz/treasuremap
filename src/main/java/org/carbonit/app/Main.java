package org.carbonit.app;

import org.tinylog.Logger;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            throw new Exception("No filepath in args");
        }
        new ReadFileAndExecuteGame(args[0]).runGame();
    }
}