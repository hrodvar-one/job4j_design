package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }
        in.close();
    }

    public void packFiles(List<Path> sources, File target) {
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(target));
            for (Path path : sources) {
                out.putNextEntry(new ZipEntry(path.toString()));
                write(new FileInputStream(path.toFile()), out);
            }
            out.close();
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
        Path rootPath = Paths.get(zipPath.get("d"));
        List<Path> sources = Search.search(rootPath, path -> !path.toFile().getName().endsWith(zipPath.get("e")));
        Zip zip = new Zip();
        File targetPath = new File(zipPath.get("o"));
        zip.packFiles(sources, targetPath);
    }
}
