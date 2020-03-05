package sr3u.streamz.streams;

import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.IntToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.IntToLongFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntBinaryOperatorex;
import sr3u.streamz.functionals.primitive.integer.IntConsumerex;
import sr3u.streamz.functionals.primitive.integer.IntFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntPredicatex;
import sr3u.streamz.functionals.primitive.integer.IntSupplierex;
import sr3u.streamz.functionals.primitive.integer.IntUnaryOperatorex;
import sr3u.streamz.functionals.primitive.integer.ObjIntConsumerex;
import sr3u.streamz.optionals.OptionalDoublex;
import sr3u.streamz.optionals.OptionalIntex;
import sr3u.streamz.streams.impl.StreamexSupport;

import java.util.IntSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.IntStream;

public interface IntStreamex {

    static IntStreamex empty() {
        return of();
    }

    static IntStreamex of(int t) {
        return of(new int[]{t});
    }

    static IntStreamex of(int... values) {
        return StreamexSupport.ofInt(values);
    }

    static IntStreamex iterate(int seed, IntUnaryOperatorex f) {
        return StreamexSupport.iterateInt(seed, f);
    }

    static IntStreamex generate(IntSupplierex s) {
        return StreamexSupport.generateInt(s);
    }

    static IntStreamex range(int startInclusive, int endExclusive) {
        return StreamexSupport.rangeInt(startInclusive, endExclusive);
    }

    static IntStreamex rangeClosed(int startInclusive, int endInclusive) {
        return StreamexSupport.rangeIntClosed(startInclusive, endInclusive);
    }

    static IntStreamex concat(IntStreamex a, IntStreamex b) {
        return StreamexSupport.concatInt(a, b);
    }

    IntStream stream();

    IntStreamex filter(IntPredicatex predicate);

    IntStreamex map(IntUnaryOperatorex mapper);

    <U> Streamex<U> mapToObj(IntFunctionex<U> mapper);

    LongStreamex mapToLong(IntToLongFunctionex mapper);

    DoubleStreamex mapToDouble(IntToDoubleFunctionex mapper);

    IntStreamex flatMap(IntFunctionex<? extends IntStream> mapper);

    IntStreamex distinct();

    IntStreamex sorted();

    IntStreamex peek(IntConsumerex action);

    IntStreamex limit(long maxSize);

    IntStreamex skip(long n);

    void forEach(IntConsumerex action);

    void forEachOrdered(IntConsumerex action);

    int[] toArray();

    int reduce(int identity, IntBinaryOperatorex op);

    OptionalIntex reduce(IntBinaryOperatorex op);

    <R> R collect(Supplierex<R> supplier, ObjIntConsumerex<R> accumulator, BiConsumerex<R, R> combiner);

    int sum();

    default OptionalIntex min() {
        return sorted().findFirst();
    }

    OptionalIntex max();

    long count();

    OptionalDoublex average();

    IntSummaryStatistics summaryStatistics();

    boolean anyMatch(IntPredicatex predicate);

    boolean allMatch(IntPredicatex predicate);

    boolean noneMatch(IntPredicatex predicate);

    OptionalIntex findFirst();

    OptionalIntex findAny();

    default LongStreamex asLongStream() {
        return mapToLong(i -> (long) i);
    }

    default DoubleStreamex asDoubleStream() {
        return mapToDouble(i -> (double) i);
    }

    default Streamex<Integer> boxed() {
        return mapToObj(Integer::valueOf);
    }

    IntStreamex sequential();

    IntStreamex parallel();

    PrimitiveIterator.OfInt iterator();

    Spliterator.OfInt spliterator();

    boolean isParallel();

    IntStreamex unordered();

    IntStreamex onClose(Runnable closeHandler);

    void close();
}
