import java.awt.*;

public class Result {
    private String fileName;
    private Range fileRange;
    private String text;

    public Result(String fileName, int start, int finish, String text){
        this.fileName = fileName;
        this.fileRange = new Range(start,finish);
        this.text = text;

    }
}
