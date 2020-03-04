package sr3u.streamz.common;

import sr3u.streamz.functionals.Functionex;
import sr3u.streamz.functionals.Predicatex;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Utils {
    public static <T> Predicatex<T> distinctByKey(Functionex<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
