package organizer;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Organizer {

    private static File ORGANIZER = new File("\\Organizer");

    private static File AUDIO = new File(ORGANIZER.getPath() + "\\Audio");

    private static File IMAGES = new File(ORGANIZER.getPath() + "\\Images");

    private static File PDF = new File(ORGANIZER.getPath() + "\\PDFs");

    private static File TEXT = new File(ORGANIZER.getPath() + "\\Texts");

    private static File VIDEOS = new File(ORGANIZER.getPath() + "\\Videos");

    private static File log = new File("log.txt");

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
            throw  new RuntimeException(e);
        }
    }

    private static void createDirectoryIfDoesNotExist() {

        var newAudioDir = "\\Audio";
        var newImagesDir = "\\Images";
        var newPdfDir = "\\PDFs";
        var newTextDir = "\\Texts";
        var newVideosDir = "\\Videos";

        try {
            Path path;

            if (!(Files.exists(AUDIO.toPath()))) {
                path = Path.of(ORGANIZER.getPath() + newAudioDir);
                Files.createDirectory(path);
                AUDIO = new File(path.toString());
            }
            if (!(Files.exists(IMAGES.toPath()))) {
                path = Path.of(ORGANIZER.getPath() + newImagesDir);
                Files.createDirectory(path);
                IMAGES = new File(path.toString());
            }
            if (!(Files.exists(PDF.toPath()))) {
                path = Path.of(ORGANIZER.getPath() + newPdfDir);
                Files.createDirectory(path);
                PDF = new File(path.toString());
            }
            if (!(Files.exists(TEXT.toPath()))) {
                path = Path.of(ORGANIZER.getPath() + newTextDir);
                Files.createDirectory(path);
                TEXT = new File(path.toString());
            }
            if (!(Files.exists(VIDEOS.toPath()))) {
                path = Path.of(ORGANIZER.getPath() + newVideosDir);
                Files.createDirectory(path);
                VIDEOS = new File(path.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Moving Files to <b>Images</b> directory
     * @param imageFile gets already sorted image format file
     */
    private static void imageOrganizing(File imageFile) {
        Path imagePath = Path.of(IMAGES.getPath());

        try {
            Path source = imageFile.toPath();
            Files.move(source,
                    imagePath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
                bw.write(imageFile.getPath() + " ---> " + IMAGES.getPath() + "\n");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Moving Files to <b>Audio</b> directory
     * @param audioFile gets already sorted audio format file
     */
    private static void audioOrganizing(File audioFile) {
        Path audioPath = Path.of(AUDIO.getPath());

        try {
            Path source = audioFile.toPath();
            Files.move(source,
                    audioPath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
                bw.write(audioFile.getPath() + " ---> " + AUDIO.getPath() + "\n");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Moving Files to <b>Text</b> directory
     * @param textFile gets already sorted text format file
     */
    private static void textOrganizing(File textFile) {
        Path textPath = Path.of(TEXT.getPath());

        try {
            Path source = textFile.toPath();
            Files.move(source,
                    textPath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
                bw.write(textFile.getPath() + " ---> " + TEXT.getPath() + "\n");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Moving Files to <b>PDFs</b> directory
     * @param pdfFile  gets already sorted PDF format file
     */
    private static void pdfOrganizing(File pdfFile) {
        Path pdfPath = Path.of(PDF.getPath());

        try {
            Path source = pdfFile.toPath();
            Files.move(source,
                    pdfPath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
                bw.write(pdfFile.getPath() + " ---> " + PDF.getPath() + "\n");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Moving Files to <b>Videos</b> directory
     * @param videoFile  gets already sorted video format file
     */
    private static void videoOrganizing(File videoFile) {
        Path videoPath = Path.of(VIDEOS.getPath());

        try {
            Path source = videoFile.toPath();
            Files.move(source,
                    videoPath.resolve(source.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(log, true))) {
                bw.write(videoFile.getPath() + " ---> " + VIDEOS.getPath() + "\n");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method detects file format and calls corresponding method to place file in accurate directory
     * @param file gets "file" and detects the format
     */
    private static void fileFormatDetector(File file) {
        String fileType;

        try {
            fileType = Files.probeContentType(file.toPath());
            System.out.println(fileType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (fileType != null) {
            String[] arr = fileType.split("/");
            switch (arr[0]) {
                case "video" -> videoOrganizing(file);
                case "audio" -> audioOrganizing(file);
                case "image" -> imageOrganizing(file);
                case "text" -> textOrganizing(file);
                case "application" -> pdfOrganizing(file);
            }
        }
    }
}
