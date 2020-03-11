package sr3u.streamz.streams;

import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.LongToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.LongToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.LongBinaryOperatorex;
import sr3u.streamz.functionals.primitive.longinteger.LongConsumerex;
import sr3u.streamz.functionals.primitive.longinteger.LongFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.LongPredicatex;
import sr3u.streamz.functionals.primitive.longinteger.LongSupplierex;
import sr3u.streamz.functionals.primitive.longinteger.LongUnaryOperatorex;
import sr3u.streamz.functionals.primitive.longinteger.ObjLongConsumerex;
import sr3u.streamz.optionals.OptionalDoublex;
import sr3u.streamz.optionals.OptionalLongex;
import sr3u.streamz.streams.impl.StreamexSupport;

import java.util.LongSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.LongStream;

public interface LongStreamex {

    static LongStreamex empty() {
        return of();
    }

    static LongStreamex of(long t) {
        return of(new long[]{t});
    }

    static LongStreamex of(long... values) {
        return StreamexSupport.ofLong(values);
    }

    static LongStreamex iterate(long seed, LongUnaryOperatorex f) {
        return StreamexSupport.iterateLong(seed, f);
    }

    static LongStreamex generate(LongSupplierex s) {
        return StreamexSupport.generateLong(s);
    }

    static LongStreamex range(long startInclusive, long endExclusive) {
        return StreamexSupport.rangeLong(startInclusive, endExclusive);
    }

    static LongStreamex rangeClosed(long startInclusive, long endInclusive) {
        return StreamexSupport.rangeLongClosed(startInclusive, endInclusive);
    }

    static LongStreamex concat(LongStreamex a, LongStreamex b) {
        return StreamexSupport.concatLong(a, b);
    }

    LongStream stream();

    LongStreamex filter(LongPredicatex predicate);

    LongStreamex map(LongUnaryOperatorex mapper);

    <U> Streamex<U> mapToObj(LongFunctionex<U> mapper);

    IntStreamex mapToInt(LongToIntFunctionex mapper);

    DoubleStreamex mapToDouble(LongToDoubleFunctionex mapper);

    default StringStreamex mapToString() {
        return boxed().asStringStream();
    }

    default StringStreamex asStringStream() {
        return mapToString();
    }

    LongStreamex flatMap(LongFunctionex<? extends LongStreamex> mapper);

    LongStreamex distinct();

    LongStreamex sorted();

    LongStreamex peek(LongConsumerex action);

    LongStreamex limit(long maxSize);

    LongStreamex skip(long n);

    void forEach(LongConsumerex action);

    void forEachOrdered(LongConsumerex action);

    long[] toArray();

    long reduce(long identity, LongBinaryOperatorex op);

    OptionalLongex reduce(LongBinaryOperatorex op);

    <R> R collect(Supplierex<R> supplier, ObjLongConsumerex<R> accumulator, BiConsumerex<R, R> combiner);

    long sum();

    default OptionalLongex min() {
        return sorted().findFirst();
    }

    OptionalLongex max();

    long count();

    OptionalDoublex average();

    LongSummaryStatistics summaryStatistics();

    boolean anyMatch(LongPredicatex predicate);

    boolean allMatch(LongPredicatex predicate);

    boolean noneMatch(LongPredicatex predicate);

    OptionalLongex findFirst();

    OptionalLongex findAny();

    default DoubleStreamex asDoubleStream() {
        return mapToDouble(i -> (double) i);
    }

    default Streamex<Long> boxed() {
        return mapToObj(Long::valueOf);
    }

    LongStreamex sequential();

    LongStreamex parallel();

    PrimitiveIterator.OfLong iterator();

    Spliterator.OfLong spliterator();

    boolean isParallel();

    LongStreamex unordered();

    LongStreamex onClose(Runnable closeHandler);

    void close();
}
