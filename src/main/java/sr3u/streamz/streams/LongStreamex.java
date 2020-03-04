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

import java.util.LongSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.LongStream;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.LongExceptionWrapper.wrap;

public class LongStreamex {

    protected LongStream internal;

    private LongStreamex(LongStream longStream) {
        this.internal = longStream;
    }

    public LongStream stream() {
        return internal;
    }

    private LongStreamex setStream(LongStream stream) {
        internal = stream;
        return this;
    }

    public static LongStreamex ofStream(LongStream longStream) {
        return new LongStreamex(longStream);
    }


    public LongStreamex filter(LongPredicatex predicate) {
        return setStream(internal.filter(wrap(predicate)));
    }

    public LongStreamex map(LongUnaryOperatorex mapper) {
        return setStream(internal.map(wrap(mapper)));
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
        return setStream(internal.flatMap(wrap(mapper)));
    }

    public LongStreamex distinct() {
        return setStream(internal.distinct());
    }

    public LongStreamex sorted() {
        return setStream(internal.sorted());
    }

    public LongStreamex peek(LongConsumerex action) {
        return setStream(internal.peek(wrap(action)));
    }

    public LongStreamex limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    public LongStreamex skip(long n) {
        return setStream(internal.skip(n));
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
        return setStream(internal.sequential());
    }

    public LongStreamex parallel() {
        return setStream(internal.parallel());
    }

    public PrimitiveIterator.OfLong iterator() {
        return internal.iterator();
    }

    public Spliterator.OfLong spliterator() {
        return internal.spliterator();
    }

    /*public static LongStream.Builder builder() { //TODO
        return LongStream.builder();
    }*/

    public static LongStreamex empty() {
        return ofStream(LongStream.empty());
    }

    public static LongStreamex of(long t) {
        return ofStream(LongStream.of(t));
    }

    public static LongStreamex of(long... values) {
        return ofStream(LongStream.of(values));
    }

    public static LongStreamex iterate(long seed, LongUnaryOperatorex f) {
        return ofStream(LongStream.iterate(seed, wrap(f)));
    }

    public static LongStreamex generate(LongSupplierex s) {
        return ofStream(LongStream.generate(wrap(s)));
    }

    public static LongStreamex range(long startInclusive, long endExclusive) {
        return ofStream(LongStream.range(startInclusive, endExclusive));
    }

    public static LongStreamex rangeClosed(long startInclusive, long endInclusive) {
        return ofStream(LongStream.rangeClosed(startInclusive, endInclusive));
    }

    public static LongStreamex concat(LongStreamex a, LongStreamex b) {
        return ofStream(LongStream.concat(a.internal, b.internal));
    }

    public boolean isParallel() {
        return internal.isParallel();
    }

    public LongStreamex unordered() {
        return setStream(internal.unordered());
    }

    public LongStreamex onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    public void close() {
        internal.close();
    }
}
