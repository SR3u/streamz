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
import sr3u.streamz.streams.impl.StreamexSupport;

import java.util.DoubleSummaryStatistics;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.stream.DoubleStream;

public interface DoubleStreamex {

    static DoubleStreamex empty() {
        return of();
    }

    static DoubleStreamex of(double t) {
        return of(new double[]{t});
    }

    static DoubleStreamex of(double... values) {
        return StreamexSupport.ofDouble(values);
    }

    static DoubleStreamex iterate(double seed, DoubleUnaryOperatorex f) {
        return StreamexSupport.iterateDouble(seed, f);
    }

    static DoubleStreamex generate(DoubleSupplierex s) {
        return StreamexSupport.generateDouble(s);
    }

    static DoubleStreamex concat(DoubleStreamex a, DoubleStreamex b) {
        return StreamexSupport.concatDouble(a, b);
    }

    DoubleStream stream();

    DoubleStreamex filter(DoublePredicatex predicate);

    DoubleStreamex map(DoubleUnaryOperatorex mapper);

    <U> Streamex<U> mapToObj(DoubleFunctionex<U> mapper);

    IntStreamex mapToInt(DoubleToIntFunctionex mapper);

    LongStreamex mapToLong(DoubleToLongFunctionex mapper);

    DoubleStreamex flatMap(DoubleFunctionex<? extends DoubleStream> mapper);

    DoubleStreamex distinct();

    DoubleStreamex sorted();

    DoubleStreamex peek(DoubleConsumerex action);

    DoubleStreamex limit(long maxSize);

    DoubleStreamex skip(long n);

    void forEach(DoubleConsumerex action);

    void forEachOrdered(DoubleConsumerex action);

    double[] toArray();

    double reduce(double identity, DoubleBinaryOperatorex op);

    OptionalDoublex reduce(DoubleBinaryOperatorex op);

    <R> R collect(Supplierex<R> supplier, ObjDoubleConsumerex<R> accumulator, BiConsumerex<R, R> combiner);

    double sum();

    OptionalDoublex min();

    OptionalDoublex max();

    long count();

    OptionalDoublex average();

    DoubleSummaryStatistics summaryStatistics();

    boolean anyMatch(DoublePredicatex predicate);

    boolean allMatch(DoublePredicatex predicate);

    boolean noneMatch(DoublePredicatex predicate);

    OptionalDoublex findFirst();

    OptionalDoublex findAny();

    default Streamex<Double> boxed() {
        return mapToObj(Double::valueOf);
    }

    DoubleStreamex sequential();

    DoubleStreamex parallel();

    PrimitiveIterator.OfDouble iterator();

    Spliterator.OfDouble spliterator()    /*public static Builder builder() {//TODO        return DoubleStream.builder();    }*/;

    boolean isParallel();

    DoubleStreamex unordered();

    DoubleStreamex onClose(Runnable closeHandler);

    void close();
}
