import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        Map<String,String> texts = getTexts(new File("assets"));

        //System.out.println("input data");
        //Scanner scanner = new Scanner(System.in);
        //String str = scanner.nextLine();

        String str = "Lorem";
        Map<String,Set<Result>> index = getIndex(str);

        Map<String, Set<Result>> map = add(index,texts);
        System.out.println(map);
    }
    public static Map<String, Set<Result>> add(Map<String, Set<Result>> index, Map<String, String> texts) {
        index.forEach(
                (val,res)->texts.forEach(
                        (name,text) -> {
                            List<String> words = Arrays.stream(text.split("\n")).collect(Collectors.toList());
                            words.stream()
                                    .filter(x->x.toLowerCase(Locale.ROOT).contains(val))
                                    .forEach(
                                            y -> {
                                                Result result = new Result(name);
                                                result.setText(y);
                                                result.setFileRange(words.indexOf(y),words.indexOf(y));
                                                res.add(result);
                                            }
                                    );
                        }
                )
        );
        return index;
    }
    private static Map<String,Set<Result>> getIndex(String str){
        Map<String,Set<Result>> index = new HashMap<>();
        str = str.toLowerCase(Locale.ROOT);
        if(str.length() < 3)
            throw new RuntimeException("Small input string");
        else
            for (int i = 3; i <= str.length(); i++)
                index.put(str.substring(0,i),new HashSet<>());

        return index;
    }
    private static Map<String,String> getTexts(File directory){
        Map<String, String> texts = new HashMap<>();
        if(directory.isDirectory()){
            for(File file1: Objects.requireNonNull(directory.listFiles())){
                try{
                    if(file1.isFile()){
                        String data = new String(Files.readAllBytes(Paths.get(file1.getAbsolutePath())));
                        texts.put(file1.getName(),data);
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
