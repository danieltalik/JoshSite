package website.Model;

import website.Transformation.DateTransform;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class Blog {

    private int id;
    private String date;
    private String title;
    private String content;
    private Timestamp postDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = new DateTransform().blogDate(date);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {

        String returnRegex = content.replaceAll("\r\n\r\n","<br/><br/>");
        String tabRegex = returnRegex.replaceAll("\n\t","<br/><br/>");
        String result = tabRegex.replaceAll("\n\n","<br/><br/>");
        this.content = result;

    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
