package sr3u.streamex.streams;

import sr3u.streamex.functionals.BiConsumerex;
import sr3u.streamex.functionals.Supplierex;
import sr3u.streamex.functionals.primitive.LongToDoubleFunctionex;
import sr3u.streamex.functionals.primitive.LongToIntFunctionex;
import sr3u.streamex.functionals.primitive.longinteger.LongBinaryOperatorex;
import sr3u.streamex.functionals.primitive.longinteger.LongConsumerex;
import sr3u.streamex.functionals.primitive.longinteger.LongFunctionex;
import sr3u.streamex.functionals.primitive.longinteger.LongPredicatex;
import sr3u.streamex.functionals.primitive.longinteger.LongSupplierex;
import sr3u.streamex.functionals.primitive.longinteger.LongUnaryOperatorex;
import sr3u.streamex.functionals.primitive.longinteger.ObjLongConsumerex;
import sr3u.streamex.optionals.OptionalDoublex;
import sr3u.streamex.optionals.OptionalLongex;

import java.util.LongSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.LongStream;

import static sr3u.streamex.common.ExceptionWrapper.wrap;
import static sr3u.streamex.common.primitive.LongExceptionWrapper.wrap;

public class LongStreamex {

    protected final LongStream internal;

    private LongStreamex(LongStream longStream) {
        this.internal = longStream;
    }

    public LongStream stream() {
        return internal;
    }

    public static LongStreamex ofStream(LongStream longStream) {
        return new LongStreamex(longStream);
    }


    public LongStreamex filter(LongPredicatex predicate) {
        return LongStreamex.ofStream(internal.filter(wrap(predicate)));
    }

    public LongStreamex map(LongUnaryOperatorex mapper) {
        return LongStreamex.ofStream(internal.map(wrap(mapper)));
    }

    public <U> Streamex<U> mapToObj(LongFunctionex<? extends U> mapper) {
        return Streamex.ofStream(internal.mapToObj(wrap(mapper)));
    }

    public IntStreamex mapToInt(LongToIntFunctionex mapper) {
        return IntStreamex.ofStream(internal.mapToInt(wrap(mapper)));
    }

    public DoubleStreamex mapToDouble(LongToDoubleFunctionex mapper) {
        return DoubleStreamex.ofStream(internal.mapToDouble(wrap(mapper)));
    }

    public LongStreamex flatMap(LongFunctionex<? extends LongStream> mapper) {
        return LongStreamex.ofStream(internal.flatMap(wrap(mapper)));
    }

    public LongStreamex distinct() {
        return LongStreamex.ofStream(internal.distinct());
    }

    public LongStreamex sorted() {
        return LongStreamex.ofStream(internal.sorted());
    }

    public LongStreamex peek(LongConsumerex action) {
        return LongStreamex.ofStream(internal.peek(wrap(action)));
    }

    public LongStreamex limit(long maxSize) {
        return LongStreamex.ofStream(internal.limit(maxSize));
    }

    public LongStreamex skip(long n) {
        return LongStreamex.ofStream(internal.skip(n));
    }

    public void forEach(LongConsumerex action) {
        internal.forEach(wrap(action));
    }

    public void forEachOrdered(LongConsumerex action) {
        internal.forEachOrdered(wrap(action));
    }

    public long[] toArray() {
        return internal.toArray();
    }

    public long reduce(long identity, LongBinaryOperatorex op) {
        return internal.reduce(identity, wrap(op));
    }

    public OptionalLongex reduce(LongBinaryOperatorex op) {
        return OptionalLongex.ofOptional(internal.reduce(wrap(op)));
    }

    public <R> R collect(Supplierex<R> supplier, ObjLongConsumerex<R> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(wrap(supplier), wrap(accumulator), wrap(combiner));
    }

    public long sum() {
        return internal.sum();
    }

    public OptionalLongex min() {
        return OptionalLongex.ofOptional(internal.min());
    }

    public OptionalLongex max() {
        return OptionalLongex.ofOptional(internal.max());
    }

    public long count() {
        return internal.count();
    }

    public OptionalDoublex average() {
        return OptionalDoublex.ofOptional(internal.average());
    }

    public LongSummaryStatistics summaryStatistics() {
        return internal.summaryStatistics();
    }

    public boolean anyMatch(LongPredicatex predicate) {
        return internal.anyMatch(wrap(predicate));
    }

    public boolean allMatch(LongPredicatex predicate) {
        return internal.allMatch(wrap(predicate));
    }

    public boolean noneMatch(LongPredicatex predicate) {
        return internal.noneMatch(wrap(predicate));
    }

    public OptionalLongex findFirst() {
        return OptionalLongex.ofOptional(internal.findFirst());
    }

    public OptionalLongex findAny() {
        return OptionalLongex.ofOptional(internal.findAny());
    }

    public DoubleStreamex asDoubleStream() {
        return DoubleStreamex.ofStream(internal.asDoubleStream());
    }

    public Streamex<Long> boxed() {
        return Streamex.ofStream(internal.boxed());
    }

    public LongStreamex sequential() {
        return LongStreamex.ofStream(internal.sequential());
    }

    public LongStreamex parallel() {
        return LongStreamex.ofStream(internal.parallel());
    }

    public PrimitiveIterator.OfLong iterator() {
        return internal.iterator();
    }

    public Spliterator.OfLong spliterator() {
        return internal.spliterator();
    }

    public static LongStream.Builder builder() {
        return LongStream.builder();
    }

    public static LongStreamex empty() {
        return LongStreamex.ofStream(LongStream.empty());
    }

    public static LongStreamex of(long t) {
        return LongStreamex.ofStream(LongStream.of(t));
    }

    public static LongStreamex of(long... values) {
        return LongStreamex.ofStream(LongStream.of(values));
    }

    public static LongStreamex iterate(long seed, LongUnaryOperatorex f) {
        return LongStreamex.ofStream(LongStream.iterate(seed, wrap(f)));
    }

    public static LongStreamex generate(LongSupplierex s) {
        return LongStreamex.ofStream(LongStream.generate(wrap(s)));
    }

    public static LongStreamex range(long startInclusive, long endExclusive) {
        return LongStreamex.ofStream(LongStream.range(startInclusive, endExclusive));
    }

    public static LongStreamex rangeClosed(long startInclusive, long endInclusive) {
        return LongStreamex.ofStream(LongStream.rangeClosed(startInclusive, endInclusive));
    }

    public static LongStreamex concat(LongStreamex a, LongStreamex b) {
        return LongStreamex.ofStream(LongStream.concat(a.internal, b.internal));
    }

    public boolean isParallel() {
        return internal.isParallel();
    }

    public LongStreamex unordered() {
        return LongStreamex.ofStream(internal.unordered());
    }

    public LongStreamex onClose(Runnable closeHandler) {
        return LongStreamex.ofStream(internal.onClose(closeHandler));
    }

    public void close() {
        internal.close();
    }
}
