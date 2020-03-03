package sr3u.streamex.streams;

import sr3u.streamex.functionals.BiConsumerex;
import sr3u.streamex.functionals.BiFunctionex;
import sr3u.streamex.functionals.BinaryOperatorex;
import sr3u.streamex.functionals.Consumerex;
import sr3u.streamex.functionals.Functionex;
import sr3u.streamex.functionals.Predicatex;
import sr3u.streamex.functionals.Supplierex;
import sr3u.streamex.functionals.UnaryOperatorex;
import sr3u.streamex.functionals.primitive.doublefloat.ToDoubleFunctionex;
import sr3u.streamex.functionals.primitive.integer.IntFunctionex;
import sr3u.streamex.functionals.primitive.integer.ToIntFunctionex;
import sr3u.streamex.functionals.primitive.longinteger.ToLongFunctionex;
import sr3u.streamex.optionals.Optionalex;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static sr3u.streamex.common.ExceptionWrapper.wrap;
import static sr3u.streamex.common.ExceptionWrapper.wrapArray;
import static sr3u.streamex.common.ExceptionWrapper.wrapDoubleStream;
import static sr3u.streamex.common.ExceptionWrapper.wrapIntStream;
import static sr3u.streamex.common.ExceptionWrapper.wrapLongStream;
import static sr3u.streamex.common.ExceptionWrapper.wrapStream;

public class Streamex<T> {

    protected Stream<T> internal;

    protected Streamex(Stream<T> stream) {
        internal = stream;
    }

    public Stream<T> stream() {
        return internal;
    }

    public Streamex<T> filter(Predicatex<T> predicate) {
        internal = stream().filter(wrap(predicate));
        return this;
    }

    public <R> Streamex<R> map(Functionex<T, ? extends R> mapper) {
        return Streamex.ofStream(stream().map(wrap(mapper)));
    }

    public IntStreamex mapToInt(ToIntFunctionex<T> mapper) {
        return IntStreamex.ofStream(stream().mapToInt(wrap(mapper)));
    }

    public LongStreamex mapToLong(ToLongFunctionex<T> mapper) {
        return LongStreamex.ofStream(stream().mapToLong(wrap(mapper)));
    }

    public DoubleStreamex mapToDouble(ToDoubleFunctionex<T> mapper) {
        return DoubleStreamex.ofStream(stream().mapToDouble(wrap(mapper)));
    }

    public <R> Streamex<R> flatMap(Functionex<T, ? extends Streamex<? extends R>> mapper) {
        return Streamex.ofStream(stream().flatMap(wrapStream(mapper)));
    }

    public IntStreamex flatMapToInt(Functionex<T, ? extends IntStreamex> mapper) {
        return IntStreamex.ofStream(stream().flatMapToInt(wrapIntStream(mapper)));
    }

    public LongStreamex flatMapToLong(Functionex<T, ? extends LongStreamex> mapper) {
        return LongStreamex.ofStream(stream().flatMapToLong(wrapLongStream(mapper)));
    }


    public DoubleStreamex flatMapToDouble(Functionex<T, ? extends DoubleStreamex> mapper) {
        return DoubleStreamex.ofStream(stream().flatMapToDouble(wrapDoubleStream(mapper)));
    }

    public Streamex<T> distinct() {
        internal = stream().distinct();
        return this;
    }

    public Streamex<T> sorted() {
        internal = stream().sorted();
        return this;
    }

    public Streamex<T> sorted(Comparator<T> comparator) {
        internal = stream().sorted(comparator);
        return this;
    }

    public Streamex<T> peek(Consumerex<T> action) {
        internal = stream().peek(wrap(action));
        return this;
    }

    public Streamex<T> limit(long maxSize) {
        internal = stream().limit(maxSize);
        return this;
    }

    public Streamex<T> skip(long n) {
        internal = stream().skip(n);
        return this;
    }

    public void forEach(Consumerex<T> action) {
        stream().forEach(wrap(action));
    }

    public void forEachOrdered(Consumerex<T> action) {
        stream().forEachOrdered(wrap(action));
    }

    public Object[] toArray() {
        return stream().toArray();
    }

    public <A> A[] toArray(IntFunctionex<A[]> generator) {
        return stream().toArray(wrapArray(generator));
    }

    public T reduce(T identity, BinaryOperatorex<T> accumulator) {
        return stream().reduce(identity, wrap(accumulator));
    }

    public Optionalex<T> reduce(BinaryOperatorex<T> accumulator) {
        return Optionalex.ofOptional(stream().reduce(wrap(accumulator)));
    }

    public <U> U reduce(U identity, BiFunctionex<U, T, U> accumulator, BinaryOperatorex<U> combiner) {
        return stream().reduce(identity, wrap(accumulator), wrap(combiner));
    }

    public <R> R collect(Supplierex<R> supplier, BiConsumerex<R, T> accumulator, BiConsumerex<R, R> combiner) {
        return stream().collect(wrap(supplier), wrap(accumulator), wrap(combiner));
    }

    <R, A> R collect(Collector<? super T, A, R> collector) {
        return stream().collect(collector);
    }

    public Optionalex<T> min(Comparator<T> comparator) {
        return Optionalex.ofOptional(stream().min(comparator));
    }

    public Optionalex<T> max(Comparator<T> comparator) {
        return Optionalex.ofOptional(stream().max(comparator));
    }

    public long count() {
        return stream().count();
    }

    public boolean anyMatch(Predicatex<T> predicate) {
        return stream().anyMatch(wrap(predicate));
    }

    public boolean allMatch(Predicatex<T> predicate) {
        return stream().allMatch(wrap(predicate));
    }

    public boolean noneMatch(Predicatex<T> predicate) {
        return stream().noneMatch(wrap(predicate));
    }

    public Optionalex<T> findFirst() {
        return Optionalex.ofOptional(stream().findFirst());
    }

    public Optionalex<T> findAny() {
        return Optionalex.ofOptional(stream().findAny());
    }

    /*public static <T1> Builder<T1> builder() { //TODO
        return Stream.builder();
    }*/

    public static <T1> Streamex<T1> empty() {
        return Streamex.ofStream(Stream.empty());
    }

    public static <T1> Streamex<T1> of(T1 t1) {
        return Streamex.ofStream(Stream.of(t1));
    }

    public static <T1> Streamex<T1> ofStream(Stream<T1> t1) {
        return new Streamex<>(t1);
    }

    @SafeVarargs
    public static <T1> Streamex<T1> of(T1... values) {
        return new Streamex<>(Stream.of(values));
    }

    public static <T1> Streamex<T1> iterate(T1 seed, UnaryOperatorex<T1> f) {
        return Streamex.ofStream(Stream.iterate(seed, wrap(f)));
    }

    public static <T> Streamex<T> generate(Supplierex<T> s) {
        return Streamex.ofStream(Stream.generate(wrap(s)));
    }

    public static <T> Streamex<T> concat(Streamex<T> a, Streamex<T> b) {
        return Streamex.ofStream(Stream.concat(a.stream(), b.stream()));
    }

    public Iterator<T> iterator() {
        return stream().iterator();
    }

    public Spliterator<T> spliterator() {
        return stream().spliterator();
    }

    public boolean isParallel() {
        return stream().isParallel();
    }

    public Streamex<T> sequential() {
        return Streamex.ofStream(stream().sequential());
    }

    public Streamex<T> parallel() {
        return Streamex.ofStream(stream().parallel());
    }

    public Streamex<T> unordered() {
        return Streamex.ofStream(stream().unordered());
    }

    public Streamex<T> onClose(Runnable closeHandler) {
        return Streamex.ofStream(stream().onClose(closeHandler));
    }

    public void close() {
        stream().close();
    }


}
