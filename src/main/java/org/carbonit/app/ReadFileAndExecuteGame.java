package org.carbonit.app;

import org.carbonit.box.*;
import org.carbonit.game.Game;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadFileAndExecuteGame {

    private final String filepath;

    public ReadFileAndExecuteGame(String arg) {
        this.filepath = arg;
    }

    public void runGame() throws Exception {
        Path file = Path.of(filepath);
        if (!Files.exists(file))
            throw new Exception("File doesn't exist");

        Scanner scanner = new Scanner(new File(filepath));
        Game game = null;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() > 0) {
                String firstLetter = line.substring(0, 1);
                if (firstLetter.equals("C"))
                    game = createGame(line);
                else if (firstLetter.equals("T"))
                    createT(game, line);
                else if (firstLetter.equals("M"))
                    createM(game, line);
                else if (firstLetter.equals("A"))
                    createA(game, line);
            }
        }
        scanner.close();

        game.start();

        String newFileName = "result_"+file.getFileName();
        //Files.writeString(file.getParent().resolve(Path.of(newFileName))).

    }

    private Game createGame(String line) throws Exception {
        try {
            String[] lineSplit = line.split("-");
            return new Game(Integer.parseInt(lineSplit[1].trim()), Integer.parseInt(lineSplit[2].trim()));
        } catch (Exception e) {
            throw new Exception("Impossible to create C: " + line);
        }
    }

    private void createT(Game game, String line) throws Exception {
        try {
            String[] lineSplit = line.split("-");
            int h = Integer.parseInt(lineSplit[1].trim());
            int v = Integer.parseInt(lineSplit[2].trim());
            int nb = Integer.parseInt(lineSplit[3].trim());
            game.addSpecialBox(new Treasure(new Position(h, v), nb));
        } catch (Exception e) {
            throw new Exception("Impossible to create T: " + line);
        }
    }

    private void createM(Game game, String line) throws Exception {
        try {
            String[] lineSplit = line.split("-");
            int h = Integer.parseInt(lineSplit[1].trim());
            int v = Integer.parseInt(lineSplit[2].trim());
            game.addSpecialBox(new Mountain(new Position(h, v)));
        } catch (Exception e) {
            throw new Exception("Impossible to create M: " + line);
        }
    }

    private void createA(Game game, String line) throws Exception {
        try {
            String[] lineSplit = line.split("-");
            String name = lineSplit[1].trim();
            int h = Integer.parseInt(lineSplit[2].trim());
            int v = Integer.parseInt(lineSplit[3].trim());
            EOrientation o = EOrientation.valueOf(lineSplit[4].trim());
            String moves = lineSplit[5].trim();
            game.addAdventurer(new Adventurer(new Position(h, v), name, o, moves));
        } catch (Exception e) {
            throw new Exception("Impossible to create A: " + line);
        }
    }


}
