import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        Map<String,String> texts = getTexts(new File("assets"));

        System.out.println("input data");
        Scanner scanner = new Scanner(System.in);
        String str = "lorem";

        try{
            Map<String,Set<Result>> index = getIndex(str);

            Map<String, Set<Result>> map = add(index,texts);
            map.forEach(
                    (x,y)-> {
                        System.out.println("\n" + x + "\n");
                        y.forEach(
                                System.out::println
                        );
                    }
            );
        }catch (Exception ex){
           ex.printStackTrace();
        }

    }
    public static Map<String, Set<Result>> add(Map<String, Set<Result>> index, Map<String, String> texts) {
        index.forEach(
                (key,res)->texts.forEach(
                        (name,text) -> {
                            List<String> sentences = Arrays.stream(text.split("\\.")).collect(Collectors.toList());
                            sentences.stream()
                                    .filter(x->x.toLowerCase(Locale.ROOT).trim().contains(key))
                                    .forEach(
                                            y -> {
                                                Result result = new Result(name);
                                                result.setText(y);

                                                String[] splitText = text.split("\n");
                                                List<String> splitY = Arrays.stream(y.split("\r\n"))
                                                        .filter(val-> !val.trim().equals(""))
                                                        .map(val-> val.toLowerCase(Locale.ROOT))
                                                        .collect(Collectors.toList());

                                                int start = Arrays.stream(splitText)
                                                        .collect(Collectors.toList()).indexOf(
                                                                Arrays.stream(splitText).filter(
                                                                        val->val.toLowerCase(Locale.ROOT)
                                                                                .contains(splitY.stream()
                                                                                .filter(x -> x.contains(key))
                                                                                .findFirst().get())
                                                                ).findFirst()
                                                                        .get()
                                                        ) + 1;
                                                result.setFileRange(
                                                        start,start+ splitY.size() -1);
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
