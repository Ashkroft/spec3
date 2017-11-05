import java.util.function.Function;

// type data of abstract's node
public interface Node <V, T> {
    <R> R process(Function<V, R> leafProcessor, TreeFunction<T, R> biNodeProcessor);
}
