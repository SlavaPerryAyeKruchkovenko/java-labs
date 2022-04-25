import java.awt.*;

public class Result {
    private String fileName;
    private Range fileRange;
    private String text;

    public Result(String fileName){
        this.fileName = fileName;
    }

    public Range getFileRange() {
        return fileRange;
    }

    public String getText() {
        return text;
    }

    public String getFileName() {
        return fileName;
    }

    public void setText(String text) {
        if(text != null)
            this.text = text;
    }

    public void setFileRange(int start,int finish) {
        this.fileRange = new Range(start,finish);
    }

    @Override
    public String toString() {
        return "file: " + fileName
                + " range: " + fileRange.getStart() + " " +fileRange.getFinish()
                + " text: " + text;
    }
}
