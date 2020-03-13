package sr3u.streamz.common;

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
import sr3u.streamz.streams.DoubleStreamex;
import sr3u.streamz.streams.IntStreamex;
import sr3u.streamz.streams.LongStreamex;
import sr3u.streamz.streams.Streamex;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static sr3u.streamz.common.ExceptionCreator.createException;

public class ExceptionWrapper {

    public static <R> Supplier<R> wrap(Supplierex<R> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T, R> Function<T, R> wrap(Functionex<T, R> f) {
        return item -> {
            try {
                return f.apply(item);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> Consumer<T> wrap(Consumerex<T> consumer) {
        return item -> {
            try {
                consumer.accept(item);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <R, T> BiConsumer<R, T> wrap(BiConsumerex<R, T> accumulator) {
        return (r, t) -> {
            try {
                accumulator.accept(r, t);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T, U, R> BiFunction<U, T, R> wrapBiFunction(BiFunctionex<U, T, R> f) {
        return (a, b) -> {
            try {
                return f.apply(a, b);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> Predicate<T> wrap(Predicatex<T> predicate) {
        return item -> {
            try {
                return predicate.test(item);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> UnaryOperator<T> wrap(UnaryOperatorex<T> f) {
        return a -> {
            try {
                return f.apply(a);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }


    public static <T> ToIntFunction<T> wrap(ToIntFunctionex<T> f) {
        return i -> {
            try {
                return f.applyAsInt(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> ToLongFunction<T> wrap(ToLongFunctionex<T> f) {
        return i -> {
            try {
                return f.applyAsLong(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> ToDoubleFunction<T> wrap(ToDoubleFunctionex<T> f) {
        return item -> {
            try {
                return f.applyAsDouble(item);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T, R> Function<T, ? extends Stream<? extends R>> wrapStream(Functionex<T, ? extends Streamex<? extends R>> f) {
        return stream -> {
            try {
                return f.apply(stream).stream();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> Function<? super T, ? extends IntStream> wrapIntStream(Functionex<T, ? extends IntStreamex> f) {
        return stream -> {
            try {
                return f.apply(stream).stream();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> Function<? super T, ? extends LongStream> wrapLongStream(Functionex<T, ? extends LongStreamex> f) {
        return stream -> {
            try {
                return f.apply(stream).stream();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> Function<? super T, ? extends DoubleStream> wrapDoubleStream(Functionex<T, ? extends DoubleStreamex> f) {
        return stream -> {
            try {
                return f.apply(stream).stream();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <A> IntFunction<A[]> wrapArray(IntFunctionex<A[]> generator) {
        return a -> {
            try {
                return generator.apply(a);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T> BinaryOperator<T> wrap(BinaryOperatorex<T> op) {
        return (a, b) -> {
            try {
                return op.apply(a, b);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <T, U> Function<T, Optional<U>> wrapOptional(Functionex<T, Optionalex<U>> f) {
        return optional -> {
            try {
                return f.apply(optional).optional();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

}
