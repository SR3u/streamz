package sr3u.streamex.streams;

import sr3u.streamex.functionals.BiConsumerex;
import sr3u.streamex.functionals.Supplierex;
import sr3u.streamex.functionals.primitive.DoubleToIntFunctionex;
import sr3u.streamex.functionals.primitive.DoubleToLongFunctionex;
import sr3u.streamex.functionals.primitive.doublefloat.DoubleBinaryOperatorex;
import sr3u.streamex.functionals.primitive.doublefloat.DoubleConsumerex;
import sr3u.streamex.functionals.primitive.doublefloat.DoubleFunctionex;
import sr3u.streamex.functionals.primitive.doublefloat.DoublePredicatex;
import sr3u.streamex.functionals.primitive.doublefloat.DoubleSupplierex;
import sr3u.streamex.functionals.primitive.doublefloat.DoubleUnaryOperatorex;
import sr3u.streamex.functionals.primitive.doublefloat.ObjDoubleConsumerex;
import sr3u.streamex.optionals.OptionalDoublex;

import java.util.DoubleSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static sr3u.streamex.common.ExceptionWrapper.wrap;
import static sr3u.streamex.common.primitive.DoubleExceptionWrapper.wrap;

public class DoubleStreamex {

    protected DoubleStream internal;

    private DoubleStreamex(DoubleStream doubleStream) {
        internal = doubleStream;
    }

    public DoubleStream stream() {
        return internal;
    }

    public static DoubleStreamex ofStream(DoubleStream doubleStream) {
        return new DoubleStreamex(doubleStream);
    }

    public DoubleStreamex filter(DoublePredicatex predicate) {
        return DoubleStreamex.ofStream(internal.filter(wrap(predicate)));
    }

    public DoubleStreamex map(DoubleUnaryOperatorex mapper) {
        return DoubleStreamex.ofStream(internal.map(wrap(mapper)));
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
        return DoubleStreamex.ofStream(internal.flatMap(wrap(mapper)));
    }

    public DoubleStreamex distinct() {
        return DoubleStreamex.ofStream(internal.distinct());
    }

    public DoubleStreamex sorted() {
        return DoubleStreamex.ofStream(internal.sorted());
    }

    public DoubleStreamex peek(DoubleConsumerex action) {
        return DoubleStreamex.ofStream(internal.peek(wrap(action)));
    }

    public DoubleStreamex limit(long maxSize) {
        return DoubleStreamex.ofStream(internal.limit(maxSize));
    }

    public DoubleStreamex skip(long n) {
        return DoubleStreamex.ofStream(internal.skip(n));
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
        return DoubleStreamex.ofStream(internal.sequential());
    }

    public DoubleStreamex parallel() {
        return DoubleStreamex.ofStream(internal.parallel());
    }

    public PrimitiveIterator.OfDouble iterator() {
        return internal.iterator();
    }

    public Spliterator.OfDouble spliterator() {
        return internal.spliterator();
    }

    /*public static Builder builder() {//TODO
        return DoubleStream.builder();
    }*/

    public static DoubleStreamex empty() {
        return DoubleStreamex.ofStream(DoubleStream.empty());
    }

    public static DoubleStreamex of(double t) {
        return DoubleStreamex.ofStream(DoubleStream.of(t));
    }

    public static DoubleStreamex of(double... values) {
        return DoubleStreamex.ofStream(DoubleStream.of(values));
    }

    public static DoubleStreamex iterate(double seed, DoubleUnaryOperatorex f) {
        return DoubleStreamex.ofStream(DoubleStream.iterate(seed, wrap(f)));
    }

    public static DoubleStreamex generate(DoubleSupplierex s) {
        return DoubleStreamex.ofStream(DoubleStream.generate(wrap(s)));
    }

    public static DoubleStreamex concat(DoubleStreamex a, DoubleStreamex b) {
        return DoubleStreamex.ofStream(DoubleStream.concat(a.internal, b.internal));
    }

    public boolean isParallel() {
        return internal.isParallel();
    }

    public DoubleStreamex unordered() {
        return DoubleStreamex.ofStream(internal.unordered());
    }

    public DoubleStreamex onClose(Runnable closeHandler) {
        internal = internal.onClose(closeHandler);
        return this;
    }

    public void close() {
        internal.close();
    }
}
