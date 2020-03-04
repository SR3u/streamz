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

import java.util.IntSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.IntStream;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.IntExceptionWrapper.wrap;

public class IntStreamex {

    protected IntStream internal;

    public IntStream stream() {
        return internal;
    }

    private IntStreamex setStream(IntStream stream) {
        internal = stream;
        return this;
    }

    private IntStreamex(IntStream intStream) {
        this.internal = intStream;
    }

    public static IntStreamex ofStream(IntStream intStream) {
        return new IntStreamex(intStream);
    }

    public IntStreamex filter(IntPredicatex predicate) {
        return setStream(internal.filter(wrap(predicate)));
    }

    public IntStreamex map(IntUnaryOperatorex mapper) {
        return setStream(internal.map(wrap(mapper)));
    }

    public <U> Streamex<? extends U> mapToObj(IntFunctionex<? extends U> mapper) {
        return Streamex.ofStream(internal.mapToObj(wrap(mapper)));
    }

    public LongStreamex mapToLong(IntToLongFunctionex mapper) {
        return LongStreamex.ofStream(internal.mapToLong(wrap(mapper)));
    }

    public DoubleStreamex mapToDouble(IntToDoubleFunctionex mapper) {
        return DoubleStreamex.ofStream(internal.mapToDouble(wrap(mapper)));
    }

    public IntStreamex flatMap(IntFunctionex<? extends IntStream> mapper) {
        return setStream(internal.flatMap(wrap(mapper)));
    }

    public IntStreamex distinct() {
        return setStream(internal.distinct());
    }

    public IntStreamex sorted() {
        return setStream(internal.sorted());
    }

    public IntStreamex peek(IntConsumerex action) {
        return setStream(internal.peek(wrap(action)));
    }

    public IntStreamex limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    public IntStreamex skip(long n) {
        return setStream(internal.skip(n));
    }

    public void forEach(IntConsumerex action) {
        internal.forEach(wrap(action));
    }

    public void forEachOrdered(IntConsumerex action) {
        internal.forEachOrdered(wrap(action));
    }

    public int[] toArray() {
        return internal.toArray();
    }

    public int reduce(int identity, IntBinaryOperatorex op) {
        return internal.reduce(identity, wrap(op));
    }

    public OptionalIntex reduce(IntBinaryOperatorex op) {
        return OptionalIntex.ofOptional(internal.reduce(wrap(op)));
    }

    public <R> R collect(Supplierex<R> supplier, ObjIntConsumerex<R> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(wrap(supplier),
                wrap(accumulator),
                wrap(combiner));
    }

    public int sum() {
        return internal.sum();
    }

    public OptionalIntex min() {
        return OptionalIntex.ofOptional(internal.min());
    }

    public OptionalIntex max() {
        return OptionalIntex.ofOptional(internal.max());
    }

    public long count() {
        return internal.count();
    }

    public OptionalDoublex average() {
        return OptionalDoublex.ofOptional(internal.average());
    }

    public IntSummaryStatistics summaryStatistics() {
        return internal.summaryStatistics();
    }

    public boolean anyMatch(IntPredicatex predicate) {
        return internal.anyMatch(wrap(predicate));
    }

    public boolean allMatch(IntPredicatex predicate) {
        return internal.allMatch(wrap(predicate));
    }

    public boolean noneMatch(IntPredicatex predicate) {
        return internal.noneMatch(wrap(predicate));
    }

    public OptionalIntex findFirst() {
        return OptionalIntex.ofOptional(internal.findFirst());
    }

    public OptionalIntex findAny() {
        return OptionalIntex.ofOptional(internal.findAny());
    }

    public LongStreamex asLongStream() {
        return LongStreamex.ofStream(internal.asLongStream());
    }

    public DoubleStreamex asDoubleStream() {
        return DoubleStreamex.ofStream(internal.asDoubleStream());
    }

    public Streamex<Integer> boxed() {
        return Streamex.ofStream(internal.boxed());
    }

    public IntStreamex sequential() {
        return setStream(internal.sequential());
    }

    public IntStreamex parallel() {
        return setStream(internal.parallel());
    }

    public PrimitiveIterator.OfInt iterator() {
        return internal.iterator();
    }

    public Spliterator.OfInt spliterator() {
        return internal.spliterator();
    }

    /*public static IntStream.Builder builder() { //TODO
        return IntStream.builder();
    }*/

    public static IntStreamex empty() {
        return ofStream(IntStream.empty());
    }

    public static IntStreamex of(int t) {
        return ofStream(IntStream.of(t));
    }

    public static IntStreamex of(int... values) {
        return ofStream(IntStream.of(values));
    }

    public static IntStreamex iterate(int seed, IntUnaryOperatorex f) {
        return ofStream(IntStream.iterate(seed, wrap(f)));
    }

    public static IntStreamex generate(IntSupplierex s) {
        return ofStream(IntStream.generate(wrap(s)));
    }

    public static IntStreamex range(int startInclusive, int endExclusive) {
        return ofStream(IntStream.range(startInclusive, endExclusive));
    }

    public static IntStreamex rangeClosed(int startInclusive, int endInclusive) {
        return ofStream(IntStream.rangeClosed(startInclusive, endInclusive));
    }

    public static IntStreamex concat(IntStreamex a, IntStreamex b) {
        return ofStream(IntStream.concat(a.internal, b.internal));
    }

    public boolean isParallel() {
        return internal.isParallel();
    }

    public IntStreamex unordered() {
        return setStream(internal.unordered());
    }

    public IntStreamex onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    public void close() {
        internal.close();
    }
}
