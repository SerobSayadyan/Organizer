package organizer;

import java.io.File;

public class CreateDirectoriesForOrganizer {

    private String[] dirNames = {"Audio", "Images", "PDFs", "Text", "Videos"};

    public CreateDirectoriesForOrganizer(File organizer, File... files) {
        organizer.mkdir();
    }

}
