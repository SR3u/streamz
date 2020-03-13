package sr3u.streamz.streams.impl;

import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.DoubleToIntFunctionex;
import sr3u.streamz.functionals.primitive.DoubleToLongFunctionex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleBinaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleConsumerex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleFunctionex;
import sr3u.streamz.functionals.primitive.doublefloat.DoublePredicatex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleUnaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.ObjDoubleConsumerex;
import sr3u.streamz.optionals.OptionalDoublex;
import sr3u.streamz.streams.DoubleStreamex;
import sr3u.streamz.streams.IntStreamex;
import sr3u.streamz.streams.LongStreamex;
import sr3u.streamz.streams.Streamex;

import java.util.DoubleSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.DoubleStream;

import static sr3u.streamz.common.primitive.DoubleExceptionWrapper.wrapDoubleStream;

public class WrappedDoubleStream implements DoubleStreamex {
    protected DoubleStream internal;

    WrappedDoubleStream(DoubleStream doubleStream) {
        internal = doubleStream;
    }

    @Override
    public DoubleStream stream() {
        return internal;
    }

    private DoubleStreamex setStream(DoubleStream stream) {
        internal = stream;
        return this;
    }

    @Override
    public DoubleStreamex filter(DoublePredicatex predicate) {
        return setStream(internal.filter(predicate.wrap()));
    }

    @Override
    public DoubleStreamex map(DoubleUnaryOperatorex mapper) {
        return setStream(internal.map(mapper.wrap()));
    }

    @Override
    public <U> Streamex<U> mapToObj(DoubleFunctionex<U> mapper) {
        return Streamex.ofStream(internal.mapToObj(mapper.wrap()));
    }

    @Override
    public IntStreamex mapToInt(DoubleToIntFunctionex mapper) {
        return StreamexSupport.intStreamOf(internal.mapToInt(mapper.wrap()));
    }

    @Override
    public LongStreamex mapToLong(DoubleToLongFunctionex mapper) {
        return StreamexSupport.longStreamOf(internal.mapToLong(mapper.wrap()));
    }

    @Override
    public DoubleStreamex flatMap(DoubleFunctionex<? extends DoubleStreamex> mapper) {
        return setStream(internal.flatMap(wrapDoubleStream(mapper)));
    }

    @Override
    public DoubleStreamex distinct() {
        return setStream(internal.distinct());
    }

    @Override
    public DoubleStreamex sorted() {
        return setStream(internal.sorted());
    }

    @Override
    public DoubleStreamex peek(DoubleConsumerex action) {
        return setStream(internal.peek(action.wrap()));
    }

    @Override
    public DoubleStreamex limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    @Override
    public DoubleStreamex skip(long n) {
        return setStream(internal.skip(n));
    }

    @Override
    public void forEach(DoubleConsumerex action) {
        internal.forEach(action.wrap());
    }

    @Override
    public void forEachOrdered(DoubleConsumerex action) {
        internal.forEachOrdered(action.wrap());
    }

    @Override
    public double[] toArray() {
        return internal.toArray();
    }

    @Override
    public double reduce(double identity, DoubleBinaryOperatorex op) {
        return internal.reduce(identity, op.wrap());
    }

    @Override
    public OptionalDoublex reduce(DoubleBinaryOperatorex op) {
        return OptionalDoublex.ofOptional(internal.reduce(op.wrap()));
    }

    @Override
    public <R> R collect(Supplierex<R> supplier, ObjDoubleConsumerex<R> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(supplier.wrap(), accumulator.wrap(), combiner.wrap());
    }

    @Override
    public double sum() {
        return internal.sum();
    }

    @Override
    public OptionalDoublex min() {
        return OptionalDoublex.ofOptional(internal.min());
    }

    @Override
    public OptionalDoublex max() {
        return OptionalDoublex.ofOptional(internal.max());
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
    public DoubleSummaryStatistics summaryStatistics() {
        return internal.summaryStatistics();
    }

    @Override
    public boolean anyMatch(DoublePredicatex predicate) {
        return internal.anyMatch(predicate.wrap());
    }

    @Override
    public boolean allMatch(DoublePredicatex predicate) {
        return internal.allMatch(predicate.wrap());
    }

    @Override
    public boolean noneMatch(DoublePredicatex predicate) {
        return internal.noneMatch(predicate.wrap());
    }

    @Override
    public OptionalDoublex findFirst() {
        return OptionalDoublex.ofOptional(internal.findFirst());
    }

    @Override
    public OptionalDoublex findAny() {
        return OptionalDoublex.ofOptional(internal.findAny());
    }

    @Override
    public Streamex<Double> boxed() {
        return StreamexSupport.streamOf(internal.boxed());
    }

    @Override
    public DoubleStreamex sequential() {
        return setStream(internal.sequential());
    }

    @Override
    public DoubleStreamex parallel() {
        return setStream(internal.parallel());
    }

    @Override
    public PrimitiveIterator.OfDouble iterator() {
        return internal.iterator();
    }

    @Override
    public Spliterator.OfDouble spliterator() {
        return internal.spliterator();
    }

    /*public static Builder builder() {//TODO        return DoubleStream.builder();    }*/

    @Override
    public boolean isParallel() {
        return internal.isParallel();
    }

    @Override
    public DoubleStreamex unordered() {
        return setStream(internal.unordered());
    }

    @Override
    public DoubleStreamex onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    @Override
    public void close() {
        internal.close();
    }
}