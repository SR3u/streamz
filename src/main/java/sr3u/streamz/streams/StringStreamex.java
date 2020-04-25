package sr3u.streamz.streams;

import sr3u.streamz.functionals.*;
import sr3u.streamz.functionals.primitive.doublefloat.ToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.integer.IntFunctionex;
import sr3u.streamz.functionals.primitive.integer.ToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.ToLongFunctionex;
import sr3u.streamz.optionals.Optionalex;
import sr3u.streamz.streams.impl.StreamexSupport;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface StringStreamex {

    default String joined() {
        return joined("");
    }

    default String joined(CharSequence delimiter) {
        return joined(delimiter, "", "");
    }

    default String joined(CharSequence delimiter,
                          CharSequence prefix,
                          CharSequence suffix) {
        return collect(Collectors.joining(delimiter, prefix, suffix));
    }

    default void printEach(PrintStream printStream) {
        forEach(printStream::print);
    }

    default void printlnEach(PrintStream printStream) {
        forEach(printStream::println);
    }

    //
    // Syntax sugar
    //

    static StringStreamex empty() {
        return ofStream(Stream.empty());
    }

    static StringStreamex of(String String) {
        return ofStream(Stream.of(String));
    }

    static StringStreamex ofStream(Stream<String> stream) {
        return StreamexSupport.stringStreamOf(stream);
    }

    static StringStreamex ofCollection(Collection<String> collection) {
        return ofStream(collection.stream());
    }

    static StringStreamex of(String... values) {
        return StreamexSupport.stringStreamOf(Stream.of(values));
    }

    static StringStreamex iterate(String seed, UnaryOperatorex<String> f) {
        return StreamexSupport.iterate(seed, f).mapToString();
    }

    static StringStreamex generate(Supplierex<String> s) {
        return StreamexSupport.generate(s).mapToString();
    }

    static StringStreamex concat(StringStreamex a, StringStreamex b) {
        return StreamexSupport.concat(a.mapToObject(), b.mapToObject()).mapToString();
    }

    default StringStreamex append(StringStreamex another) {
        return Streamex.concat(this.mapToObject(), another.mapToObject()).mapToString();
    }

    StringStreamex filter(Predicatex<String> predicate);

    <R> Streamex<R> map(Functionex<String, ? extends R> mapper);

    IntStreamex mapToInt(ToIntFunctionex<String> mapper);

    LongStreamex mapToLong(ToLongFunctionex<String> mapper);

    DoubleStreamex mapToDouble(ToDoubleFunctionex<String> mapper);

    StringStreamex mapToString(Functionex<String, String> mapper);

    default Streamex<Object> mapToObject() {
        return map(s->s);
    }

    <R> Streamex<? extends R> flatMap(Functionex<String, ? extends Streamex<? extends R>> mapper);

    IntStreamex flatMapToInt(Functionex<String, ? extends IntStreamex> mapper);

    LongStreamex flatMapToLong(Functionex<String, ? extends LongStreamex> mapper);

    DoubleStreamex flatMapToDouble(Functionex<String, ? extends DoubleStreamex> mapper);

    StringStreamex distinct();

    <P> StringStreamex distinct(Functionex<String, P> getter);

    StringStreamex sorted();

    StringStreamex sorted(Comparator<String> comparator);

    StringStreamex peek(Consumerex<String> action);

    StringStreamex limit(long maxSize);

    StringStreamex skip(long n);

    void forEach(Consumerex<String> action);

    void forEachOrdered(Consumerex<String> action);

    String[] toArray();

    String[] toArray(IntFunctionex<String[]> generator);

    String reduce(String identity, BinaryOperatorex<String> accumulator);

    Optionalex<String> reduce(BinaryOperatorex<String> accumulator);

    <U> U reduce(U identity, BiFunctionex<U, String, U> accumulator, BinaryOperatorex<U> combiner);

    <R> R collect(Supplierex<R> supplier, BiConsumerex<R, String> accumulator, BiConsumerex<R, R> combiner);

    <R, A> R collect(Collector<? super String, A, R> collector);

    Optionalex<String> min(Comparator<String> comparator);

    Optionalex<String> max(Comparator<String> comparator);

    default Optionalex<String> min() {
        return min(String::compareTo);
    }

    default Optionalex<String> max() {
        return max(String::compareTo);
    }

    long count();

    boolean anyMatch(Predicatex<String> predicate);

    boolean allMatch(Predicatex<String> predicate);

    boolean noneMatch(Predicatex<String> predicate);

    Optionalex<String> findFirst();

    Optionalex<String> findAny();
    /*public static Builder<String> builder() { //TODO        return Stream.builder();    }*/

    Iterator<String> iterator();

    Spliterator<String> spliterator();

    boolean isParallel();

    StringStreamex sequential();

    StringStreamex parallel();

    StringStreamex unordered();

    StringStreamex onClose(Runnable closeHandler);

    void close();

}
