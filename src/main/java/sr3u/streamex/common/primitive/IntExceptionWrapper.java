package sr3u.streamex.common.primitive;

import sr3u.streamex.functionals.primitive.IntToDoubleFunctionex;
import sr3u.streamex.functionals.primitive.IntToLongFunctionex;
import sr3u.streamex.functionals.primitive.integer.IntBinaryOperatorex;
import sr3u.streamex.functionals.primitive.integer.IntConsumerex;
import sr3u.streamex.functionals.primitive.integer.IntFunctionex;
import sr3u.streamex.functionals.primitive.integer.IntPredicatex;
import sr3u.streamex.functionals.primitive.integer.IntSupplierex;
import sr3u.streamex.functionals.primitive.integer.IntUnaryOperatorex;
import sr3u.streamex.functionals.primitive.integer.ObjIntConsumerex;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;

import static sr3u.streamex.common.ExceptionCreator.createException;

public class IntExceptionWrapper {
    public static IntBinaryOperator wrap(IntBinaryOperatorex op) {
        return (left, right) -> {
            try {
                return op.applyAsInt(left, right);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <R> IntFunction<R> wrap(IntFunctionex<R> f) {
        return i -> {
            try {
                return f.apply(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static IntConsumer wrap(IntConsumerex consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static IntUnaryOperator wrap(IntUnaryOperatorex op) {
        return i -> {
            try {
                return op.applyAsInt(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static IntPredicate wrap(IntPredicatex predicate) {
        return i -> {
            try {
                return predicate.test(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static IntSupplier wrap(IntSupplierex supplier) {
        return () -> {
            try {
                return supplier.getAsInt();
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static IntToDoubleFunction wrap(IntToDoubleFunctionex f) {
        return i -> {
            try {
                return f.applyAsDouble(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static IntToLongFunction wrap(IntToLongFunctionex f) {
        return i -> {
            try {
                return f.applyAsLong(i);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

    public static <R> ObjIntConsumer<R> wrap(ObjIntConsumerex<R> consumer) {
        return (a, b) -> {
            try {
                consumer.accept(a, b);
            } catch (Exception e) {
                throw createException(e);
            }
        };
    }

}
