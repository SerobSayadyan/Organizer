package organizer;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Organizer {

    private static File ORGANIZER = new File("");

    private final static File AUDIO;

    private final static File IMAGES;

    private final static File PDF;

    private final static File TEXT;

    private final static File VIDEOS;

    private final static File log = new File("log.txt");

    static {
        boolean isTrue = true;
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.print("Please enter correct PATH of folder/directory you want to organize:\t");
                String path = sc.nextLine();
                System.out.println();
                if (Files.exists(Path.of(path))) {
                    ORGANIZER = new File(path);
                    isTrue = false;
                }
            } while (isTrue);
        }

        AUDIO = new File(ORGANIZER.getPath() + "\\Audio");

        IMAGES = new File(ORGANIZER.getPath() + "\\Images");

        PDF = new File(ORGANIZER.getPath() + "\\PDFs");

        TEXT = new File(ORGANIZER.getPath() + "\\Texts");

        VIDEOS = new File(ORGANIZER.getPath() + "\\Videos");

    }


    public static void organize() {

        try {
            if (ORGANIZER.exists()) {
                createDirectoryIfDoesNotExist();
                File[] files = ORGANIZER.listFiles();
                assert files != null;
                for (File file : files) {
                    if (file != null) {
                        fileFormatDetector(file);
                    }
                }
            }
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method is creating directories (Audio, Images, PDFs, Texts, Videos) if they are absent in working directory
     */
    private static void createDirectoryIfDoesNotExist() {

        String[] newDirs = {"\\Audio", "\\Images", "\\PDFs", "\\Texts", "\\Videos"};
        File[] orgFiles = {AUDIO, IMAGES, PDF, TEXT, VIDEOS};

        for (int i = 0; i < newDirs.length; i++) {
            try {
                Path path;

                if (!(Files.exists(orgFiles[i].toPath()))) {
                    path = Path.of(ORGANIZER.getPath() + newDirs[i]);
                    if (!(Files.exists(path))) {
                        Files.createDirectory(path);
                        orgFiles[i] = new File(path.toString());
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Moving Files to <b><i>destination</i></b> directory
     *
     * @param sourceFile        gets source file ready for moving to destination directory (Images, Audion etc...)
     * @param destinationOfFile gets destination directory where the <b>sourceFile</b> should be moved
     */
    private static void organizingInDirectories(File sourceFile, File destinationOfFile) {
        Path imagePath = Path.of(destinationOfFile.getPath());

        try {
            Path source = sourceFile.toPath();
            Files.move(source,
                    imagePath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);

            writeInLog(sourceFile.getPath(), destinationOfFile.getPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to write source file and destination in log.txt
     *
     * @param sourcePath      gets source file path
     * @param destinationPath gets destination directory path
     */
    private static void writeInLog(String sourcePath, String destinationPath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
            bw.write(sourcePath + " ---> " + destinationPath + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method detects file format and calls corresponding method to place file in accurate directory
     *
     * @param file gets file and detects the format
     */
    private static void fileFormatDetector(File file) {
        String fileType;

        try {
            fileType = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fileType != null) {
            String[] arr = fileType.split("/");
            switch (arr[0]) {
                case "video" -> organizingInDirectories(file, VIDEOS);
                case "audio" -> organizingInDirectories(file, AUDIO);
                case "image" -> organizingInDirectories(file, IMAGES);
                case "text" -> organizingInDirectories(file, TEXT);
                case "application" -> organizingInDirectories(file, PDF);
            }
        }
    }
}
