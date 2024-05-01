package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalysisTest {

    @Test
    public void testUnavailable(@TempDir Path tempDir) throws IOException {
        Path source = tempDir.resolve("source.log");
        Files.write(source, List.of(
                "400 20210101T0000",
                "200 20210101T0200",
                "500 20210101T0300",
                "300 20210101T0400"
        ));

        Path target = tempDir.resolve("target.csv");
        Analysis analysis = new Analysis();
        analysis.unavailable(source.toString(), target.toString());
        List<String> result = Files.readAllLines(target);
        assertEquals(2, result.size());
        assertEquals("20210101T0000;20210101T0200;", result.get(0));
        assertEquals("20210101T0300;20210101T0400;", result.get(1));
    }
}