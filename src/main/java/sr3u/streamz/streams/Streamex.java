package sr3u.streamz.streams;

import sr3u.streamz.common.Utils;
import sr3u.streamz.functionals.BiConsumerex;
import sr3u.streamz.functionals.BiFunctionex;
import sr3u.streamz.functionals.BinaryOperatorex;
import sr3u.streamz.functionals.Consumerex;
import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.functionals.Predicatex;
import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.UnaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.ToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntFunctionex;
import sr3u.streamz.functionals.primitive.integer.ToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.ToLongFunctionex;
import sr3u.streamz.optionals.Optionalex;

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

public class Streamex<T> {
    protected Stream<T> internal;

    protected Streamex(Stream<T> stream) {
        internal = stream;
    }

    public Stream<T> stream() {
        return internal;
    }

    private Streamex<T> setStream(Stream<T> stream) {
        internal = stream;
        return this;
    }

    public Streamex<T> filter(Predicatex<T> predicate) {
        return setStream(internal.filter(wrap(predicate)));
    }

    public <R> Streamex<R> map(Functionex<T, ? extends R> mapper) {
        return ofStream(internal.map(wrap(mapper)));
    }

    public IntStreamex mapToInt(ToIntFunctionex<T> mapper) {
        return IntStreamex.ofStream(internal.mapToInt(wrap(mapper)));
    }

    public LongStreamex mapToLong(ToLongFunctionex<T> mapper) {
        return LongStreamex.ofStream(internal.mapToLong(wrap(mapper)));
    }

    public DoubleStreamex mapToDouble(ToDoubleFunctionex<T> mapper) {
        return DoubleStreamex.ofStream(internal.mapToDouble(wrap(mapper)));
    }

    public <R> Streamex<R> flatMap(Functionex<T, ? extends Streamex<? extends R>> mapper) {
        return ofStream(internal.flatMap(wrapStream(mapper)));
    }

    public IntStreamex flatMapToInt(Functionex<T, ? extends IntStreamex> mapper) {
        return IntStreamex.ofStream(internal.flatMapToInt(wrapIntStream(mapper)));
    }

    public LongStreamex flatMapToLong(Functionex<T, ? extends LongStreamex> mapper) {
        return LongStreamex.ofStream(internal.flatMapToLong(wrapLongStream(mapper)));
    }

    public DoubleStreamex flatMapToDouble(Functionex<T, ? extends DoubleStreamex> mapper) {
        return DoubleStreamex.ofStream(internal.flatMapToDouble(wrapDoubleStream(mapper)));
    }

    public Streamex<T> distinct() {
        return setStream(internal.distinct());
    }

    public <P> Streamex<T> distinct(Functionex<T, P> getter) {
        Predicate<T> wrap = wrap(Utils.distinctByKey(getter));
        return setStream(internal.filter(wrap));
    }

    public Streamex<T> sorted() {
        return setStream(internal.sorted());
    }

    public Streamex<T> sorted(Comparator<T> comparator) {
        return setStream(internal.sorted(comparator));
    }

    public Streamex<T> peek(Consumerex<T> action) {
        return setStream(internal.peek(wrap(action)));
    }

    public Streamex<T> limit(long maxSize) {
        return setStream(internal.limit(maxSize));
    }

    public Streamex<T> skip(long n) {
        return setStream(internal.skip(n));
    }

    public void forEach(Consumerex<T> action) {
        internal.forEach(wrap(action));
    }

    public void forEachOrdered(Consumerex<T> action) {
        internal.forEachOrdered(wrap(action));
    }

    public Object[] toArray() {
        return internal.toArray();
    }

    public <A> A[] toArray(IntFunctionex<A[]> generator) {
        final IntFunction<A[]> wrappedGenerator = wrapArray(generator);
        return internal.toArray(wrappedGenerator);
    }

    public T reduce(T identity, BinaryOperatorex<T> accumulator) {
        return internal.reduce(identity, wrap(accumulator));
    }

    public Optionalex<T> reduce(BinaryOperatorex<T> accumulator) {
        return Optionalex.ofOptional(internal.reduce(wrap(accumulator)));
    }

    public <U> U reduce(U identity, BiFunctionex<U, T, U> accumulator, BinaryOperatorex<U> combiner) {
        return internal.reduce(identity, wrap(accumulator), wrap(combiner));
    }

    public <R> R collect(Supplierex<R> supplier, BiConsumerex<R, T> accumulator, BiConsumerex<R, R> combiner) {
        return internal.collect(wrap(supplier), wrap(accumulator), wrap(combiner));
    }

    <R, A> R collect(Collector<? super T, A, R> collector) {
        return internal.collect(collector);
    }

    public Optionalex<T> min(Comparator<T> comparator) {
        return Optionalex.ofOptional(internal.min(comparator));
    }

    public Optionalex<T> max(Comparator<T> comparator) {
        return Optionalex.ofOptional(internal.max(comparator));
    }

    public long count() {
        return internal.count();
    }

    public boolean anyMatch(Predicatex<T> predicate) {
        return internal.anyMatch(wrap(predicate));
    }

    public boolean allMatch(Predicatex<T> predicate) {
        return internal.allMatch(wrap(predicate));
    }

    public boolean noneMatch(Predicatex<T> predicate) {
        return internal.noneMatch(wrap(predicate));
    }

    public Optionalex<T> findFirst() {
        return Optionalex.ofOptional(internal.findFirst());
    }

    public Optionalex<T> findAny() {
        return Optionalex.ofOptional(internal.findAny());
    }    /*public static <T1> Builder<T1> builder() { //TODO        return Stream.builder();    }*/

    public static <T1> Streamex<T1> empty() {
        return ofStream(Stream.empty());
    }

    public static <T1> Streamex<T1> of(T1 t1) {
        return ofStream(Stream.of(t1));
    }

    public static <T1> Streamex<T1> ofStream(Stream<T1> t1) {
        return new Streamex<>(t1);
    }

    @SafeVarargs
    public static <T1> Streamex<T1> of(T1... values) {
        return new Streamex<>(Stream.of(values));
    }

    public static <T1> Streamex<T1> iterate(T1 seed, UnaryOperatorex<T1> f) {
        return ofStream(Stream.iterate(seed, wrap(f)));
    }

    public static <T> Streamex<T> generate(Supplierex<T> s) {
        return ofStream(Stream.generate(wrap(s)));
    }

    public static <T> Streamex<T> concat(Streamex<T> a, Streamex<T> b) {
        return ofStream(Stream.concat(a.stream(), b.stream()));
    }

    public Iterator<T> iterator() {
        return internal.iterator();
    }

    public Spliterator<T> spliterator() {
        return internal.spliterator();
    }

    public boolean isParallel() {
        return internal.isParallel();
    }

    public Streamex<T> sequential() {
        return setStream(internal.sequential());
    }

    public Streamex<T> parallel() {
        return setStream(internal.parallel());
    }

    public Streamex<T> unordered() {
        return setStream(internal.unordered());
    }

    public Streamex<T> onClose(Runnable closeHandler) {
        return setStream(internal.onClose(closeHandler));
    }

    public void close() {
        internal.close();
    }
}