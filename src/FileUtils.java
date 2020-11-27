import java.io.*;
import java.time.LocalDate;

public class FileUtils {

    private static final String NOTES_PATH = "./notes/";

    //It's a static initializer. It's executed when the class is loaded.
    //It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
    }

    public static File[] getFilesInDirectory() {
        return new File(NOTES_PATH).listFiles();
    }


    public static String fileReader(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(file))) {
            Note existingNote = (Note) objectReader.readObject();
            stringBuilder.append(existingNote.getContent());
            stringBuilder.append("\n").append(existingNote.getDate());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void fileWriter(String content) {
        String fileTitle = getProperFileName(content);
        Note newNote = new Note(fileTitle, content, LocalDate.now().toString());
        String fileName = newNote.toString() + ".txt";
        try (ObjectOutputStream objectWriter = new ObjectOutputStream(new FileOutputStream(".\\notes\\" + fileName))) {
            objectWriter.writeObject(newNote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperFileName(String content) {
        int loc = content.indexOf(" ");
        if (loc != -1) {
            return content.substring(0, loc) + "...";
        }
        if (!content.isEmpty()) {
            return content + "...";
        }
        return System.currentTimeMillis() + "_new file";
    }
}