import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        System.out.println("i am gay");
        Map<String,String> texts = getTexts(new File("assets"));
        
    }
    public static Map<String, Set<Result>> add(Map<String, Set<Result>> index, Map<String, String> texts) {
        return null;
    }
    public static Map<String,String> getTexts(File directory){
        Map<String, String> texts = new HashMap<>();
        if(directory.isDirectory()){
            for(File file1: Objects.requireNonNull(directory.listFiles())){
                try{
                    if(file1.isFile()){
                        String data = new String(Files.readAllBytes(Paths.get(file1.getAbsolutePath())));
                        texts.put(file1.getName(),data);
                        System.out.println(data);
                    }
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }
        return texts;
    }
}
