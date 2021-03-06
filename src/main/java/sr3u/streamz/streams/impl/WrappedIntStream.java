package sr3u.streamz.streams.impl;

import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.IntToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.IntToLongFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntBinaryOperatorex;
import sr3u.streamz.functionals.primitive.integer.IntConsumerex;
import sr3u.streamz.functionals.primitive.integer.IntFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntPredicatex;
import sr3u.streamz.functionals.primitive.integer.IntUnaryOperatorex;
import sr3u.streamz.functionals.primitive.integer.ObjIntConsumerex;
import sr3u.streamz.optionals.OptionalDoublex;
import sr3u.streamz.optionals.OptionalIntex;
import sr3u.streamz.streams.DoubleStreamex;
import sr3u.streamz.streams.IntStreamex;
import sr3u.streamz.streams.LongStreamex;
import sr3u.streamz.streams.Streamex;

import java.util.IntSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.IntStream;

import static sr3u.streamz.common.primitive.IntExceptionWrapper.wrapIntStream;

public class WrappedIntStream implements IntStreamex {

    protected IntStream internal;

    @Override
    public IntStream stream() {
        return internal;
    }

    private IntStreamex setStream(IntStream stream) {
        internal = stream;
        return this;
    }

    WrappedIntStream(IntStream intStream) {
        this.internal = intStream;
    }

    @Override
    public IntStreamex filter(IntPredicatex predicate) {
        return setStream(internal.filter(predicate.wrap()));
    }

    @Override
    public IntStreamex map(IntUnaryOperatorex mapper) {
        return setStream(internal.map(mapper.wrap()));
    }

    @Override
    public <U> Streamex<U> mapToObj(IntFunctionex<U> mapper) {
        return Streamex.ofStream(internal.mapToObj(mapper.wrap()));
    }

    @Override
    public LongStreamex mapToLong(IntToLongFunctionex mapper) {
        return StreamexSupport.longStreamOf(internal.mapToLong(mapper.wrap()));
    }

    @Override
    public DoubleStreamex mapToDouble(IntToDoubleFunctionex mapper) {
        return StreamexSupport.doubleStreamOf(internal.mapToDouble(mapper.wrap()));
    }

    @Override
    public IntStreamex flatMap(IntFunctionex<? extends IntStreamex> mapper) {
        return setStream(internal.flatMap(wrapIntStream(mapper)));
    }

    @Override
    public IntStreamex distinct() {
        return setStream(internal.distinct());
    }

    @Override
    public IntStreamex sorted() {
        return setStream(internal.sorted());
    }

    @Override
    public IntStreamex peek(IntConsumerex action) {
        return setStream(internal.peek(action.wrap()));
    }

    @Override
    public IntStreamex limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    @Override
    public IntStreamex skip(long n) {
        return setStream(internal.skip(n));
    }

    @Override
    public void forEach(IntConsumerex action) {
        internal.forEach(action.wrap());
    }

    @Override
    public void forEachOrdered(IntConsumerex action) {
        internal.forEachOrdered(action.wrap());
    }

    @Override
    public int[] toArray() {
        return internal.toArray();
    }

    @Override
    public int reduce(int identity, IntBinaryOperatorex op) {
        return internal.reduce(identity, op.wrap());
    }

    @Override
    public OptionalIntex reduce(IntBinaryOperatorex op) {
        return OptionalIntex.ofOptional(internal.reduce(op.wrap()));
    }

    @Override
    public <R> R collect(Supplierex<R> supplier, ObjIntConsumerex<R> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(supplier.wrap(),
                accumulator.wrap(),
                combiner.wrap());
    }

    @Override
    public int sum() {
        return internal.sum();
    }

    @Override
    public OptionalIntex min() {
        return OptionalIntex.ofOptional(internal.min());
    }

    @Override
    public OptionalIntex max() {
        return OptionalIntex.ofOptional(internal.max());
    }

    @Override
    public long count() {
        return internal.count();
    }

    @Override
    public OptionalDoublex average() {
        return OptionalDoublex.ofOptional(internal.average());
    }

    @Override
    public IntSummaryStatistics summaryStatistics() {
        return internal.summaryStatistics();
    }

    @Override
    public boolean anyMatch(IntPredicatex predicate) {
        return internal.anyMatch(predicate.wrap());
    }

    @Override
    public boolean allMatch(IntPredicatex predicate) {
        return internal.allMatch(predicate.wrap());
    }

    @Override
    public boolean noneMatch(IntPredicatex predicate) {
        return internal.noneMatch(predicate.wrap());
    }

    @Override
    public OptionalIntex findFirst() {
        return OptionalIntex.ofOptional(internal.findFirst());
    }

    @Override
    public OptionalIntex findAny() {
        return OptionalIntex.ofOptional(internal.findAny());
    }

    @Override
    public LongStreamex asLongStream() {
        return StreamexSupport.longStreamOf(internal.asLongStream());
    }

    @Override
    public DoubleStreamex asDoubleStream() {
        return StreamexSupport.doubleStreamOf(internal.asDoubleStream());
    }

    @Override
    public Streamex<Integer> boxed() {
        return Streamex.ofStream(internal.boxed());
    }

    @Override
    public IntStreamex sequential() {
        return setStream(internal.sequential());
    }

    @Override
    public IntStreamex parallel() {
        return setStream(internal.parallel());
    }

    @Override
    public PrimitiveIterator.OfInt iterator() {
        return internal.iterator();
    }

    @Override
    public Spliterator.OfInt spliterator() {
        return internal.spliterator();
    }

    /*public static IntStream.Builder builder() { //TODO
        return IntStream.builder();
    }*/

    @Override
    public boolean isParallel() {
        return internal.isParallel();
    }

    @Override
    public IntStreamex unordered() {
        return setStream(internal.unordered());
    }

    @Override
    public IntStreamex onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    @Override
    public void close() {
        internal.close();
    }
}
