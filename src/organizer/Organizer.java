package organizer;

import java.io.*;
import java.nio.file.*;

public class Organizer {
    private final static File ORGANIZER = new File("C:\\Users\\Serob\\Desktop\\Organizer");

    private static final File AUDIO = new File("C:\\Users\\Serob\\Desktop\\Organizer\\Audio");

    private static final File IMAGES = new File("C:\\Users\\Serob\\Desktop\\Organizer\\Images");

    private static final File PDF = new File("C:\\Users\\Serob\\Desktop\\Organizer\\PDFs");

    private static final File TEXT = new File("C:\\Users\\Serob\\Desktop\\Organizer\\Texts");

    private static final File VIDEOS = new File("C:\\Users\\Serob\\Desktop\\Organizer\\Videos");

    private final static File log = new File("log.txt");


    public static void organize() {

        try {
            if (ORGANIZER.exists()) {
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


    /**
     * Moving Files to <b>Images</b> directory
     * @param imageFile
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
     * @param audioFile
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
     * @param textFile
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
     * @param pdfFile
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
     * @param videoFile
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
     * @param file
     */
    private static void fileFormatDetector(File file) {
        String fileName = file.getName();
        if (fileName.contains(".jpg") || fileName.contains(".png")
                || fileName.contains(".jpeg") || fileName.contains(".gif")
                || fileName.contains(".ico")) {
            imageOrganizing(file);
        } else if (fileName.contains(".txt") || fileName.contains(".doc")
                || fileName.contains(".xml")) {
            textOrganizing(file);
        } else if (fileName.contains(".pdf")) {
            pdfOrganizing(file);
        } else if (fileName.contains(".mp3") || fileName.contains(".wav")
                || fileName.contains(".m4a") || fileName.contains(".wma")) {
            audioOrganizing(file);
        } else if (fileName.contains(".mp4") || fileName.contains(".mov")
                || fileName.contains(".wmv") || fileName.contains(".mpeg")
                || fileName.contains(".mpg") || fileName.contains(".3gp")
                || fileName.contains(".webm")) {
            videoOrganizing(file);
        } else {
            System.out.println("The " + fileName + " has unknown format");
        }
    }
}
