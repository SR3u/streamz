package sr3u.streamz.streams;

import java.io.PrintStream;
import java.util.stream.Collectors;

public interface StringStreamex extends Streamex<String> {

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

}
