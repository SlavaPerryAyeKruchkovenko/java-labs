import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args){
        ArrayList<String> list1 = new ArrayList<>();

        list1.add("abc");
        list1.add("de");
        list1.add("f");

        ArrayList<String> result1 = Operator.modify(list1).add("1234").add(list1).add("zzzzz").get();
        System.out.println(result1);
        // [abc, de, f, 1234, abc, de, f, 1234, zzzzz]

        LinkedHashSet<Integer> set1 = new LinkedHashSet<>();

        set1.add(45678);
        set1.add(56789);
        set1.add(12345);
        set1.add(23456);
        set1.add(34567);

        Consumer<Object> print = System.out::print;
        Comparator<Number> compareNumbers = Comparator.comparingDouble(Number::doubleValue);
        Predicate<Number> greaterThan30000 = i -> compareNumbers.compare(i, 30000) > 0;
        LinkedHashSet<Integer> result2 = Operator.modify(set1).add(99999).sort(compareNumbers.reversed()).get();
        System.out.println(result2);
        // [12345, 23456, 99999]

    }
}
