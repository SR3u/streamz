package sr3u.streamz.streams.impl;

import sr3u.streamz.functionals.Supplierex;
import sr3u.streamz.functionals.UnaryOperatorex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleSupplierex;
import sr3u.streamz.functionals.primitive.doublefloat.DoubleUnaryOperatorex;
import sr3u.streamz.functionals.primitive.integer.IntSupplierex;
import sr3u.streamz.functionals.primitive.integer.IntUnaryOperatorex;
import sr3u.streamz.functionals.primitive.longinteger.LongSupplierex;
import sr3u.streamz.functionals.primitive.longinteger.LongUnaryOperatorex;
import sr3u.streamz.streams.DoubleStreamex;
import sr3u.streamz.streams.IntStreamex;
import sr3u.streamz.streams.LongStreamex;
import sr3u.streamz.streams.Streamex;
import sr3u.streamz.streams.StringStreamex;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static sr3u.streamz.common.ExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.DoubleExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.IntExceptionWrapper.wrap;
import static sr3u.streamz.common.primitive.LongExceptionWrapper.wrap;

public class StreamexSupport {

    public static <T> Streamex<T> streamOf(Stream<T> stream) {
        return new WrappedStream<>(stream);
    }

    @SafeVarargs
    public static <T> Streamex<T> streamOf(T... values) {
        return streamOf(Stream.of(values));
    }

    public static <T1> Streamex<T1> iterate(T1 seed, UnaryOperatorex<T1> f) {
        return streamOf(Stream.iterate(seed, wrap(f)));
    }

    public static <T> Streamex<T> generate(Supplierex<T> s) {
        return streamOf(Stream.generate(wrap(s)));
    }

    public static <T> Streamex<T> concat(Streamex<T> a, Streamex<T> b) {
        return streamOf(Stream.concat(a.stream(), b.stream()));
    }

    public static IntStreamex ofInt(int... values) {
        return intStreamOf(IntStream.of(values));
    }

    public static IntStreamex iterateInt(int seed, IntUnaryOperatorex f) {
        return intStreamOf(IntStream.iterate(seed, wrap(f)));
    }

    public static IntStreamex generateInt(IntSupplierex s) {
        return intStreamOf(IntStream.generate(wrap(s)));
    }

    public static IntStreamex concatInt(IntStreamex a, IntStreamex b) {
        return intStreamOf(IntStream.concat(a.stream(), b.stream()));
    }

    public static LongStreamex ofLong(long... values) {
        return longStreamOf(LongStream.of(values));
    }

    public static LongStreamex iterateLong(long seed, LongUnaryOperatorex f) {
        return longStreamOf(LongStream.iterate(seed, wrap(f)));
    }

    public static LongStreamex generateLong(LongSupplierex s) {
        return longStreamOf(LongStream.generate(wrap(s)));
    }

    public static LongStreamex concatLong(LongStreamex a, LongStreamex b) {
        return longStreamOf(LongStream.concat(a.stream(), b.stream()));
    }

    public static LongStreamex longStreamOf(LongStream LongStream) {
        return new WrappedLongStream(LongStream);
    }

    public static LongStreamex rangeLong(long startInclusive, long endExclusive) {
        return longStreamOf(LongStream.range(startInclusive, endExclusive));
    }

    public static LongStreamex rangeLongClosed(long startInclusive, long endInclusive) {
        return longStreamOf(LongStream.rangeClosed(startInclusive, endInclusive));
    }

    public static IntStreamex intStreamOf(IntStream IntStream) {
        return new WrappedIntStream(IntStream);
    }

    public static IntStreamex rangeInt(int startInclusive, int endExclusive) {
        return intStreamOf(IntStream.range(startInclusive, endExclusive));
    }

    public static IntStreamex rangeIntClosed(int startInclusive, int endInclusive) {
        return intStreamOf(IntStream.rangeClosed(startInclusive, endInclusive));
    }

    public static DoubleStreamex ofDouble(double... values) {
        return doubleStreamOf(DoubleStream.of(values));
    }

    public static DoubleStreamex iterateDouble(double seed, DoubleUnaryOperatorex f) {
        return doubleStreamOf(DoubleStream.iterate(seed, wrap(f)));
    }

    public static DoubleStreamex generateDouble(DoubleSupplierex s) {
        return doubleStreamOf(DoubleStream.generate(wrap(s)));
    }

    public static DoubleStreamex concatDouble(DoubleStreamex a, DoubleStreamex b) {
        return doubleStreamOf(DoubleStream.concat(a.stream(), b.stream()));
    }

    public static DoubleStreamex doubleStreamOf(DoubleStream doubleStream) {
        return new WrappedDoubleStream(doubleStream);
    }

    public static StringStreamex stringStreamOf(Stream<String> stream) {
        return new WrappedStringStream(stream);
    }

}
