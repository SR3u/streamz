package sr3u.streamz.streams;

import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.DoubleToIntFunctionex;
import sr3u.streamz.functionals.primitive.DoubleToLongFunctionex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleBinaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleConsumerex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleFunctionex;
import sr3u.streamz.functionals.primitive.doublefloat.DoublePredicatex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleSupplierex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleUnaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.ObjDoubleConsumerex;
import sr3u.streamz.optionals.OptionalDoublex;

import java.util.DoubleSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.DoubleExceptionWrapper.wrap;

public class DoubleStreamex {
    protected DoubleStream internal;

    private DoubleStreamex(DoubleStream doubleStream) {
        internal = doubleStream;
    }

    public DoubleStream stream() {
        return internal;
    }

    private DoubleStreamex setStream(DoubleStream stream) {
        internal = stream;
        return this;
    }

    public static DoubleStreamex ofStream(DoubleStream doubleStream) {
        return new DoubleStreamex(doubleStream);
    }

    public DoubleStreamex filter(DoublePredicatex predicate) {
        return setStream(internal.filter(wrap(predicate)));
    }

    public DoubleStreamex map(DoubleUnaryOperatorex mapper) {
        return setStream(internal.map(wrap(mapper)));
    }

    public <U> Streamex<? extends U> mapToObj(DoubleFunctionex<? extends U> mapper) {
        return Streamex.ofStream(internal.mapToObj(wrap(mapper)));
    }

    public IntStreamex mapToInt(DoubleToIntFunctionex mapper) {
        return IntStreamex.ofStream(internal.mapToInt(wrap(mapper)));
    }

    public LongStreamex mapToLong(DoubleToLongFunctionex mapper) {
        return LongStreamex.ofStream(internal.mapToLong(wrap(mapper)));
    }

    public DoubleStreamex flatMap(DoubleFunctionex<? extends DoubleStream> mapper) {
        return setStream(internal.flatMap(wrap(mapper)));
    }

    public DoubleStreamex distinct() {
        return setStream(internal.distinct());
    }

    public DoubleStreamex sorted() {
        return setStream(internal.sorted());
    }

    public DoubleStreamex peek(DoubleConsumerex action) {
        return setStream(internal.peek(wrap(action)));
    }

    public DoubleStreamex limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    public DoubleStreamex skip(long n) {
        return setStream(internal.skip(n));
    }

    public void forEach(DoubleConsumerex action) {
        internal.forEach(wrap(action));
    }

    public void forEachOrdered(DoubleConsumerex action) {
        internal.forEachOrdered(wrap(action));
    }

    public double[] toArray() {
        return internal.toArray();
    }

    public double reduce(double identity, DoubleBinaryOperatorex op) {
        return internal.reduce(identity, wrap(op));
    }

    public OptionalDoublex reduce(DoubleBinaryOperatorex op) {
        return OptionalDoublex.ofOptional(internal.reduce(wrap(op)));
    }

    public <R> R collect(Supplierex<R> supplier, ObjDoubleConsumerex<R> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(wrap(supplier), wrap(accumulator), wrap(combiner));
    }

    public double sum() {
        return internal.sum();
    }

    public OptionalDoublex min() {
        return OptionalDoublex.ofOptional(internal.min());
    }

    public OptionalDoublex max() {
        return OptionalDoublex.ofOptional(internal.max());
    }

    public long count() {
        return internal.count();
    }

    public OptionalDoublex average() {
        return OptionalDoublex.ofOptional(internal.average());
    }

    public DoubleSummaryStatistics summaryStatistics() {
        return internal.summaryStatistics();
    }

    public boolean anyMatch(DoublePredicatex predicate) {
        return internal.anyMatch(wrap(predicate));
    }

    public boolean allMatch(DoublePredicatex predicate) {
        return internal.allMatch(wrap(predicate));
    }

    public boolean noneMatch(DoublePredicatex predicate) {
        return internal.noneMatch(wrap(predicate));
    }

    public OptionalDoublex findFirst() {
        return OptionalDoublex.ofOptional(internal.findFirst());
    }

    public OptionalDoublex findAny() {
        return OptionalDoublex.ofOptional(internal.findAny());
    }

    public Stream<Double> boxed() {
        return internal.boxed();
    }

    public DoubleStreamex sequential() {
        return setStream(internal.sequential());
    }

    public DoubleStreamex parallel() {
        return setStream(internal.parallel());
    }

    public PrimitiveIterator.OfDouble iterator() {
        return internal.iterator();
    }

    public Spliterator.OfDouble spliterator() {
        return internal.spliterator();
    }    /*public static Builder builder() {//TODO        return DoubleStream.builder();    }*/

    public static DoubleStreamex empty() {
        return ofStream(DoubleStream.empty());
    }

    public static DoubleStreamex of(double t) {
        return ofStream(DoubleStream.of(t));
    }

    public static DoubleStreamex of(double... values) {
        return ofStream(DoubleStream.of(values));
    }

    public static DoubleStreamex iterate(double seed, DoubleUnaryOperatorex f) {
        return ofStream(DoubleStream.iterate(seed, wrap(f)));
    }

    public static DoubleStreamex generate(DoubleSupplierex s) {
        return ofStream(DoubleStream.generate(wrap(s)));
    }

    public static DoubleStreamex concat(DoubleStreamex a, DoubleStreamex b) {
        return ofStream(DoubleStream.concat(a.internal, b.internal));
    }

    public boolean isParallel() {
        return internal.isParallel();
    }

    public DoubleStreamex unordered() {
        return setStream(internal.unordered());
    }

    public DoubleStreamex onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    public void close() {
        internal.close();
    }
}