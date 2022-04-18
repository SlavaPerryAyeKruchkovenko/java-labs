import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Operator<C extends Collection<T>,T> {
    private final C collection;

    /*Class<C> clazz = collection.getClass();
        this.collection = clazz.newInstance();*/
    private Operator(C collection){
        if(collection != null)
            this.collection = collection;
        else
            throw new NullPointerException("Collection is null");
    }

    public static <C extends Collection<T> ,T> Operator<C,T> modify(C collection){
        return new Operator<>(collection);
    }

    public Operator<C,T> add(T element){
        this.collection.add(element);
        return new Operator<>(this.collection);
    }
    public Operator<C,T> add(Collection<T> elements){
        this.collection.addAll(elements);
        return new Operator<>(this.collection);
    }

    public C get(){
        return this.collection;
    }

    public Operator<C,T> each(Consumer<T> consumer){
        this.collection.forEach(consumer);
        return new Operator<>(this.collection);
    }

    public Operator<C,T> sort(Comparator<T> comparator){
        //Todo make sort of collection
        /*this.collection = this.collection.stream()
                .sorted(comparator)
                .collect(Collectors
                        .toCollection(C::new));*/
        return new Operator<>(this.collection);
    }
    public Operator<C,T> remove(Predicate<? super T> predicate){
        this.collection.removeIf(predicate);
        return new Operator<>(this.collection);
    }

    public Operator<C, T> copyTo(Supplier<C> supplier){
        return new Operator<>(this.collection.stream().collect(Collectors.toCollection(supplier)));
    }

    public <Result,NewC extends Collection<Result>> Operator<NewC,Result> convertTo(Supplier<NewC> supplier, Function<T,Result> modifier){
        return new Operator<>(this.collection.stream()
                .map(modifier)
                .collect(Collectors.toCollection(supplier)));
    }
}
