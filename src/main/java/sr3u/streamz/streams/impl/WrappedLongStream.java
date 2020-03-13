package sr3u.streamz.streams.impl;

import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.LongToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.LongToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.LongBinaryOperatorex;
import sr3u.streamz.functionals.primitive.longinteger.LongConsumerex;
import sr3u.streamz.functionals.primitive.longinteger.LongFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.LongPredicatex;
import sr3u.streamz.functionals.primitive.longinteger.LongUnaryOperatorex;
import sr3u.streamz.functionals.primitive.longinteger.ObjLongConsumerex;
import sr3u.streamz.optionals.OptionalDoublex;
import sr3u.streamz.optionals.OptionalLongex;
import sr3u.streamz.streams.DoubleStreamex;
import sr3u.streamz.streams.IntStreamex;
import sr3u.streamz.streams.LongStreamex;
import sr3u.streamz.streams.Streamex;

import java.util.LongSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.LongStream;

import static sr3u.streamz.common.primitive.LongExceptionWrapper.wrapLongStream;

public class WrappedLongStream implements LongStreamex {

    protected LongStream internal;

    WrappedLongStream(LongStream longStream) {
        this.internal = longStream;
    }

    @Override
    public LongStream stream() {
        return internal;
    }

    private LongStreamex setStream(LongStream stream) {
        internal = stream;
        return this;
    }


    @Override
    public LongStreamex filter(LongPredicatex predicate) {
        return setStream(internal.filter(predicate.wrap()));
    }

    @Override
    public LongStreamex map(LongUnaryOperatorex mapper) {
        return setStream(internal.map(mapper.wrap()));
    }

    @Override
    public <U> Streamex<U> mapToObj(LongFunctionex<U> mapper) {
        return Streamex.ofStream(internal.mapToObj(mapper.wrap()));
    }

    @Override
    public IntStreamex mapToInt(LongToIntFunctionex mapper) {
        return StreamexSupport.intStreamOf(internal.mapToInt(mapper.wrap()));
    }

    @Override
    public DoubleStreamex mapToDouble(LongToDoubleFunctionex mapper) {
        return StreamexSupport.doubleStreamOf(internal.mapToDouble(mapper.wrap()));
    }

    @Override
    public LongStreamex flatMap(LongFunctionex<? extends LongStreamex> mapper) {
        return setStream(internal.flatMap(wrapLongStream(mapper)));
    }

    @Override
    public LongStreamex distinct() {
        return setStream(internal.distinct());
    }

    @Override
    public LongStreamex sorted() {
        return setStream(internal.sorted());
    }

    @Override
    public LongStreamex peek(LongConsumerex action) {
        return setStream(internal.peek(action.wrap()));
    }

    @Override
    public LongStreamex limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    @Override
    public LongStreamex skip(long n) {
        return setStream(internal.skip(n));
    }

    @Override
    public void forEach(LongConsumerex action) {
        internal.forEach(action.wrap());
    }

    @Override
    public void forEachOrdered(LongConsumerex action) {
        internal.forEachOrdered(action.wrap());
    }

    @Override
    public long[] toArray() {
        return internal.toArray();
    }

    @Override
    public long reduce(long identity, LongBinaryOperatorex op) {
        return internal.reduce(identity, op.wrap());
    }

    @Override
    public OptionalLongex reduce(LongBinaryOperatorex op) {
        return OptionalLongex.ofOptional(internal.reduce(op.wrap()));
    }

    @Override
    public <R> R collect(Supplierex<R> supplier, ObjLongConsumerex<R> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(supplier.wrap(), accumulator.wrap(), combiner.wrap());
    }

    @Override
    public long sum() {
        return internal.sum();
    }

    @Override
    public OptionalLongex min() {
        return OptionalLongex.ofOptional(internal.min());
    }

    @Override
    public OptionalLongex max() {
        return OptionalLongex.ofOptional(internal.max());
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
    public LongSummaryStatistics summaryStatistics() {
        return internal.summaryStatistics();
    }

    @Override
    public boolean anyMatch(LongPredicatex predicate) {
        return internal.anyMatch(predicate.wrap());
    }

    @Override
    public boolean allMatch(LongPredicatex predicate) {
        return internal.allMatch(predicate.wrap());
    }

    @Override
    public boolean noneMatch(LongPredicatex predicate) {
        return internal.noneMatch(predicate.wrap());
    }

    @Override
    public OptionalLongex findFirst() {
        return OptionalLongex.ofOptional(internal.findFirst());
    }

    @Override
    public OptionalLongex findAny() {
        return OptionalLongex.ofOptional(internal.findAny());
    }

    @Override
    public DoubleStreamex asDoubleStream() {
        return StreamexSupport.doubleStreamOf(internal.asDoubleStream());
    }

    @Override
    public Streamex<Long> boxed() {
        return Streamex.ofStream(internal.boxed());
    }

    @Override
    public LongStreamex sequential() {
        return setStream(internal.sequential());
    }

    @Override
    public LongStreamex parallel() {
        return setStream(internal.parallel());
    }

    @Override
    public PrimitiveIterator.OfLong iterator() {
        return internal.iterator();
    }

    @Override
    public Spliterator.OfLong spliterator() {
        return internal.spliterator();
    }

    /*public static LongStream.Builder builder() { //TODO
        return LongStream.builder();
    }*/

    @Override
    public boolean isParallel() {
        return internal.isParallel();
    }

    @Override
    public LongStreamex unordered() {
        return setStream(internal.unordered());
    }

    @Override
    public LongStreamex onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    @Override
    public void close() {
        internal.close();
    }
}
