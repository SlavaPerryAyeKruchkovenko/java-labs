import java.util.Collection;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Operator<T> {
    private final Collection<T> collection;

    private Operator(Collection<T> collection){
        this.collection = collection;
    }
    public static Operator modify(Collection collection){
        return new Operator(collection);
    }
    public Operator<T> add(T element){
        this.collection.add(element);
        return new Operator<T>(this.collection);
    }
    public Operator<T> add(Collection<T> elements){
        this.collection.addAll(elements);
        return new Operator<T>(this.collection);
    }
    public Collection<T> get(){
        return this.collection;
    }
    public void each(Consumer<T> consumer){
        this.collection.forEach(consumer);
    }
    public Operator<T> sort(Comparator<T> comparator){
        Collection<T> newCollect = this.collection.stream().sorted(comparator).collect(Collectors.toList());
        return new Operator<T>(newCollect);
    }
    public Operator<T> remove(Predicate<T> predicate){
        // TODO make remove for predicate
        //this.collection.remove();
        return new Operator<T>(this.collection);
    }
}
