package sr3u.streamz.common.primitive;

import sr3u.streamz.functionals.primitive.LongToDoubleFunctionex;
import sr3u.streamz.functionals.primitive.LongToIntFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.LongBinaryOperatorex;
import sr3u.streamz.functionals.primitive.longinteger.LongConsumerex;
import sr3u.streamz.functionals.primitive.longinteger.LongFunctionex;
import sr3u.streamz.functionals.primitive.longinteger.LongPredicatex;
import sr3u.streamz.functionals.primitive.longinteger.LongSupplierex;
import sr3u.streamz.functionals.primitive.longinteger.LongUnaryOperatorex;
import sr3u.streamz.functionals.primitive.longinteger.ObjLongConsumerex;

import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjLongConsumer;

import static sr3u.streamz.common.ExceptionCreator.createException;

public class LongExceptionWrapper {
    public static LongBinaryOperator wrap(LongBinaryOperatorex op) {
        return (left, right) -> {
            try {
                return op.applyAsLong(left, right);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <R> LongFunction<R> wrap(LongFunctionex<R> f) {
        return i -> {
            try {
                return f.apply(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static LongConsumer wrap(LongConsumerex consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static LongUnaryOperator wrap(LongUnaryOperatorex op) {
        return i -> {
            try {
                return op.applyAsLong(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static LongPredicate wrap(LongPredicatex predicate) {
        return i -> {
            try {
                return predicate.test(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static LongSupplier wrap(LongSupplierex supplier) {
        return () -> {
            try {
                return supplier.getAsLong();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static LongToDoubleFunction wrap(LongToDoubleFunctionex f) {
        return i -> {
            try {
                return f.applyAsDouble(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static LongToIntFunction wrap(LongToIntFunctionex f) {
        return i -> {
            try {
                return f.applyAsInt(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <R> ObjLongConsumer<R> wrap(ObjLongConsumerex<R> consumer) {
        return (a, b) -> {
            try {
                consumer.accept(a, b);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

}
