import java.io.*;

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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            char[] chars = new char[8192];
            int count;
            while (bufferedReader.ready()) {
                count = bufferedReader.read(chars);
                stringBuilder.append(new String(chars, 0, count));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void fileWriter(String content) {
        String fileName = getProperFileName(content);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(".\\notes\\" + fileName))) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperFileName(String content) {
        int loc = content.indexOf("\n");
        if (loc != -1) {
            return content.substring(0, loc) + ".txt";
        }
        if (!content.isEmpty()) {
            return content + ".txt";
        }
        return System.currentTimeMillis() + "_new file.txt";
    }
}