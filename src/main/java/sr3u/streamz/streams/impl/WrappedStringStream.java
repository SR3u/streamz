package sr3u.streamz.streams.impl;

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
import sr3u.streamz.streams.StringStreamex;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class WrappedStringStream implements StringStreamex {
    Streamex<String> internal;

    protected WrappedStringStream(Stream<String> stream) {
        internal = Streamex.ofStream(stream);
    }

    @Override
    public StringStreamex filter(Predicatex<String> predicate) {
        return internal.filter(predicate).asStringStream();
    }

    @Override
    public <R> Streamex<R> map(Functionex<String, ? extends R> mapper) {
        return internal.map(mapper);
    }

    @Override
    public IntStreamex mapToInt(ToIntFunctionex<String> mapper) {
        return internal.mapToInt(mapper);
    }

    @Override
    public LongStreamex mapToLong(ToLongFunctionex<String> mapper) {
        return internal.mapToLong(mapper);
    }

    @Override
    public DoubleStreamex mapToDouble(ToDoubleFunctionex<String> mapper) {
        return internal.mapToDouble(mapper);
    }

    @Override
    public StringStreamex mapToString(Functionex<String, String> mapper) {
        return internal.mapToString(mapper);
    }

    @Override
    public <R> Streamex<? extends R> flatMap(Functionex<String, ? extends Streamex<? extends R>> mapper) {
        return internal.flatMap(mapper);
    }

    @Override
    public IntStreamex flatMapToInt(Functionex<String, ? extends IntStreamex> mapper) {
        return internal.flatMapToInt(mapper);
    }

    @Override
    public LongStreamex flatMapToLong(Functionex<String, ? extends LongStreamex> mapper) {
        return internal.flatMapToLong(mapper);
    }

    @Override
    public DoubleStreamex flatMapToDouble(Functionex<String, ? extends DoubleStreamex> mapper) {
        return internal.flatMapToDouble(mapper);
    }

    @Override
    public StringStreamex distinct() {
        return internal.distinct().asStringStream();
    }

    @Override
    public <P> StringStreamex distinct(Functionex<String, P> getter) {
        return internal.distinct(getter).asStringStream();
    }

    @Override
    public StringStreamex sorted() {
        return internal.sorted().asStringStream();
    }

    @Override
    public StringStreamex sorted(Comparator<String> comparator) {
        return internal.sorted(comparator).asStringStream();
    }

    @Override
    public StringStreamex peek(Consumerex<String> action) {
        return internal.peek(action).asStringStream();
    }

    @Override
    public StringStreamex limit(long maxSize) {
        return internal.limit(maxSize).asStringStream();
    }

    @Override
    public StringStreamex skip(long n) {
        return internal.skip(n).asStringStream();
    }

    @Override
    public void forEach(Consumerex<String> action) {
        internal.forEach(action);
    }

    @Override
    public void forEachOrdered(Consumerex<String> action) {
        internal.forEachOrdered(action);
    }

    @Override
    public String[] toArray() {
        return internal.toArray(String[]::new);
    }

    @Override
    public String[] toArray(IntFunctionex<String[]> generator) {
        return internal.toArray(generator);
    }

    @Override
    public String reduce(String identity, BinaryOperatorex<String> accumulator) {
        return internal.reduce(identity, accumulator);
    }

    @Override
    public Optionalex<String> reduce(BinaryOperatorex<String> accumulator) {
        return internal.reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunctionex<U, String, U> accumulator, BinaryOperatorex<U> combiner) {
        return internal.reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplierex<R> supplier, BiConsumerex<R, String> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super String, A, R> collector) {
        return internal.collect(collector);
    }

    @Override
    public Optionalex<String> min(Comparator<String> comparator) {
        return internal.min(comparator);
    }

    @Override
    public Optionalex<String> max(Comparator<String> comparator) {
        return internal.max(comparator);
    }

    @Override
    public long count() {
        return internal.count();
    }

    @Override
    public boolean anyMatch(Predicatex<String> predicate) {
        return internal.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicatex<String> predicate) {
        return internal.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicatex<String> predicate) {
        return internal.noneMatch(predicate);
    }

    @Override
    public Optionalex<String> findFirst() {
        return internal.findFirst();
    }

    @Override
    public Optionalex<String> findAny() {
        return internal.findAny();
    }

    @Override
    public Iterator<String> iterator() {
        return internal.iterator();
    }

    @Override
    public Spliterator<String> spliterator() {
        return internal.spliterator();
    }

    @Override
    public boolean isParallel() {
        return internal.isParallel();
    }

    @Override
    public StringStreamex sequential() {
        return internal.sequential().asStringStream();
    }

    @Override
    public StringStreamex parallel() {
        return internal.parallel().asStringStream();
    }

    @Override
    public StringStreamex unordered() {
        return internal.unordered().asStringStream();
    }

    @Override
    public StringStreamex onClose(Runnable closeHandler) {
        return internal.onClose(closeHandler).asStringStream();
    }

    @Override
    public void close() {
        internal.close();
    }
}
