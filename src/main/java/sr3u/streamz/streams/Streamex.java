package sr3u.streamz.streams;

import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.BiFunctionex;
import sr3u.streamz.functionals.BinaryOperatorex;
import sr3u.streamz.functionals.Consumerex;
import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.functionals.Predicatex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.UnaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.ToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntFunctionex;
import sr3u.streamz.functionals.primitive.integer.ToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.ToLongFunctionex;
import sr3u.streamz.optionals.Optionalex;
import sr3u.streamz.streams.impl.StreamexSupport;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Collector;
import java.util.stream.Stream;

public interface Streamex<T> {
    static <T1> Streamex<T1> empty() {
        return ofStream(Stream.empty());
    }

    static <T1> Streamex<T1> of(T1 t1) {
        return ofStream(Stream.of(t1));
    }

    static <T1> Streamex<T1> ofStream(Stream<T1> stream) {
        return StreamexSupport.streamOf(stream);
    }

    @SafeVarargs
    static <T1> Streamex<T1> of(T1... values) {
        return StreamexSupport.streamOf(values);
    }

    static <T1> Streamex<T1> iterate(T1 seed, UnaryOperatorex<T1> f) {
        return StreamexSupport.iterate(seed, f);
    }

    static <T> Streamex<T> generate(Supplierex<T> s) {
        return StreamexSupport.generate(s);
    }

    static <T> Streamex<T> concat(Streamex<T> a, Streamex<T> b) {
        return StreamexSupport.concat(a, b);
    }

    Stream<T> stream();

    Streamex<T> filter(Predicatex<T> predicate);

    <R> Streamex<R> map(Functionex<T, ? extends R> mapper);

    IntStreamex mapToInt(ToIntFunctionex<T> mapper);

    LongStreamex mapToLong(ToLongFunctionex<T> mapper);

    DoubleStreamex mapToDouble(ToDoubleFunctionex<T> mapper);

    <R> Streamex<R> flatMap(Functionex<T, ? extends Streamex<? extends R>> mapper);

    IntStreamex flatMapToInt(Functionex<T, ? extends IntStreamex> mapper);

    LongStreamex flatMapToLong(Functionex<T, ? extends LongStreamex> mapper);

    DoubleStreamex flatMapToDouble(Functionex<T, ? extends DoubleStreamex> mapper);

    Streamex<T> distinct();

    <P> Streamex<T> distinct(Functionex<T, P> getter);

    Streamex<T> sorted();

    Streamex<T> sorted(Comparator<T> comparator);

    Streamex<T> peek(Consumerex<T> action);

    Streamex<T> limit(long maxSize);

    Streamex<T> skip(long n);

    void forEach(Consumerex<T> action);

    void forEachOrdered(Consumerex<T> action);

    Object[] toArray();

    <A> A[] toArray(IntFunctionex<A[]> generator);

    T reduce(T identity, BinaryOperatorex<T> accumulator);

    Optionalex<T> reduce(BinaryOperatorex<T> accumulator);

    <U> U reduce(U identity, BiFunctionex<U, T, U> accumulator, BinaryOperatorex<U> combiner);

    <R> R collect(Supplierex<R> supplier, BiConsumerex<R, T> accumulator, BiConsumerex<R, R> combiner);

    <R, A> R collect(Collector<? super T, A, R> collector);

    Optionalex<T> min(Comparator<T> comparator);

    Optionalex<T> max(Comparator<T> comparator);

    long count();

    boolean anyMatch(Predicatex<T> predicate);

    boolean allMatch(Predicatex<T> predicate);

    boolean noneMatch(Predicatex<T> predicate);

    Optionalex<T> findFirst();

    Optionalex<T> findAny();
    /*public static <T1> Builder<T1> builder() { //TODO        return Stream.builder();    }*/

    Iterator<T> iterator();

    Spliterator<T> spliterator();

    boolean isParallel();

    Streamex<T> sequential();

    Streamex<T> parallel();

    Streamex<T> unordered();

    Streamex<T> onClose(Runnable closeHandler);

    void close();
}
