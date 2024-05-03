package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.Files;

public class Zip {

    private static void validate(ArgsName name) {
        File file = new File(name.get("d"));
        if (!file.exists()) {
            throw new IllegalArgumentException("Such file does not exist.");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a directory.");
        }
        String extension = name.get("e");
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("The extension must begin with the character .");
        }
        String output = name.get("o");
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("The extension must end with .zip.");
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target))) {
            for (Path path : sources) {
                out.putNextEntry(new ZipEntry(path.toString().replace("\\", "/")));
                try (InputStream in = Files.newInputStream(path)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                }
                out.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName zipPath = ArgsName.of(args);
        validate(zipPath);
        Path rootPath = Paths.get(zipPath.get("d"));
        List<Path> sources = Search.search(rootPath, path -> !path.toFile().getName().endsWith(zipPath.get("e")));
        Zip zip = new Zip();
        File targetPath = new File(zipPath.get("o"));
        zip.packFiles(sources, targetPath);
    }
}
