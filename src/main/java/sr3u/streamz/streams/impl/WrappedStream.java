package sr3u.streamz.streams.impl;

import sr3u.streamz.common.Utils;
import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.BiFunctionex;
import sr3u.streamz.functionals.BinaryOperatorex;
import sr3u.streamz.functionals.Consumerex;
import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.functionals.Predicatex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.primitive.doublefloat.ToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntFunctionex;
import sr3u.streamz.functionals.primitive.integer.ToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.ToLongFunctionex;
import sr3u.streamz.optionals.Optionalex;
import sr3u.streamz.streams.DoubleStreamex;
import sr3u.streamz.streams.IntStreamex;
import sr3u.streamz.streams.LongStreamex;
import sr3u.streamz.streams.Streamex;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.ExceptionWrapper.wrapArray;
import static sr3u.streamz.common.ExceptionWrapper.wrapDoubleStream;
import static sr3u.streamz.common.ExceptionWrapper.wrapIntStream;
import static sr3u.streamz.common.ExceptionWrapper.wrapLongStream;
import static sr3u.streamz.common.ExceptionWrapper.wrapStream;

public class WrappedStream<T> implements Streamex<T> {
    protected Stream<T> internal;

    protected WrappedStream(Stream<T> stream) {
        internal = stream;
    }

    @Override
    public Stream<T> stream() {
        return internal;
    }

    private WrappedStream<T> setStream(Stream<T> stream) {
        internal = stream;
        return this;
    }

    @Override
    public Streamex<T> filter(Predicatex<T> predicate) {
        return setStream(internal.filter(wrap(predicate)));
    }

    @Override
    public <R> Streamex<R> map(Functionex<T, ? extends R> mapper) {
        return new WrappedStream<>(internal.map(wrap(mapper)));
    }

    @Override
    public IntStreamex mapToInt(ToIntFunctionex<T> mapper) {
        return StreamexSupport.intStreamOf(internal.mapToInt(wrap(mapper)));
    }

    @Override
    public LongStreamex mapToLong(ToLongFunctionex<T> mapper) {
        return StreamexSupport.longStreamOf(internal.mapToLong(wrap(mapper)));
    }

    @Override
    public DoubleStreamex mapToDouble(ToDoubleFunctionex<T> mapper) {
        return StreamexSupport.doubleStreamOf(internal.mapToDouble(wrap(mapper)));
    }

    @Override
    public <R> Streamex<R> flatMap(Functionex<T, ? extends Streamex<? extends R>> mapper) {
        return new WrappedStream<>(internal.flatMap(wrapStream(mapper)));
    }

    @Override
    public IntStreamex flatMapToInt(Functionex<T, ? extends IntStreamex> mapper) {
        return StreamexSupport.intStreamOf(internal.flatMapToInt(wrapIntStream(mapper)));
    }

    @Override
    public LongStreamex flatMapToLong(Functionex<T, ? extends LongStreamex> mapper) {
        return StreamexSupport.longStreamOf(internal.flatMapToLong(wrapLongStream(mapper)));
    }

    @Override
    public DoubleStreamex flatMapToDouble(Functionex<T, ? extends DoubleStreamex> mapper) {
        return StreamexSupport.doubleStreamOf(internal.flatMapToDouble(wrapDoubleStream(mapper)));
    }

    @Override
    public Streamex<T> distinct() {
        return setStream(internal.distinct());
    }

    @Override
    public <P> Streamex<T> distinct(Functionex<T, P> getter) {
        Predicate<T> wrap = wrap(Utils.distinctByKey(getter));
        return setStream(internal.filter(wrap));
    }

    @Override
    public Streamex<T> sorted() {
        return setStream(internal.sorted());
    }

    @Override
    public Streamex<T> sorted(Comparator<T> comparator) {
        return setStream(internal.sorted(comparator));
    }

    @Override
    public Streamex<T> peek(Consumerex<T> action) {
        return setStream(internal.peek(wrap(action)));
    }

    @Override
    public Streamex<T> limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    @Override
    public Streamex<T> skip(long n) {
        return setStream(internal.skip(n));
    }

    @Override
    public void forEach(Consumerex<T> action) {
        internal.forEach(wrap(action));
    }

    @Override
    public void forEachOrdered(Consumerex<T> action) {
        internal.forEachOrdered(wrap(action));
    }

    @Override
    public Object[] toArray() {
        return internal.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunctionex<A[]> generator) {
        final IntFunction<A[]> wrappedGenerator = wrapArray(generator);
        return internal.toArray(wrappedGenerator);
    }

    @Override
    public T reduce(T identity, BinaryOperatorex<T> accumulator) {
        return internal.reduce(identity, wrap(accumulator));
    }

    @Override
    public Optionalex<T> reduce(BinaryOperatorex<T> accumulator) {
        return Optionalex.ofOptional(internal.reduce(wrap(accumulator)));
    }

    @Override
    public <U> U reduce(U identity, BiFunctionex<U, T, U> accumulator, BinaryOperatorex<U> combiner) {
        return internal.reduce(identity, wrap(accumulator), wrap(combiner));
    }

    @Override
    public <R> R collect(Supplierex<R> supplier, BiConsumerex<R, T> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(wrap(supplier), wrap(accumulator), wrap(combiner));
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return internal.collect(collector);
    }

    @Override
    public Optionalex<T> min(Comparator<T> comparator) {
        return Optionalex.ofOptional(internal.min(comparator));
    }

    @Override
    public Optionalex<T> max(Comparator<T> comparator) {
        return Optionalex.ofOptional(internal.max(comparator));
    }

    @Override
    public long count() {
        return internal.count();
    }

    @Override
    public boolean anyMatch(Predicatex<T> predicate) {
        return internal.anyMatch(wrap(predicate));
    }

    @Override
    public boolean allMatch(Predicatex<T> predicate) {
        return internal.allMatch(wrap(predicate));
    }

    @Override
    public boolean noneMatch(Predicatex<T> predicate) {
        return internal.noneMatch(wrap(predicate));
    }

    @Override
    public Optionalex<T> findFirst() {
        return Optionalex.ofOptional(internal.findFirst());
    }

    @Override
    public Optionalex<T> findAny() {
        return Optionalex.ofOptional(internal.findAny());
    }    /*public static <T1> Builder<T1> builder() { //TODO        return Stream.builder();    }*/

    @Override
    public Iterator<T> iterator() {
        return internal.iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return internal.spliterator();
    }

    @Override
    public boolean isParallel() {
        return internal.isParallel();
    }

    @Override
    public Streamex<T> sequential() {
        return setStream(internal.sequential());
    }

    @Override
    public Streamex<T> parallel() {
        return setStream(internal.parallel());
    }

    @Override
    public Streamex<T> unordered() {
        return setStream(internal.unordered());
    }

    @Override
    public Streamex<T> onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    @Override
    public void close() {
        internal.close();
    }
}