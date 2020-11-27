import java.io.Serializable;

public class Note implements Serializable {

    private String title;
    private String content;
    private String date;

    public Note(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    /**
     * @return this object's content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return this object's date
     */
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return title + ", " + date;
    }

}

