package tests;

import org.carbonit.app.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class AppTest {


    @Test
    public void executeGameFromFileTest() throws Exception {
        String text = "C - 4 - 2\n" +
                "M - 2 - 1\n" +
                "T - 2 - 0 - 2\n" +
                "A - 1n - 1 - 0 - E - aggdda\n" +
                "A - 2n - 0 - 0 - E - aggdda";

        Path folder = Path.of("input");
        Files.createDirectories(folder);
        Path filepath = folder.resolve(Path.of(UUID.randomUUID() + ".txt"));
        Files.writeString(filepath, text);

        String[] args = new String[1];
        args[0] = filepath.toString();
        Main.main(args);

        String res = "C - 4 - 2\n" +
                "M - 2 - 1\n" +
                "A - 1n - 3 - 0 - E - 1\n" +
                "A - 2n - 2 - 0 - E - 1\n";


        String newFileName = "result_" + filepath.getFileName();
        Path newFilePath = filepath.getParent().resolve(Path.of(newFileName));
        String fileResultContent = Files.readString(newFilePath);
        Assertions.assertEquals(res, fileResultContent);
        Files.delete(filepath);
        Files.delete(newFilePath);
    }


}
